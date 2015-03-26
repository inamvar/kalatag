package com.semsari.api.version1;

import com.semsari.domain.ItemCategory;
import com.semsari.domain.SubCategory;
import com.semsari.service.ItemCategoryService;
import com.semsari.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * Created by Iman on 3/21/2015.
 */

@RestController
@RequestMapping("/api/v1/sub-category")
public class SubCategoryRestController {



    @Autowired
    SubCategoryService subCategoryService;

    @Autowired
    ItemCategoryService itemCategoryService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
    public SubCategory add(@RequestBody SubCategory category){

            ItemCategory parent = itemCategoryService.find(category.getId());
            category.setParent(parent);
            category.setId(0);
            category = subCategoryService.create(category);

        return category;
    }
}
