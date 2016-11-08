package com.xing.wlxt.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xing.wlxt.base.controller.BaseController;

@Controller
@RequestMapping("/ws/export")
public class WSExportController extends BaseController{
	
	@RequestMapping("/toedit.action")
	public String toedit(){
		return "/ws/export/ajaxExport";
	}
}
