package com.semsari.api.version1;

import java.util.ArrayList;
import java.util.List;

import com.semsari.domain.ItemStatus;
import com.semsari.domain.SubCategory;
import com.semsari.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.semsari.domain.Item;
import com.semsari.service.DealService;

@RestController
@RequestMapping("/api/v1")
public class DealRestController {

	@Autowired
	private DealService dealService;

    @Autowired
    private SubCategoryService subCategoryService;

	@RequestMapping(value = "/deals/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Item> getDeals() {
		List<Item> items = new ArrayList<Item>();
		try {

			
			items = dealService.findAll();
			for (Item d : items) {
				d.setThumbnail(null);
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return items;
	}



    @RequestMapping(value = "/deals/search", method = RequestMethod.POST, headers = "Accept=application/json")
    public List<Item> searchDeal(@RequestBody SearchObject search) {
        List<Item> items = new ArrayList<Item>();
        try {

            SubCategory category  = subCategoryService.find(search.getCategory());


            items = dealService.find(0,search.getQ(),null,category, ItemStatus.ON,null,null,null,null,null,null,null,null,true,null,null,search.getPageSize(),search.getPageNumber());
            for (Item d : items) {
                d.setThumbnail(null);
                d.setImage1(null);
                d.setImage2(null);
                d.setImage3(null);
                d.setImage4(null);
                d.setCustomer(null);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return items;
    }

	
	@RequestMapping(value = "/deals/find/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Item getDeal(@PathVariable("id") Integer id) {
		Item item = dealService.find(id);
		
		return item;
	}


}
