package com.semsari.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.semsari.domain.*;
import com.semsari.service.*;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.semsari.gateway.PaymentGateway;
import com.semsari.propertyeditor.CityEditor;

import com.semsari.service.person.PersonService;
import com.semsari.util.Util;

@Controller
@RequestMapping(value = "/")
public class SiteController {
	private static final Logger logger = LoggerFactory
			.getLogger(SiteController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PersonService personService;


	@Autowired
	private DealService dealService;

	@Autowired
	private ItemCategoryService categoryService;


    @Autowired
    private SubCategoryService subCategoryService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CityService cityService;
	
	@Autowired
	private CommentService commentService;

	@Autowired
	private PaymentGateway paymentGateway;

	@Autowired
    TransactionService txnService;

	private @Autowired CityEditor cityEditor;


    @Autowired
    RequestCache requestCache;

    @Autowired
    protected AuthenticationManager authenticationManager;



	@InitBinder
	public void iniBinder(WebDataBinder binder) {
		binder.registerCustomEditor(City.class, this.cityEditor);
	}

    private int pageNumber = 0;
    private int pageSize = 0;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(
            @RequestParam(value = "category", required = false) Integer catergoryId,
            Locale locale, Model model) {

        model.addAttribute("title",
                messageSource.getMessage("website.home.title", null, locale));
        model.addAttribute("categories", categoryService.findAll());
        List<Item> items;
        List<Item> freeItems;
        if (catergoryId != null && catergoryId > 0) {
            SubCategory category = subCategoryService.find(catergoryId);
            if (category != null) {
                items = dealService
                        .find(0,null,DealLabel.FEATURED, category, ItemStatus.ON, null, null, null, null, null, null, null, null,true,null,null,pageSize,pageNumber);
                freeItems = dealService
                        .find(0,null,DealLabel.NORMAL, category, ItemStatus.ON, null, null, null, null, null, null, null, null,true,null,null,pageSize,pageNumber);
            } else {
              items =  new ArrayList<Item>();
                freeItems = new ArrayList<Item>();
            }

        } else {
            items = dealService
                    .find(0,null,DealLabel.FEATURED, null, ItemStatus.ON, null, null, null, null, null, null, null, null,true,null,null, pageSize,pageNumber);
            freeItems = dealService
                    .find(0,null,DealLabel.NORMAL, null, ItemStatus.ON, null, null, null, null, null, null, null, null,true,null,null,pageSize,pageNumber);

        }

        model.addAttribute("deals", items);
        model.addAttribute("freeItems", freeItems);
        return "website/index";
    }





    @RequestMapping(value = "/search", method ={ RequestMethod.POST,RequestMethod.GET})
    public String search(
            @RequestParam(value = "txt", required = false) String txt,
            @RequestParam(value = "category", required = false) int cateId,
            @RequestParam(value = "order", required = false) String order,
            @RequestParam(value = "asc", required = false) String asc,
            Locale locale, Model model) {


        model.addAttribute("title",
                messageSource.getMessage("website.home.search", null, locale));
        List<Item> items = new ArrayList<Item>();
        if(txt !=null && !txt.isEmpty()) {
            txt = txt.trim();
            model.addAttribute("txt", txt);

        }else{
            txt =null;
        }
          SubCategory category = subCategoryService.find(cateId);
            model.addAttribute("categories", categoryService.findAll());
        if(category !=null) {
            model.addAttribute("cat", category);
        }else{
            model.addAttribute("cat", new SubCategory());
        }

          items = dealService
                    .find(0, txt,null, category, ItemStatus.ON, null, null, null, null, null, null, null, null,true,order,asc,pageSize,pageNumber);

            model.addAttribute("resultCount", items.size());
            model.addAttribute("deals", items);

        return "website/search";
    }



	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(@RequestParam("deal") int dealId, Locale locale,
			Model uiModel) throws Exception {
        Item item = dealService.find(dealId);
        if(dealService.isExpired(item)){
            throw new Exception("Item has been Expired");
        }

        if(item !=null){
            dealService.incrementVisit(item);
        }
        uiModel.addAttribute("categories", categoryService.findAll());
		uiModel = fillDetail(dealId, locale, uiModel);		
		return "website/detail";
	}

	@RequestMapping(value = "/newcomment", method = RequestMethod.POST)
	public String newComment(@RequestParam("dealId") int dealId,
			@RequestParam("memo") String memo, Locale locale, Model uiModel) {
			Item item = dealService.find(dealId);
			Person author = personService.findByUserName(Util.getCurrentUserName());
			if(item !=null && author !=null){
				Comment comment = new Comment();
				comment.setItem(item);
				comment.setDate(new Date());
				comment.setCommentText(memo);
				comment.setAuthor(author);

				comment.setAccepted(false);
				
				 Comment com = commentService.create(comment);
				 if(com !=null)
				 {
					 uiModel.addAttribute("successMsg",
								messageSource.getMessage("comment.insert.success", null, locale));
				 }else{
					 uiModel.addAttribute("errorMsg",
								messageSource.getMessage("comment.insert.failed", null, locale));
				 }
			}
		uiModel = fillDetail(dealId, locale, uiModel);
		return "website/detail";
	}
	
	private Model fillDetail(int dealId, Locale locale, Model uiModel){
		
		Item item = dealService.find(dealId);
		boolean expired = false;
		if (item == null)
			throw new ResourceNotFoundException(dealId + "");
		else {
            uiModel.addAttribute("deal", item);

			Date now = new Date();
			if ((item.getValidity().compareTo(now) < 0)
					|| item.getStatus() != ItemStatus.ON) {
				expired = true;
			}

            List<Integer> images = new ArrayList<Integer>();
            if(item.getImage1() != null && item.getImage1().length >  0)
                images.add(1);
            if(item.getImage2() != null && item.getImage2().length >  0)
                images.add(2);
            if(item.getImage3() != null && item.getImage3().length >  0)
                images.add(3);
            if(item.getImage4() != null && item.getImage4().length >  0)
                images.add(4);

            uiModel.addAttribute("images", images);
            //	List<Comment> comments = commentService.findByDeal(deal, true);
		//	uiModel.addAttribute("comments", comments);
			List<Item> similars = dealService.findSimilars(item);

			uiModel.addAttribute("similars", similars);

		}
		//uiModel.addAttribute("expired", expired);
		uiModel.addAttribute("title",
		messageSource.getMessage("website.detail.title", null, locale));
		return uiModel;
	}

	@RequestMapping(value = "/myerror", method = RequestMethod.GET)
	public String myerror() throws Throwable {
		throw new Exception("This is a sample exception.");

	}

	@RequestMapping(value = "/resetpass", method = RequestMethod.GET)
	public String resetPassword(Model uiModel) throws Throwable {

		return "website/resetpass";
	}

	@RequestMapping(value = "/resetpass", method = RequestMethod.POST)
	public String resetPassword(@RequestParam("email") String email,
			Model uiModel, Locale locale) throws Throwable {

		Person person = personService.findByUserName(email);
		if (person != null && person.getId() > 0) {
			int result = personService.resetPassword(person.getId(), null);
			if (result > 0)
				uiModel.addAttribute("successMsg", messageSource.getMessage(
						"security.resetpass.success.message", null, locale));
		}
		uiModel = fillModelForIndex(uiModel, locale);
		return "website/index";

	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String changePassword(Model uiModel) throws Throwable {

		return "website/changePassword";
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String changePassword(
			@RequestParam("new_password") String newPassword, Model uiModel,
			Locale locale) throws Throwable {

		Person person = personService.findByUserName(Util.getCurrentUserName());
		if (person != null && person.getId() > 0) {
			int result = personService.changePassword(person.getId(),
					newPassword);
			if (result > 0)
				uiModel.addAttribute("successMsg", messageSource.getMessage(
						"security.password.change.success", null, locale));
		}
		uiModel = fillModelForIndex(uiModel, locale);
		return "website/index";

	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String RegisterCustomer(Model uiModel, Locale locale) {

		uiModel.addAttribute("title", messageSource.getMessage(
				"website.register.title", null, locale));
		uiModel.addAttribute("cities", cityService.findAll());
		Customer customer = new Customer();
		customer.setContact(new Contact());
		uiModel.addAttribute("customer", customer);
		return "website/register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String RegisterCustomerPost(
			 @Valid @ModelAttribute("customer") Customer customer,
			BindingResult result, Locale locale, Model uiModel, HttpServletRequest request) {

		if (result.hasErrors()) {
			uiModel.addAttribute("title", messageSource.getMessage(
					"website.register.title", null, locale));
			uiModel.addAttribute("cities", cityService.findAll());
			return "website/register";
		}


        String username = customer.getUsername();
        String password = customer.getPassword();

		customer = customerService.create(customer);



		if (customer != null && customer.getId() > 0) {
			/*uiModel.addAttribute("successMsg", messageSource.getMessage(
					"customer.register.success", null, locale));*/

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

            // generate session if one doesn't exist
            request.getSession();

            token.setDetails(new WebAuthenticationDetails(request));
            Authentication authenticatedUser = authenticationManager.authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(authenticatedUser);


            logger.debug("customer= " + customer.getId() + ", "
                    + customer.getFirstName() + " " + customer.getLastName());

            return "redirect:customer/panel";
		}else {

            uiModel = fillModelForIndex(uiModel, locale);

            return "website/index";
        }
	}

	private Model fillModelForIndex(Model uiModel, Locale locale) {
		uiModel.addAttribute("title",
				messageSource.getMessage("website.home.title", null, locale));
		uiModel.addAttribute("categories", categoryService.findAll());
		uiModel.addAttribute("featureds", dealService
				.findDealsByLabelAndStatus(DealLabel.FEATURED, ItemStatus.ON));
		uiModel.addAttribute("deals", dealService.findDealsByStatusAndNotLabel(
				DealLabel.FEATURED, ItemStatus.ON));

		return uiModel;
	}
}
