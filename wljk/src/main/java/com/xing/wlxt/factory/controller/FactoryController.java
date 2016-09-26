package com.xing.wlxt.factory.controller;

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
	
	@RequestMapping("/getFactory.action")
	@ResponseBody
	public Factory getFactory(){
		Factory factory=factoryService.findFactory();
		return factory;
	}
}
