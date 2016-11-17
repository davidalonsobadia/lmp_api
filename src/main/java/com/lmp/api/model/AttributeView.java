package com.lmp.api.model;

import java.io.Serializable;

public class AttributeView implements Serializable{
	private static final long serialVersionUID = 1L;

	String category;
	
	String subcategory;
	
	String attribute;
	
	Object value;
	
	public AttributeView(String cat, String sub, String attr){
		this.category = cat;
		this.subcategory = sub;
		this.attribute = attr;
	}
	
	public AttributeView(){}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
