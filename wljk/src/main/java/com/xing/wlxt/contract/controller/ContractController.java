package com.xing.wlxt.contract.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xing.wlxt.base.controller.BaseController;
import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contract.service.ContractService;

@Controller
@RequestMapping("/cargo/contract")
public class ContractController extends BaseController{
	
	@Resource
	ContractService contractService;
	
	/**
	 * 获取所有的购销合同的信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/list.action")
	public String list(Model model){
		List<Contract> dataList=contractService.find();
		model.addAttribute("dataList", dataList);
		return "/cargo/contract/jContractList";
	}
	
	/**
	 * 跳转到新增页面
	 * @return
	 */
	@RequestMapping("/tocreate.action")
	public String tocreate(){
		return "/cargo/contract/jContractCreate";
	}
	
	/**
	 * 获取数据并跳转到更新页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/toupdate.action")
	public String toupdate(Model model,String id){
		Contract obj=contractService.get(id);
		model.addAttribute("obj", obj);
		return "/cargo/contract/jContractUpdate";
	}
	
	/**
	 * 根据id集合来删除购销合同信息（删除成功后跳转到list集合页面）
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")String [] ids){
		contractService.delete(ids);
		return "redirect:/cargo/contract/list.action";
	}
}
