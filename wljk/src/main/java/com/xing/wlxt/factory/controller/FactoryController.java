package com.xing.wlxt.factory.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xing.wlxt.factory.entity.Factory;
import com.xing.wlxt.factory.service.FactoryService;


@Controller
@RequestMapping("/factory")
public class FactoryController {
	@Resource
	FactoryService factoryService;
	
	/**
	 * 获取所有的厂家信息
	 * @return
	 */
	@RequestMapping("/getFactory")
	@ResponseBody
	public List<Factory> getFactory(){
		List<Factory> factorys=factoryService.findFactory();
		return factorys;
	}
	
	/**
	 * 新增厂家信息
	 * @param factory
	 */
	@RequestMapping("/saveFactory")
	public void saveFactory(Factory factory){
		factoryService.saveFactory(factory);
	}
	
	/**
	 * 根据id获取厂家信息
	 * @param factoryId
	 * @return
	 */
	@RequestMapping("/findFactoryById")
	@ResponseBody
	public Factory findFactoryById(String factoryId){
		factoryId="12";
		return factoryService.findFactoryById(factoryId);
	}
	
	/**
	 * 根据id删除用户信息（其实就是修改厂家的状态，即：state值设置为0）
	 * @param factoryId
	 */
	@RequestMapping("/deleteFactoryById")
	public void deleteFactoryById(String factoryId){
		factoryId="12";
		factoryService.deleteFactoryById(factoryId);
	}
}
