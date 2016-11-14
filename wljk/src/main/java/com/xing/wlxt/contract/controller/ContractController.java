package com.xing.wlxt.contract.controller;

import java.text.ParseException;
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
import com.xing.wlxt.print.ContractPrint;

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
		List<Contract> dataList=contractService.find(null);
		model.addAttribute("dataList", dataList);
		return "/cargo/contract/jContractList.jsp";
	}
	
	/**
	 * 查看购销合同的详细信息（包含购销合同下的货物，和附件信息）
	 */
	@RequestMapping("/toview.action")
	public String toview(String id,Model model){
		ContractVO obj=contractService.view(id);
		model.addAttribute("obj", obj);
		return "/cargo/contract/jContractView.jsp";
	}
	
	/**
	 * 跳转到新增页面
	 * @return
	 */
	@RequestMapping("/tocreate.action")
	public String tocreate(){
		return "/cargo/contract/jContractCreate.jsp";
	}
	
	/**
	 * 插入新的购销合同的信息
	 */
	@RequestMapping("/insert.action")
	public String insert(Contract contract){
		contractService.insert(contract);
		return "redirect:/cargo/contract/list.action";
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
		return "/cargo/contract/jContractUpdate.jsp";
	}
	
	/**
	 * 修改购销合同
	 */
	@RequestMapping("/update.action")
	public String update(Contract contract){
		contractService.update(contract);
		return "redirect:/cargo/contract/list.action";
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
	
	/**
	 * 上报购销合同
	 */
	@RequestMapping("/submit.action")
	public String submit(@RequestParam("id")String [] ids){
		contractService.submit(ids);
		return "redirect:/cargo/contract/list.action";
	}
	
	/**
	 * 取消购销合同
	 */
	@RequestMapping("/cancel.action")
	public String cancel(@RequestParam("id")String [] ids){
		contractService.cancel(ids);
		return "redirect:/cargo/contract/list.action";
	}
	
	/**
	 * 打印
	 * @throws Exception 
	 */
	@RequestMapping("/print.action")
	public void print(String id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ContractPrint cp=new ContractPrint();   //创建打印对象
		
		
		ContractVO obj=contractService.view(id);       //获取要打印的数据
		cp.print(obj,request.getSession().getServletContext().getRealPath("/"),response);  //打印购销合同
	}
}
