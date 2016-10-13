package com.xing.wlxt.contract.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
