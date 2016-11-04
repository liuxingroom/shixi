package com.xing.wlxt.packingList.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xing.wlxt.base.controller.BaseController;
import com.xing.wlxt.packingList.entity.PackingList;
import com.xing.wlxt.packingList.service.PackingListService;

@RequestMapping("/cargo/packinglist")
@Controller
public class PackingListController extends BaseController{
	
	@Resource
	PackingListService packingListService;
	
	
	/**
	 *	查看装箱单的集合页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/list.action")
	public String list(Model model){
		List<PackingList> dataList=packingListService.find(null);
		model.addAttribute("dataList", dataList);
		return "/cargo/packinglist/jPackingListList";
	}
	
	
	@RequestMapping("/tocreate.action")
	public String tocreate(@RequestParam("id")String [] ids,Model model){
		
		// TODO
		//携带出口报运的id集合	//显示装箱和报运的关系
			//	model.addAttribute("divData", packingListService.getDivDataCreate(id));
		return "/cargo/packinglist/jPackingListCreate";
	}
	
	/**
	 * 查看装箱单
	 */
	@RequestMapping("/toview.action")
	public String toview(String id,Model model){
		PackingList obj=packingListService.get(id);                   //根据id来获取装箱单信息
		model.addAttribute("obj", obj);
		
		// TODO
		//model.addAttribute("divData", packingListService.getDivDataView(obj.getExportNos().split("\\|")));
		return "/cargo/packinglist/jPackingListView";
	}
	
	/**
	 * 转向修改装箱单
	 */
	@RequestMapping("/toupdate.action")
	public String toupdate(String id,Model model){
		PackingList obj=packingListService.get(id);         //根据id来获取装箱单
		model.addAttribute("obj", obj);
		
		
		//TODO
//		String _s = packingListService.getDivDataUpdate(obj.getExportIds().split("\\|"), obj.getExportNos().split("\\|"));
		return "/cargo/packinglist/jPackingListUpdate";
	}
	
	/**
	 * 修改装箱单信息
	 */
	@RequestMapping("/update.action")
	public String update(PackingList packingList){
		packingListService.update(packingList);             //修改装箱单信息

		return "redirect:/cargo/packinglist/list.action";
	}
	
	/**
	 * 删除装箱单
	 */
	@RequestMapping("/delete.action")
	public String delete(@RequestParam("id")String [] ids){
		packingListService.delete(ids);
		return "redirect:/cargo/packinglist/list.action";
	}
	
	/**
	 * 上报装箱单
	 */
	@RequestMapping("/submit.action")
	public String submit(String id){
		packingListService.submit(id);      //上报
		return "redirect:/cargo/packinglist/list.action";
	}
	
	/**
	 * 装箱单上报取消
	 * @return
	 */
	@RequestMapping("/cancel.action")
	public String cancel(String id){
		packingListService.cancel(id);      //取消上报
		return "redirect:/cargo/packinglist/list.action";
	}
}
