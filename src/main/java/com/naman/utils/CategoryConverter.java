package com.naman.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.naman.Model.Category;
import com.naman.dao.CategoryDaoImpl;

@Named
@FacesConverter(value = "categoryConverter")
public class CategoryConverter implements Converter{
	
	@Autowired
	CategoryDaoImpl categoryDaoImpl;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		return categoryDaoImpl.getCategoryByName(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		// TODO Auto-generated method stub
		Category x = (Category) value;
		return x.getName();
	}

}
