package com.xing.wlxt.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
	
	/**
	 * 加载起始首页（即系统首页）
	 * @return
	 */
	@RequestMapping("/main.action")
	public String main(){
		return "home/olmsgList";			//首页转向留言板
	}
	
	/**
	 * 加载货运管理主页面
	 */
	@RequestMapping("/cargoMain.action")
	public String cargoMain(){
		return "cargo/main";
	}
	
	/**
	 * 加载货运管理左侧页面
	 * @return
	 */
	@RequestMapping("/cargoLeft.action")
	public String cargoLeft(){
		return "cargo/left";
	}
	
	/**
	 * 加载统计分析主页面
	 */
	@RequestMapping("/statMain.action")
	public String statMain(){
		return "stat/main";
	}
	
	/**
	 * 加载统计分析的左侧菜单
	 */
	@RequestMapping("/statLeft.action")
	public String statLeft(){
		return "stat/left";
	}
	
	/**
	 * 加载基础信息的主页面
	 */
	@RequestMapping("/baseinfoMain.action")
	public String baseinfoMain(){
		return "baseinfo/main";
	}
	
	/**
	 * 加载基础信息的左侧页面
	 */
	@RequestMapping("/baseinfoLeft.action")
	public String baseinfoLeft(){
		return "baseinfo/left";
	}
	
	/**
	 * 加载系统管理模块的主页面
	 */
	@RequestMapping("/sysadminMain")
	public String sysadminMain(){
		return "sysadmin/main";
	}
	
	/**
	 * 加载系统管理模块的左侧页面
	 */
	@RequestMapping("/sysadminLeft")
	public String sysadminLeft(){
		return "/sysadmin/left";
	}
}

