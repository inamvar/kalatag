package com.semsari.controller;

import java.io.IOException;

import java.util.Locale;

import javax.validation.Valid;

import com.semsari.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.semsari.domain.Item;

import com.semsari.domain.ItemCategory;

import com.semsari.propertyeditor.ItemCategoryEditor;


import com.semsari.service.DealService;
import com.semsari.service.ItemCategoryService;


@Controller
@RequestMapping(value = "/admin/deal")
public class DealController {
	private static final Logger logger = LoggerFactory
			.getLogger(DealController.class);

	@Autowired
	DealService dealService;
	@Autowired
	ItemCategoryService categoryService;

    @Autowired
    CustomerService customerService;

	@Autowired
	private MessageSource messageSource;

	private @Autowired ItemCategoryEditor categoryEditor;


	@InitBinder
	public void iniBinder(WebDataBinder binder) {
		binder.registerCustomEditor(ItemCategory.class, this.categoryEditor);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Locale locale, Model uiModel) {

		uiModel.addAttribute("deals", dealService.findAll());
		uiModel.addAttribute("title",
				messageSource.getMessage("admin.menu.deals", null, locale));
		return "deal/list";
	}

	@RequestMapping(value = "/add/{categoryId}", method = RequestMethod.GET)
	public String addForm(@ModelAttribute("deal") @Valid Item item, @PathVariable Integer categoryId,
			BindingResult result, Locale locale, Model uiModel) {

		uiModel.addAttribute("title",
				messageSource.getMessage("deal.insert.message", null, locale));
		Item deal = new Item();
		uiModel.addAttribute("deal", deal);
		uiModel.addAttribute("categories", categoryService.findAll());
		uiModel.addAttribute("customers", customerService.findAll());
		return "deal/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute("deal") @Valid Item item,
			BindingResult result,
			@RequestParam("thumbnail") MultipartFile thumbnail,
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2,
            @RequestParam("image3") MultipartFile image3,
            @RequestParam("image4") MultipartFile image4,

            Locale locale,
			Model uiModel) {

		if (result.hasErrors()) {
			uiModel.addAttribute("categories", categoryService.findAll());
			uiModel.addAttribute("customer", customerService.findAll());
			uiModel.addAttribute("title", messageSource.getMessage(
					"deal.insert.message", null, locale));
			return "deal/add";
		}

		if (thumbnail != null) {

			try {
				item.setThumbnail(thumbnail.getBytes());
			} catch (IOException e) {

				e.printStackTrace();
			}
		}


        if (image1 != null) {

            try {
                item.setImage1(image1.getBytes());
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        if (image2 != null) {

            try {
                item.setImage2(image2.getBytes());
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        if (image3 != null) {

            try {
                item.setImage3(image3.getBytes());
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        if (image4 != null) {

            try {
                item.setImage4(image4.getBytes());
            } catch (IOException e) {

                e.printStackTrace();
            }
        }


		dealService.create(item);

		return "redirect:/admin/deal";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateFrom(@PathVariable Integer id, Locale locale,
			Model uiModel) {

		Item item = dealService.find(id);

		uiModel.addAttribute("deal", item);
		uiModel.addAttribute("title",
				messageSource.getMessage("deal.update.message", null, locale));
		uiModel.addAttribute("categories", categoryService.findAll());
		uiModel.addAttribute("customers", customerService.findAll());
		return "deal/update";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("deal") @Valid Item item,
			@RequestParam("files") MultipartFile[] files,
			@RequestParam("file") MultipartFile thumbnail,
			@PathVariable Integer id, BindingResult result, Model uiModel,
			Locale locale) {

		if (result.hasErrors()) {
			uiModel.addAttribute("categories", categoryService.findAll());
			uiModel.addAttribute("title", messageSource.getMessage(
					"deal.update.message", null, locale));
			return "deal/update/" + id;
		}


		Item originalItem = dealService.find(item.getId());


		if (originalItem.getThumbnail() != null)
			item.setThumbnail(originalItem.getThumbnail());


		if (thumbnail != null && thumbnail.getSize() > 0) {

			try {
				item.setThumbnail(thumbnail.getBytes());
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		dealService.update(item);
		return "redirect:/admin/deal";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {

		dealService.delete(id);

		return "redirect:/admin/deal";
	}

	@RequestMapping(value = "/detail/{id}")
	public String detail(@PathVariable int id, Model uiModel) {

		uiModel.addAttribute("deal", dealService.find(id));

		return "deal/detail";
	}


}
