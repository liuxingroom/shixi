package com.xing.wlxt.contractExtProduct.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xing.wlxt.contractExtProduct.entity.ContractExtProduct;
import com.xing.wlxt.contractExtProduct.service.ContractExtProductService;
import com.xing.wlxt.factory.entity.Factory;
import com.xing.wlxt.factory.service.FactoryService;

/**
 * 购销合同中货物下的附件
 *
 */
@Controller
@RequestMapping("/cargo/extcproduct")
public class ContractExtProductController {
	
	@Resource
	ContractExtProductService contractExtProductService;
	
	@Resource
	FactoryService factoryService;
	
	/**
	 * 转向附件的新增页面
	 */
	@RequestMapping("/tocreate.action")
	public String tocreate(String contractProductID,Model model){
		model.addAttribute("contractProductID", contractProductID);    //传递货物id
		
		List<ContractExtProduct> dataList=contractExtProductService.findExtByProductId(contractProductID);                //获取货物下的附件信息
		model.addAttribute("dataList", dataList);
		
		List<Factory> factoryList=factoryService.findFactory();         //获取生产厂家信息
		model.addAttribute("factoryList", factoryList);
		
	//	contractExtProductService.getCTypeList();              //准备分类下拉列表
		
		return "/cargo/contract/jExtCproductCreate";
	}

}
