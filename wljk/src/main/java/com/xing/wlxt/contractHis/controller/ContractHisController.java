package com.xing.wlxt.contractHis.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xing.wlxt.base.controller.BaseController;
import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contract.service.ContractService;
import com.xing.wlxt.contract.view.ContractVO;
import com.xing.wlxt.contractHis.service.ContractHisService;
import com.xing.wlxt.print.ContractPrint;

@RequestMapping("/cargo/contracthis")
@Controller
public class ContractHisController extends BaseController{
	
	@Resource
	ContractHisService contractHisService;
	
	@Resource
	ContractService contractService;
	
	/**
	 * 查询历史购销合同的记录
	 */
	@RequestMapping("/list.action")
	public String list(Model model){
		List<Contract> dataList=contractHisService.find(null);
		model.addAttribute("dataList", dataList);
		return "/cargo/contracthis/jContractHisList.jsp";
	}
	
	
	
	/**
	 * 购销合同归档（即：将时间长的购销合同记录归到历史表中）
	 * @return
	 */
	@RequestMapping("/pigeinhole.action")
	public String pigeinhole(@RequestParam("id")String[] ids){
		contractHisService.pigeinhole(ids);               //将购销合同归档
		return "redirect:/cargo/contracthis/list.action";
	}
	
	
	/**
	 * 购销合同取消归档
	 * @param id
	 * @return
	 */
	@RequestMapping("/pigeouthole.action")
	public String pigeouthole(@RequestParam("id")String[] ids){
		contractHisService.pigeouthole(ids);              //取消购销合同归档
		return "redirect:/cargo/contracthis/list.action";
	}
	/**
	 * 查看历史的购销合同信息，（包括历史购销合同中的货物信息和附件信息）
	 */
	@RequestMapping("/toview.action")
	public String toview(String id,Model model){
		ContractVO obj=contractHisService.view(id);
		model.addAttribute("obj", obj);
		return "/cargo/contracthis/jContractHisView.jsp";
	}
	
	/**
	 * 打印
	 * @throws Exception 
	 */
	@RequestMapping("/print.action")
	public void print(String id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ContractPrint cp=new ContractPrint();   //创建打印对象
		
		ContractVO obj=contractHisService.view(id);       //获取要打印的数据
		cp.print(obj,request.getSession().getServletContext().getRealPath("/"),response);  //打印购销合同
	}
}
