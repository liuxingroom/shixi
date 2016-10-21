package com.xing.wlxt.contractProduct.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xing.wlxt.contractProduct.entity.ContractProduct;
import com.xing.wlxt.contractProduct.service.ContractProductService;
import com.xing.wlxt.factory.entity.Factory;
import com.xing.wlxt.factory.service.FactoryService;

/**
 * 购销合同下的货物
 *
 */
@Controller
@RequestMapping("/cargo/contractproduct")
public class ContractProductController {
	
	@Resource
	FactoryService factoryService;
	
	@Resource
	ContractProductService contractProductService;
	
	/**
	 * 转向货物信息的新增页面
	 */
	@RequestMapping("/tocreate.action")
	public String tocreate(Model model,String contractId){
		//传递购销合同的id信息
		model.addAttribute("contractId", contractId);
		
		//获取所有的生产厂家信息
		List<Factory> factoryList=factoryService.findFactory();
		model.addAttribute("factoryList", factoryList);
		
		//某个合同下的货物列表
		List<ContractProduct> dataList=contractProductService.findContractProductByContractId(contractId);
		model.addAttribute("dataList", dataList);
		return "/cargo/contract/jContractProductCreate";  //货物的新增页面
	}
	
	/**
	 * 新增货物信息
	 */
	@RequestMapping("/insert.action")
	public String insert(ContractProduct contractProduct,Model model){
		contractProductService.insert(contractProduct);
		
		//传递购销合同的id
		model.addAttribute("contractId", contractProduct.getContractID());
		return "redirect:/cargo/contractproduct/tocreate.action";
		
	}
	
	/**
	 * 转向货物修改页面
	 */
	@RequestMapping("/toupdate.action")
	public String toupdate(Model model,String id){
		//通过货物的id来获取货物信息
		ContractProduct obj=contractProductService.findContractProductById(id);
		model.addAttribute("obj", obj);
		
		//获取所有的生产厂家信息
		List<Factory> factoryList=factoryService.findFactory();
		model.addAttribute("factoryList", factoryList);
		return "/cargo/contract/jContractProductUpdate";
	}
	
	/**
	 * 更新货物信息
	 */
	@RequestMapping("/update.action")
	public String update(ContractProduct contractProduct,Model model){
		//根据货物id来更新货物信息
		contractProductService.update(contractProduct);
		
		//传递购销合同的id信息
		model.addAttribute("contractId", contractProduct.getContractID());
		return "redirect:/cargo/contractproduct/tocreate.action";
	}
	
	/**
	 * 根据货物的id来删除货物信息
	 * @return
	 */
	@RequestMapping("/deleteById.action")
	public String deleteById(String id, String contractId, Model model){
		//根据id来删除货物信息
		contractProductService.deleteById(id);
		//传递购销合同的id
		model.addAttribute("contractId", contractId);
		return "redirect:/cargo/contractproduct/tocreate.action";
	}
	
}
