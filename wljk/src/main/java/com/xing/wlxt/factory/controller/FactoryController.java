package com.xing.wlxt.factory.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xing.wlxt.base.controller.BaseController;
import com.xing.wlxt.factory.entity.Factory;
import com.xing.wlxt.factory.service.FactoryService;


@Controller
@RequestMapping("/basicinfo/factory")
public class FactoryController extends BaseController{
	@Resource
	FactoryService factoryService;
	
	/**
	 * 获取所有的厂家信息
	 * @return
	 */
	@RequestMapping("/findFactory.action")
	public String findFactory(Model model){
		List<Factory> factorys=factoryService.findFactory();
		model.addAttribute("dataList", factorys);
		return "/basicinfo/factory/jFactoryList.jsp";
	}
	
	/**
	 * 查看生产厂家信息
	 */
	@RequestMapping("/toview.action")
	public String toview(Model model,String id){
		Factory obj=factoryService.findFactoryById(id);
		model.addAttribute("obj",obj);
		return "/basicinfo/factory/jFactoryView.jsp";
	}
	
	/**
	 * 转向新增厂家的页面
	 * @param factory
	 */
	@RequestMapping("/tocreate.action")
	public String tocreate(){
		return "/basicinfo/factory/jFactoryCreate.jsp";
	}
	
	/**
	 * 保存生产厂家(保存后跳转到生产厂家集合页面)
	 */
	@RequestMapping("/insert.action")
	public String insert(Factory factory){
		factoryService.insert(factory);
		return "redirect:/basicinfo/factory/findFactory.action";
	}
	/**
	 * 根据id获取厂家信息
	 * @param factoryId
	 * @return
	 */
	@RequestMapping("/findFactoryById")
	@ResponseBody
	public Factory findFactoryById(String id){
		return factoryService.findFactoryById(id);
	}
	
	/**
	 * 根据id删除一条生产厂家信息
	 * @param factoryId
	 */
	@RequestMapping("/deleteFactoryById.action")
	public String deleteFactoryById(String id){
		//删除成功后跳转到查询页面
		factoryService.deleteFactoryById(id);
	    return "redirect:/basicinfo/factory/findFactory.action"; 
	}
	
	/**
	 * 根据id集合删除多条生产厂家信息
	 */
	@RequestMapping("/delete.action")
	public String delete(@RequestParam("id")String[] ids){
		factoryService.delete(ids);
		return "redirect:/basicinfo/factory/findFactory.action";
	}
	/**
	 * 修改生产厂家
	 */
	@RequestMapping("/toupdate.action")
	public String toupdate(String id,Model model){
		Factory obj=factoryService.findFactoryById(id);
		model.addAttribute("obj", obj);
		return "/basicinfo/factory/jFactoryUpdate.jsp";
	}
	
	/**
	 * 修改生产厂家(修改完成后跳转到生产厂家集合页面)
	 */
	@RequestMapping("/update.action")
	public String update(Factory factory){
		factoryService.update(factory);
		return "redirect:/basicinfo/factory/findFactory.action";
	}
	
	
	/**
	 * 启用该生产厂家
	 */
	@RequestMapping("/start.action")
	public String start(String  id){
		factoryService.start(id);
		return "redirect:/basicinfo/factory/findFactory.action";
	}
	
	/**
	 * 停用该生产厂家
	 */
	@RequestMapping("/stop.action")
	public String stop(String id){
		factoryService.stop(id);
		return "redirect:/basicinfo/factory/findFactory.action";
	}
}
