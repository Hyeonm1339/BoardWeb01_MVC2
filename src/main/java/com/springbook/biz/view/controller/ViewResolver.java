package com.springbook.biz.view.controller;

import lombok.Setter;

@Setter
public class ViewResolver {

	public String prefix;
	public String suffix;
	
	public String getView(String viewName) {
		return prefix +viewName + suffix;
	}

}
