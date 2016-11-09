package com.xing.wlxt.chart.controller;

import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xing.wlxt.base.controller.BaseController;
import com.xing.wlxt.common.springdao.SqlDao;
import com.xing.wlxt.utils.file.FileUtil;

/**
 *  该controller是为了生产统计的图形   
 */
@Controller
@RequestMapping("/stat/chart")
public class ChartController extends BaseController{
	
	@Resource
	SqlDao sqlDao;
	
	/**
	 * 生产厂家销售的饼状图
	 * 开发步骤：
	 * 	1.组织数据源
	 * 	2.拼接成xml
	 * 	3.创建一个文件txt格式，xml  工具类
	 *  4.转向对应目录下的index.html	
	 * @return
	 * @throws FileNotFoundException 
	 */
	@RequestMapping("/factorySale.action")
	public String factorySale(HttpServletRequest request,Model model) throws FileNotFoundException{
		String path=request.getSession().getServletContext().getRealPath("/");     //获取真实路径
		String dir="factorysale"; 
		
		String sql = "SELECT f.factory_na,cp.countnum FROM (SELECT factory_id,factory_na FROM factory_c) f RIGHT JOIN (SELECT factory_id,COUNT(*) AS countnum FROM contract_product_c GROUP BY factory_id ) cp ON f.factory_id=cp.factory_id";
		this.writeXml(path,dir,this.getPieXml(this.getData(sql)));
		
		return "stat/chart/jStat.jsp?forward="+dir;
	}
	
	/**
	 * 产品销售排行，畅销产品排行
	 * @param request
	 * @param model
	 * @return
	 * @throws FileNotFoundException
	 */
	@RequestMapping("/productSale.action")
	public String productSale(HttpServletRequest request,Model model) throws FileNotFoundException{
		String path = request.getSession().getServletContext().getRealPath("/");	//真实路径
		String dir = "productsale";
		
		String sql = "SELECT product_no,SUM(cnumber) AS sumnum FROM contract_product_c GROUP BY product_no ORDER BY SUM(cnumber)  DESC";
		this.writeXml(path, dir, this.getColumnAndLineXml(this.getData(sql)));
		return "stat/chart/jStat.jsp?forward="+dir;
	}
	
	/**
	 * 系统访问压力的曲线图
	 * @param request
	 * @param model
	 * @return
	 * @throws FileNotFoundException
	 */
	@RequestMapping("/onlineInfo.action")
	public String onlineInfo(HttpServletRequest request,Model model) throws FileNotFoundException{
		String path = request.getSession().getServletContext().getRealPath("/");	//真实路径
		String dir = "onlineinfo";
		
		String sql = "SELECT t.a1,p.countnum FROM (SELECT a1 FROM online_t) t LEFT JOIN (SELECT SUBSTRING(login_time,12,2) AS a1,COUNT(*) AS countnum FROM login_log_p GROUP BY SUBSTRING(login_time,12,2)) p ON t.a1=p.a1";
		this.writeXml(path, dir, this.getColumnAndLineXml(this.getData(sql)));
		
		return "stat/chart/jStat.jsp?forward="+dir;
	}
	
	//拼接饼形图xml
	public String getPieXml(List<String> dataList){
		//拼接数据位一个xml字符串
		StringBuffer sBuf = new StringBuffer();
		sBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sBuf.append("<pie>");
		for(int i=0;i<dataList.size();){
			//在for循环内部控制当前记录标识
			sBuf.append("  <slice title=\"").append(dataList.get(i++)).append("\">").append(dataList.get(i++)).append("</slice>");
		}
		sBuf.append("</pie>");
		
		return sBuf.toString();
	}
		
	//获得柱状图xml
	public String getColumnAndLineXml(List<String> dataList){
		StringBuffer sBuf = new StringBuffer();
		sBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sBuf.append("<chart>");
		sBuf.append("<series>");
		
		int xid = 0;			//对应标识
		for(int i=0;i<dataList.size();){
			sBuf.append("<value xid=\"").append(xid++).append("\">").append(dataList.get(i++)).append("</value>");
			i++;		//skip
		}
		sBuf.append("</series>");
		sBuf.append("<graphs>");
		sBuf.append("<graph gid=\"30\" color=\"#FFCC00\" gradient_fill_colors=\"#111111, #1A897C\">");
		
		xid = 0;
		for(int i=0;i<dataList.size();){
			i++;		//skip
			sBuf.append("<value xid=\"").append(xid++).append("\">").append(dataList.get(i++)).append("</value>");
		}
		
		sBuf.append("</graph>");
		sBuf.append("</graphs>");
		sBuf.append("</chart>");
		
		return sBuf.toString();
	}
	
	//写xml文本文件，格式utf-8
	private void writeXml(String path, String dir, String content) throws FileNotFoundException {
		//输出文件，如果目录下没有文件，直接创建；如果目录下文件存在，覆盖
		FileUtil fu=new FileUtil();
		fu.createTxt(path+"/stat/chart/"+dir, "data.xml", content, "utf-8");
	}

	//获取数据
	private List<String> getData(String sql) {
		return sqlDao.executeSQL(sql);
	}
}
