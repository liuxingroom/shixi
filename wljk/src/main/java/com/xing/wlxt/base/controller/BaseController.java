package com.xing.wlxt.base.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 *  该类中的方法为所有Controller类所共有的
 */
public abstract class BaseController {
	/**
	 * 此方法用于日期的转换，如果未加，当页面日期格式转换错误，将报400错误，实际是因为此方法
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dataFormat=new SimpleDateFormat("yyyy-MM-dd");
		dataFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dataFormat, true));//true:允许输入空值，false:不能为空值 
	}
}
