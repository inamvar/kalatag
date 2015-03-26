package com.semsari.propertyeditor;

import com.semsari.domain.ItemCategory;
import com.semsari.domain.SubCategory;
import com.semsari.service.ItemCategoryService;
import com.semsari.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

@Component
public class SubCategoryEditor extends PropertyEditorSupport {
	private @Autowired
    SubCategoryService subCategoryService;
	@Override
	public void setAsText(String text){
		SubCategory c = subCategoryService.find(Integer.valueOf(text));
		this.setValue(c);
	}

}
