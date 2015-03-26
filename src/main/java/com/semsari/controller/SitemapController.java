package com.semsari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.semsari.domain.Item;
import com.semsari.domain.ItemStatus;
import com.semsari.service.DealService;
import com.semsari.util.XmlUrl;
import com.semsari.util.XmlUrlSet;

@Controller
public class SitemapController {

	@Autowired
	DealService dealService;

	@RequestMapping(value = "/sitemap.xml", method = RequestMethod.GET)
	@ResponseBody
	public XmlUrlSet main() {
		XmlUrlSet xmlUrlSet = new XmlUrlSet();
		create(xmlUrlSet, "", XmlUrl.Priority.HIGH);

		create(xmlUrlSet, "/login", XmlUrl.Priority.MEDIUM);
		create(xmlUrlSet, "/register", XmlUrl.Priority.MEDIUM);
        create(xmlUrlSet, "/contact-us", XmlUrl.Priority.MEDIUM);
        create(xmlUrlSet, "/how-to", XmlUrl.Priority.MEDIUM);
        create(xmlUrlSet, "/faq", XmlUrl.Priority.MEDIUM);
        create(xmlUrlSet, "/terms", XmlUrl.Priority.MEDIUM);
        create(xmlUrlSet, "/search?category=0", XmlUrl.Priority.MEDIUM);
		List<Item> items = dealService.findDealsByStatus(ItemStatus.ON);
		if (items != null && items.size() > 0) {
			for (Item item : items) {
				create(xmlUrlSet, "/detail?deal=" + item.getId(), XmlUrl.Priority.MEDIUM);
			}
		}
		return xmlUrlSet;
	}

	private void create(XmlUrlSet xmlUrlSet, String link,
			XmlUrl.Priority priority) {
		xmlUrlSet.addUrl(new XmlUrl("http://www.kalatag.com" + link, priority));
	}

}
