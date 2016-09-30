package com.xing.wlxt.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	/**
	 * 跳转到到登录页面
	 * @return
	 */
	@RequestMapping("/login.action")
	public String login(){
		
		return "index";
	}
	
	/**
	 * 跳转到系统主页面
	 * @return
	 */
	@RequestMapping("/fmain.action")
	public String fmain(){
		return "home/fmain";
	}
	
	/**
	 * 加载系统顶部页面
	 * @return
	 */
	@RequestMapping("/title.action")
	public String title(){
		return "home/title";
	}

	/**
	 * 加载系统左侧菜单
	 * @return
	 */
	@RequestMapping("/left.action")
	public String left(){
		return "home/left";
	}
}
