package com.xing.wlxt.shipment.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xing.wlxt.base.controller.BaseController;

/**
 *	出货表对应的controller 打印出货表
 */
@Controller
@RequestMapping("/cargo/outproduct")
public class ShipmentController extends BaseController{
	
	/**
	 * 转向编辑页面
	 */
	@RequestMapping("/toedit.action")
	public String toedit(){
		return "/cargo/outproduct/jOutProduct.jsp";
	}
	
}
