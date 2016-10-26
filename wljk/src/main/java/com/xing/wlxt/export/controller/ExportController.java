package com.xing.wlxt.export.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xing.wlxt.base.controller.BaseController;
import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.export.entity.Export;
import com.xing.wlxt.export.service.ExportService;

/**
 * 报运信息
 */
@Controller
@RequestMapping("/cargo/export")
public class ExportController extends BaseController{
	
	@Resource
	ExportService exportService;
	
	/**
	 * 查询报运表中的信息
	 */
	@RequestMapping("/list.action")
	public String list(Model model){
		
		List<Export> dataList=exportService.find(null); //查询报运表中的信息
		model.addAttribute("dataList", dataList);
		return "/cargo/export/jExportList";
	}
	
	/**
	 * 查看报运信息
	 */
	@RequestMapping("/toview.action")
	public String toview(){
		
		return "";
	}
	
	/**
	 * 转向报运的修改页面
	 */
	@RequestMapping("/toupdate.action")
	public String toupdate(String id ,Model model){
		Export obj=exportService.get(id);  //通过报运表id来获取报运表信息
		model.addAttribute("obj", obj);
		
		return "/cargo/export/jExportUpdate";
	}
	
	
	/**
	 * 修改报运单的信息
	 */
	@RequestMapping("/update.action")
	public String update(Export export){
		exportService.update(export);
		return "redirect:/cargo/export/list.action";
	}
	
	/***
	 * 购销合同的查询列表
	 */
	@RequestMapping("/contractList.action")
	public String contractList(Model model){
		
		List<Contract> dataList=exportService.getContractList(); //货物购销合同列表
		model.addAttribute("dataList", dataList);
		return "/cargo/export/jContractList";
	}
	
	/**
	 * 将购销合同中的货物报运
	 * @return
	 */
	@RequestMapping("/insert.action")
	public String insert(){
		
		return "redirect:/";
	}
	
	/**
	 * 根据报运id来删除报运新
	 */
	@RequestMapping("/delete.action")
	public String delete(@RequestParam("id")String [] ids){
		exportService.delete(ids);  //根据报运单id来删除报运信息
		return "redirect:/cargo/export/list.action";
	}
	
	
}
