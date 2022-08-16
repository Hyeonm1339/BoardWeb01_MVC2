package com.springbook.biz.view.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	String HandleRequest(HttpServletRequest request, HttpServletResponse response);
}
