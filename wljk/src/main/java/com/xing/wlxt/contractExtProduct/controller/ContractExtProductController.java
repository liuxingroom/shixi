
package com.xing.wlxt.contractExtProduct.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

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
		
		//获取货物下的附件信息
		List<ContractExtProduct> dataList=contractExtProductService.findExtByProductId(contractProductID);                
		model.addAttribute("dataList", dataList);
		
		List<Factory> factoryList=factoryService.findFactory();         //获取生产厂家信息
		model.addAttribute("factoryList", factoryList);
		// TODO 
	//	contractExtProductService.getCTypeList();              //准备分类下拉列表
		
		return "/cargo/contract/jExtCproductCreate";
	}
	
	/**
	 * 新增附件信息
	 */
	@RequestMapping("/insert.action")
	public String insert(ContractExtProduct extProduct, Model model){
		contractExtProductService.insert(extProduct);   //插入附件信息
		
		model.addAttribute("contractProductID", extProduct.getContractProductID());  //设置商品的id
		return "redirect:/cargo/extcproduct/tocreate.action";
	}
	
	/**
	 * 跳转到修改页面
	 */
	@RequestMapping("/toupdate.action")
	public String toupdate(Model model,String id){
		//获取要修改的附件信息
		ContractExtProduct obj=contractExtProductService.get(id);
		model.addAttribute("obj",obj);
		
		//获取生产厂家信息
		List<Factory>  factoryList=factoryService.findFactory();
		model.addAttribute("factoryList", factoryList);
		
		//获取分类下拉列表的信息
//		List<SysCode> ctypeList = extCproductService.getCtypeList();
//		model.addAttribute("ctypeList", ctypeList);
		
		return "/cargo/contract/jExtCproductUpdate";
	}
	
	/**
	 * 修改购销合同下的货物的附件信息
	 */
	@RequestMapping("/update.action")
	public String update(ContractExtProduct extProduct,Model model){
		//传递购销合同下的货物id
		model.addAttribute("contractProductID", extProduct.getContractProductID());
		
		//更新附件信息
		contractExtProductService.update(extProduct);
		
		return "redirect:/cargo/extcproduct/tocreate.action";
	}
	
	/**
	 * 根据附件的id来删除附件新信息
	 */
	@RequestMapping("/deleteById.action")
	public String deleteById(String id ,String contractProductID,Model model){
		//传递购销合同中的货物id
		model.addAttribute("contractProductID", contractProductID);
		
		//删除附件信息
		contractExtProductService.deleteById(id);
		return "redirect:/cargo/extcproduct/tocreate.action";
	}
}
