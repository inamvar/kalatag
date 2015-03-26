package com.semsari.controller;

import java.util.List;
import java.util.Locale;

import com.semsari.domain.DealLabel;
import com.semsari.domain.Item;
import com.semsari.domain.ItemStatus;
import com.semsari.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.semsari.domain.Comment;
import com.semsari.service.CommentService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
//	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private CommentService commentService;


    @Autowired
    private DealService dealService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping( method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		model.addAttribute("title", messageSource.getMessage("admin.home.title", null,locale) );
		List<Comment> comments = commentService.findAccepted(false);


        List<Item> items = dealService
                .find(0,null,null, null, ItemStatus.OFF, null, null, null, null, null, null, null, null,true,null,null,0,0);

        model.addAttribute("items", items);

		return "index";
	}
	
	
	@RequestMapping(value = "/acceptItem", method = RequestMethod.GET)
	public String acceptComment(@RequestParam("id") Integer id,
			@RequestParam("accept") Integer accept, Locale locale, Model uiModel) {
		ItemStatus status;
		if (accept == 1)
            status = ItemStatus.ON;
		else
            status = ItemStatus.OFF;

		dealService.changeStatus(status, id);
		return "redirect:";
	}
	
	@RequestMapping(value = "/acceptAllComments", method = RequestMethod.GET)
	public String acceptAllComments( Locale locale, Model uiModel) {
		
		commentService.acceptAllComments();
		return "redirect:";
	}

	
}
