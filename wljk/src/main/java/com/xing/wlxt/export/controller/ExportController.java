package com.xing.wlxt.export.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xing.wlxt.base.controller.BaseController;
import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.export.service.ExportService;

/**
 * 报运信息
 */
@Controller
@RequestMapping("/cargo/export")
public class ExportController extends BaseController{
	
	@Resource
	ExportService exportService;
	
	/***
	 * 购销合同的查询列表
	 */
	@RequestMapping("/contractList.action")
	public String contractList(Model model){
		
		List<Contract> dataList=exportService.getContractList(); //货物购销合同列表
		model.addAttribute("dataList", dataList);
		return "/cargo/export/jContractList";
	}
}
