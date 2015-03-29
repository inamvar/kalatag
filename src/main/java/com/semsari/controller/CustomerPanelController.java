package com.semsari.controller;


import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;


import com.semsari.domain.*;
import com.semsari.gateway.PayLineGateway;
import com.semsari.gateway.PaymentGateway;
import com.semsari.propertyeditor.CityEditor;
import com.semsari.propertyeditor.ItemCategoryEditor;
import com.semsari.propertyeditor.SubCategoryEditor;
import com.semsari.service.*;
import com.semsari.util.ImageUtil;
import org.apache.http.HttpResponse;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.tools.generic.ClassTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.semsari.service.person.PersonService;
import com.semsari.util.Util;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/customer")
public class CustomerPanelController {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private PersonService personService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DealService dealService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ItemCategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired

    private TransactionService txnService;

    @Autowired
    private   PaymentGateway paymentGateway;


    private @Autowired
    ItemCategoryEditor categoryEditor;


    private @Autowired
    SubCategoryEditor subCategoryEditor;


    private @Autowired
    CityEditor cityEditor;

    @InitBinder
    public void iniBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ItemCategory.class, this.categoryEditor);
        binder.registerCustomEditor(SubCategory.class, this.subCategoryEditor);
        binder.registerCustomEditor(City.class, this.cityEditor);
    }

    @RequestMapping(value = "/panel", method = RequestMethod.GET)
	public String panel(
			Locale locale, Model uiModel) {

		uiModel = fillModel(uiModel, locale);


		return "website/customer/home";


	}


    @RequestMapping(value = "/deal/select-category", method = RequestMethod.GET)
    public String selectCategory(Locale locale, Model uiModel){
        uiModel.addAttribute("categories", categoryService.findAll());
        return "website/customer/select-category";
    }

    @RequestMapping(value = "/deal/add", method = RequestMethod.GET)
    public String addDeal(@ModelAttribute("deal") Item item, @RequestParam("category") int catId,
            Locale locale, Model uiModel) {
        if(catId ==0) {
            return "redirect:select-category";
        }
        uiModel.addAttribute("title",
                messageSource.getMessage("deal.insert.message", null, locale));



        if(item == null)
            item =new Item() ;

        Customer customer = customerService.findByUserName(Util.getCurrentUserName());
        item.setCustomer(customer);

        if( item.getCategory() == null || item.getCategory().getId() <=0){
                if(catId > 0) {
                    item.setCategory(subCategoryService.find(catId));
                }


        }else {
            LinkedHashMap<ItemPeriod, String> periods = new LinkedHashMap<ItemPeriod, String>();
            periods.put(ItemPeriod.Free, messageSource.getMessage(
                    "deal.free", null, locale));
            periods.put(ItemPeriod.Week, messageSource.getMessage(
                    "deal.week", new Object[]{item.getCategory().getParent().getPrice() * 1}, locale));
            periods.put(ItemPeriod.TwoWeek, messageSource.getMessage(
                    "deal.twoweek", new Object[]{item.getCategory().getParent().getPrice() * 1.8}, locale));
            periods.put(ItemPeriod.Month, messageSource.getMessage(
                    "deal.month", new Object[]{item.getCategory().getParent().getPrice() * 3.6}, locale));
            periods.put(ItemPeriod.TreeMonth, messageSource.getMessage(
                    "deal.threemonth", new Object[]{item.getCategory().getParent().getPrice() * 10}, locale));
            uiModel.addAttribute("periods", periods);
        }

        //item.setCity(item.getCustomer().getContact().getCity());
        uiModel.addAttribute("categories", categoryService.findAll());
        uiModel.addAttribute("deal", item);
        uiModel.addAttribute("cities", cityService.findAll());

        return "website/customer/add-deal";
    }



    @RequestMapping(value = "/deal/add", method = RequestMethod.POST)
    public String addDeal(@ModelAttribute("deal") @Valid Item item,
                      BindingResult result,
                      @RequestParam("file0") MultipartFile file0,
                      @RequestParam("file1") MultipartFile file1,
                      @RequestParam("file2") MultipartFile file2,
                      @RequestParam("file3") MultipartFile file3,
                      @RequestParam("file4") MultipartFile file4,

                      Locale locale,
                      Model uiModel) throws Exception {

        if (result.hasErrors()) {





                LinkedHashMap<ItemPeriod, String> periods = new LinkedHashMap<ItemPeriod, String>();
                periods.put(ItemPeriod.Free, messageSource.getMessage(
                        "deal.free", null, locale));
                periods.put(ItemPeriod.Week, messageSource.getMessage(
                        "deal.week", new Object[]{item.getCategory().getParent().getPrice() * 1}, locale));
                periods.put(ItemPeriod.TwoWeek, messageSource.getMessage(
                        "deal.twoweek", new Object[]{item.getCategory().getParent().getPrice() * 1.8}, locale));
                periods.put(ItemPeriod.Month, messageSource.getMessage(
                        "deal.month", new Object[]{item.getCategory().getParent().getPrice() * 3.6}, locale));
                periods.put(ItemPeriod.TreeMonth, messageSource.getMessage(
                        "deal.threemonth", new Object[]{item.getCategory().getParent().getPrice() * 10}, locale));
                uiModel.addAttribute("periods", periods);

            uiModel.addAttribute("deal", item);
            uiModel.addAttribute("categories", categoryService.findAll());
            uiModel.addAttribute("cities", cityService.findAll());

            uiModel.addAttribute("title", messageSource.getMessage(
                    "deal.insert.message", null, locale));

            return "deal/add";
        }

        int IMG_WIDTH = 800;
        int IMG_HEIGHT= 600;
        if (file0 != null && file0.getSize() > 0) {

            try {
                item.setThumbnail(ImageUtil.resizeImageWithHint(file0.getBytes(),250,250) );
            } catch (IOException e) {

                e.printStackTrace();
            }
        }


        if (file1 != null && file1.getSize() > 0) {

            try {
                item.setImage1(ImageUtil.resizeImageWithHint(file1.getBytes(), IMG_WIDTH, IMG_HEIGHT));
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        if (file2 != null && file2.getSize() > 0) {

            try {
                item.setImage2(ImageUtil.resizeImageWithHint(file2.getBytes(), IMG_WIDTH, IMG_HEIGHT));
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        if (file3 != null && file3.getSize() > 0) {

            try {
                item.setImage3(ImageUtil.resizeImageWithHint(file3.getBytes(), IMG_WIDTH, IMG_HEIGHT));
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        if (file4 != null && file4.getSize() > 0) {

            try {
                item.setImage4(ImageUtil.resizeImageWithHint(file4.getBytes(), IMG_WIDTH, IMG_HEIGHT));
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
        Customer customer = customerService.findByUserName(Util.getCurrentUserName());
        item.setCustomer(customer);
        item.setStatus(ItemStatus.OFF);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, item.getPeriod().getValue());
        item.setValidity(cal.getTime());


        item.setRegisterDate(new Date());
        if(item.getPeriod() == ItemPeriod.Free){
            item.setLabel(DealLabel.NORMAL);
            item = dealService.create(item);
            return "redirect:../deal";
        }else{
            item.setLabel(DealLabel.FEATURED);
            Transaction trx= new Transaction();



           double price = preparePrice(item);

            trx.setAmount(price);
            trx.setDate(new Date());
            trx.setDealId(item.getId());
            trx.setItem(item);
            trx.setQty(1);
            trx.setTotalAmount(price);
            trx.setPerson(customer);





            uiModel.addAttribute("trx", trx);


                paymentGateway.setTrx(trx);
                String res = paymentGateway.send();
                if (Integer.parseInt(res) > 0)
                {

                    item.setStatus(ItemStatus.WAITING_FOR_PAYMENT);
                    item = dealService.create(item);
                    trx.setItem(item);
                    trx.setId_get(res);
                    trx.setStatus(TransactiontStatus.PENDING);
                    trx = txnService.create(trx);
                    return "redirect:" +((PayLineGateway)paymentGateway).getPaymentUrl() + res;
                }
                else
                {
                   throw new Exception(res);

                }




        }



    }


    private double preparePrice(Item item){
        double price = item.getCategory().getParent().getPrice();
        switch (item.getPeriod()) {
            case Free:
                price = 0;
                break;
            case Week:
                price *= 1;
                break;
            case TwoWeek:
                price *= 1.8;
                break;
            case Month:
                price *= 3.6;
                break;
            case TreeMonth:
                price *= 10;
                break;
        }


        return  price;

    }


    @RequestMapping(value = "/deal/revival", method = RequestMethod.GET)
    public String revival(@RequestParam("itemId") int itemId, Locale locale,
                          Model uiModel) throws Exception {
        Item item = dealService.find(itemId);
        Customer cus = customerService.findByUserName( Util.getCurrentUserName());
        if(item == null){
            throw  new ResourceNotFoundException("Item Not found");
        }else if(cus.getId() != item.getCustomer().getId()){
            throw  new Exception("Item does not belongs to this customer");
        }else{

            uiModel.addAttribute("item",item);

            LinkedHashMap<ItemPeriod, String> periods = new LinkedHashMap<ItemPeriod, String>();
            periods.put(ItemPeriod.Free, messageSource.getMessage(
                    "deal.free", null, locale));
            periods.put(ItemPeriod.Week, messageSource.getMessage(
                    "deal.week", new Object[]{item.getCategory().getParent().getPrice() * 1}, locale));
            periods.put(ItemPeriod.TwoWeek, messageSource.getMessage(
                    "deal.twoweek", new Object[]{item.getCategory().getParent().getPrice() * 1.8}, locale));
            periods.put(ItemPeriod.Month, messageSource.getMessage(
                    "deal.month", new Object[]{item.getCategory().getParent().getPrice() * 3.6}, locale));
            periods.put(ItemPeriod.TreeMonth, messageSource.getMessage(
                    "deal.threemonth", new Object[]{item.getCategory().getParent().getPrice() * 10}, locale));
            uiModel.addAttribute("periods", periods);

        }
        return "website/customer/revival";
    }



    @RequestMapping(value = "/deal/pay", method = RequestMethod.GET)
    public String repay(@RequestParam("itemId") int itemId,
                          Locale locale,
                          Model uiModel) throws Exception {


        Item item = dealService.find(itemId);
        Customer cus = customerService.findByUserName( Util.getCurrentUserName());
        if(item == null){
            throw  new ResourceNotFoundException("Item Not found");
        }else if(cus.getId() != item.getCustomer().getId()){
            throw  new Exception("Item does not belongs to this customer");
        }else {

                Transaction trx= new Transaction();
                double price = preparePrice(item);

                trx.setAmount(price);
                trx.setDate(new Date());
                trx.setDealId(item.getId());
                trx.setItem(item);
                trx.setQty(1);
                trx.setTotalAmount(price);
                trx.setPerson(cus);
                uiModel.addAttribute("trx", trx);

                paymentGateway.setTrx(trx);
                String res = paymentGateway.send();
                if (Integer.parseInt(res) > 0)
                {
                    trx.setItem(item);
                    trx.setId_get(res);
                    trx.setStatus(TransactiontStatus.PENDING);
                    trx = txnService.create(trx);
                    return "redirect:" +((PayLineGateway)paymentGateway).getPaymentUrl() + res;
                }
                else
                {
                    throw new Exception(res);

                }


        }


    }


    @RequestMapping(value = "/deal/revival", method = RequestMethod.POST)
    public String revival(@RequestParam("itemId") int itemId,
                          @RequestParam("period") ItemPeriod period,
                          Locale locale,
                          Model uiModel) throws Exception {


        Item item = dealService.find(itemId);
        Customer cus = customerService.findByUserName( Util.getCurrentUserName());
        if(item == null){
            throw  new ResourceNotFoundException("Item Not found");
        }else if(cus.getId() != item.getCustomer().getId()){
            throw  new Exception("Item does not belongs to this customer");
        }else {
            item.setPeriod(period);
            if(period == ItemPeriod.Free){
                item.setLabel(DealLabel.NORMAL);
                dealService.update(item);
                dealService.changeStatus(ItemStatus.ON,item.getId());
            }else{

                item.setLabel(DealLabel.FEATURED);
                Transaction trx= new Transaction();
                double price = preparePrice(item);

                trx.setAmount(price);
                trx.setDate(new Date());
                trx.setDealId(item.getId());
                trx.setItem(item);
                trx.setQty(1);
                trx.setTotalAmount(price);
                trx.setPerson(cus);
                uiModel.addAttribute("trx", trx);

                paymentGateway.setTrx(trx);
                String res = paymentGateway.send();
                if (Integer.parseInt(res) > 0)
                {
                    trx.setItem(item);
                    trx.setId_get(res);
                    trx.setStatus(TransactiontStatus.PENDING);
                    trx = txnService.create(trx);
                    return "redirect:" +((PayLineGateway)paymentGateway).getPaymentUrl() + res;
                }
                else
                {
                    throw new Exception(res);

                }

            }
        }

        return "redirect:../deal";
    }



    @RequestMapping(value = "/deal", method = RequestMethod.GET)
    public String dealList(
            Locale locale, Model uiModel) {
        uiModel.addAttribute("title",
                messageSource.getMessage("deal.list", null, locale));

        fillModel(uiModel,locale);
        return "website/customer/deal-list";
    }



    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProfile(
            Locale locale, Model uiModel) {
        uiModel.addAttribute("title",
                messageSource.getMessage("person.update", null, locale));

       Customer customer = customerService.findByUserName(Util.getCurrentUserName());
        if(customer == null) {
            throw new ResourceNotFoundException("User not found");
        } else{
            uiModel.addAttribute("customer",customer);
        }
        return "website/customer/edit";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editProfile( @ModelAttribute("customer") @Valid Customer customer,
                              Locale locale, Model uiModel,  BindingResult result){

        if (result.hasErrors()) {

            return "website/customer/edit";
        }
        customerService.edit(customer);

        return "redirect: panel";
    }



    private Model fillModel(Model uiModel, Locale locale) {

		String username = Util.getCurrentUserName();
		if (username != null) {
			
				Customer customer = customerService.findByUserName(username);
				uiModel.addAttribute("deals", dealService.findDealsByCustomer(customer));
				uiModel.addAttribute("customer", customer);	
		}

		uiModel.addAttribute("title", messageSource.getMessage(
				"website.customer.panel.title", null, locale));

		return uiModel;
	}

}