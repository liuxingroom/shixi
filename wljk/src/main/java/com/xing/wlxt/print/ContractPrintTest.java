package com.xing.wlxt.print;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.util.xml.SimpleSaxErrorHandler;

import com.mysql.jdbc.Util;
import com.xing.wlxt.contract.view.ContractVO;
import com.xing.wlxt.contractProduct.view.ContractProductVO;
import com.xing.wlxt.utils.UtilFuns;
import com.xing.wlxt.utils.file.PoiUtil;

public class ContractPrintTest {
	
	public void print(ContractVO contract,String path,HttpServletRequest request) throws Exception{
		//获取购销合同中的货物信息
		List<ContractProductVO> oList=contract.getContractProduct();
		ContractProductVO oProduct;            //定义货物对象
		//获取工具类对象
		UtilFuns utilFuns=new UtilFuns();
		String tempXlsFile=path+"/make/xlsprint/tCONTRACT.xls";   //加载模板对象
		
		List<HashMap<String, String>> pageList=new ArrayList<HashMap<String,String>>();
		HashMap<String, String> pageMap=null;
		String oldFactory="";
		
		String stars="";
		for(int i=0;i<contract.getImportNum();i++){    //获取该购销合同的重要程度
			stars+="★";
		}

//----------------------准备数据开始------------------------------------------		
		for(int i=0;i<oList.size();i++){
			oProduct=oList.get(i);        //获取一个货物信息
			pageMap=new HashMap<String, String>();
			pageMap.put("offeror", "收购方: "+contract.getOfferor());
			pageMap.put("Offeror", "收 购 方：" + contract.getOfferor());
			pageMap.put("Factory", "生产工厂：" + oProduct.getFactoryID().getFactoryNA());
			pageMap.put("ContractNo", "合 同 号：" + contract.getContractNO());
			pageMap.put("Contacts", "联 系 人：" + oProduct.getFactoryID().getContacts());
			pageMap.put("SigningDate", "签单日期："+UtilFuns.formatDateTimeCN(UtilFuns.dateTimeFormat(contract.getSigningDate())));
			pageMap.put("Phone", "电    话：" + UtilFuns.convertNull(oProduct.getFactoryID().getPhone()));
			pageMap.put("InputBy", "制单：" + contract.getInputBy());
			pageMap.put("CheckBy", "审单："+ utilFuns.fixSpaceStr(contract.getCheckBy(),26)+"验货员："+utilFuns.convertNull(contract.getInspector()));
			pageMap.put("Remark", "  "+UtilFuns.convertNull(contract.getRemark()));
			pageMap.put("Crequest", "  "+UtilFuns.convertNull(contract.getCrequest()));
			
			pageMap.put("ProductImage", oProduct.getProductImage());
			pageMap.put("ProductDesc", oProduct.getProductDesc());
			pageMap.put("Cnumber", String.valueOf(oProduct.getcNumber().doubleValue()));
			
			if(oProduct.getPackingUnit().equals("PCS")){
				pageMap.put("PackingUnit", "只");
			}else if(oProduct.getPackingUnit().equals("SETS")){
				pageMap.put("PackingUnit", "套");
			}
			pageMap.put("Price", String.valueOf(oProduct.getPrice().doubleValue()));
			
			oldFactory=oProduct.getFactoryID().getFactoryNA();      //用于记录第一个货物的生产厂家信息  
			
			
			//如果要将两个货物信息打印到一张表中  需要的条件1.打印模板为两个    2.第一个货物选中后还存在下一个货物信息   3.第一个货物的生产厂家信息和第二个生产厂家信息相同
			
			if(contract.getPrintStyle()=='2'){      //如果打印的模板是一张两个
				i++;
				if(i<oList.size()){           //判断下一个数据不是最后一个
					if(oProduct.getFactoryID().getFactoryNA().equals(oldFactory)){  //如果这个货物的生产厂家和上一个货物的生产厂家的信息相同
						pageMap.put("ProductImage2", oProduct.getProductImage());
						pageMap.put("ProductDesc2", oProduct.getProductDesc());
						pageMap.put("Cnumber2", String.valueOf(oProduct.getcNumber().doubleValue()));
						if(oProduct.getPackingUnit().equals("PCS")){
							pageMap.put("PackingUnit2", "只");
						}else if(oProduct.getPackingUnit().equals("SETS")){
							pageMap.put("PackingUnit2", "套");
						}						
						pageMap.put("Price2", String.valueOf(oProduct.getPrice().doubleValue()));
						//pageMap.put("Amount2", String.valueOf(oProduct.getAmount().doubleValue()));			//在excel中金额采用公式，所以无需准备数据
						pageMap.put("ProductNo2", oProduct.getProductNo());
					}else{//如果厂家不同
						i--;   
					}
				}else{//如果下一个货物信息不存在
					pageMap.put("ProudctNo2", null);
				}
			}
			pageMap.put("ContractDesc", stars+" 货物描述");
			pageList.add(pageMap);
		}
		
//----------------------------准备数据结束--------------------------------------		
		
		//--------------------------打印开始-------------------------------------
		
		PoiUtil poiUtil=new PoiUtil();             //创建打印的工具类对象
		
	//	HSSFWorkbook wb=new HSSFWorkbook();        //创建Excel对象
		//创建一个Excel对象   改对象加载一个模板（即该对象是根据模板开发的）
		HSSFWorkbook wb=new HSSFWorkbook(new FileInputStream(tempXlsFile));
		
		HSSFFont defaultFont10=this.defaultFont10(wb);                           //设置字体	
		HSSFFont defaultFont12=this.defaultFont12(wb);                           //设置字体	
		HSSFFont blackFont = this.blackFont12(wb);			                    //设置字体
		Short rmb2Format=this.rmb2Format(wb);								    //设置格式
		Short rmb4Format=this.rmb4Format(wb);                                    //设置格式
		
		int curRow=0;      //设置excel的函数  初始值为零
		
		CellRangeAddress region=null;  //合并单元格对象
		
		HSSFSheet sheet=wb.getSheetAt(0);   //选着第一个工作簿
		/**
		 * 在打印的报表中添加图片或添加划线时都会用到该对象
		 */
		HSSFPatriarch patriarch=sheet.createDrawingPatriarch();
		HSSFRow nRow=null;     //定义一个行对象
		HSSFCell nCell=null;   //定义一个列对象
		
		wb.setSheetName(0, "购销合同");        //为第一个工作薄设置名称
		Map<String, String> printMap=null;
		
		
		
		for(int p=0;p<pageList.size();p++){
			printMap=pageList.get(p);         //获取第一个map集合
			
			if(p>0){//从第二个开始设置分页符
				sheet.setRowBreak(curRow++);
			}
			
			//设置logo图片
			this.setPicture(wb, patriarch, path+"/make/xlsprint/logo.jpg", curRow, 2, curRow+4, 2);
			
			//header
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(21);
			
			nCell=nRow.createCell(3);
			nCell.setCellValue("SHAANXI");
			nCell.setCellStyle(headStyle(wb));
			
			//header
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(41);
			
			nCell=nRow.createCell(3);
			nCell.setCellValue("    JK INTERNATIONAL ");
			nCell.setCellStyle(tipStyle(wb));
			
			//header
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(20);
			
			nCell=nRow.createCell(1);
			nCell.setCellValue("                 西经济技术开发区西城一路27号无迪大厦19楼");
			nCell.setCellStyle(addressStyle(wb));
			
			//header
			nCell=nRow.createCell(6);
			nCell.setCellValue(" CO., LTD.");
			nCell.setCellStyle(ltdStyle(wb));
			
			//header
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(15);
			
			nCell=nRow.createCell(1);
			nCell.setCellValue("                   TEL: 0086-29-86339371  FAX: 0086-29-86303310               E-MAIL: ijackix@glass.cn");
			nCell.setCellStyle(telStyle(wb));
			
			//line(设置划线)
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(7);
			
			//划线
			this.setLine(wb, patriarch, curRow, 2,curRow, 8);
		
			//header
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints((short)30);
			
			nCell=nRow.createCell(4);
			nCell.setCellValue("    购   销   合   同");
			nCell.setCellStyle(titleStyle(wb));
			
			
			//offeror
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(20);
			
			nCell=nRow.createCell(1);
			nCell.setCellValue(printMap.get("offeror"));
			nCell.setCellStyle(this.titlev12(wb,blackFont));
			
			//factory
			nCell=nRow.createCell(5);
			nCell.setCellValue(printMap.get("Factory"));
			nCell.setCellStyle(this.titlev12(wb, blackFont));
		
			//contractNo
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(20);
			
			nCell=nRow.createCell(1);
			nCell.setCellValue(printMap.get("ContractNo"));
			nCell.setCellStyle(this.titlev12(wb, blackFont));
			
			//Contacts
			nCell=nRow.createCell(5);
			nCell.setCellValue(printMap.get("Contacts"));
			nCell.setCellStyle(this.titlev12(wb, blackFont));
			
			//SigningDate
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(20);
			
			nCell =nRow.createCell(1);
			nCell.setCellValue(printMap.get("SigningDate"));
			nCell.setCellStyle(this.titlev12(wb, blackFont));
			
			//phone
			nCell=nRow.createCell(5);
			nCell.setCellValue(printMap.get("Phone"));
			nCell.setCellStyle(this.titlev12(wb, blackFont));
			
			
			//inputNum
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(24);

//注意在使用合并单元格时 设置完第一行的样式和内容后     其他的行只需要设置样式即可
			//将第10行的第一列和第三列的单元格合并
			region=new CellRangeAddress(curRow-1, curRow-1, 1, 3);
			sheet.addMergedRegion(region);
			
			nCell=nRow.createCell(1);
			nCell.setCellValue("产品");
			nCell.setCellStyle(this.thStyle(wb));
			
			nCell=nRow.createCell(2);
			nCell.setCellStyle(this.thStyle(wb));
			
			nCell=nRow.createCell(3);
			nCell.setCellStyle(this.thStyle(wb));
			
			nCell=nRow.createCell(4);
			nCell.setCellValue(printMap.get("ContractDesc"));
			nCell.setCellStyle(thStyle(wb));
			
			region=new CellRangeAddress(curRow-1, curRow-1, 5, 6);         //纵向合并单元格
			sheet.addMergedRegion(region);
			
			nCell=nRow.createCell(5);
			nCell.setCellValue("数量");
			nCell.setCellStyle(thStyle(wb));
			
			nCell=nRow.createCell(6);
			nCell.setCellStyle(this.thStyle(wb));
			
			nCell=nRow.createCell(7);
			nCell.setCellValue("单价");
			nCell.setCellStyle(thStyle(wb));
			
			nCell=nRow.createCell(8);
			nCell.setCellValue("总金额");
			nCell.setCellStyle(thStyle(wb));
			
			//设置货物信息
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(96);
			
			//合并单元格
			region=new CellRangeAddress(curRow-1, curRow-1, 1, 3);         
			sheet.addMergedRegion(region);
			
			//插入图片(注意图片所在的位置：第一行=最后一行+1)
			this.setPicture(wb, patriarch, path+"/ufiles/jquery/"+printMap.get("ProductImage"), curRow-1, curRow, 1, 3);
			
			nCell=nRow.createCell(2);
			nCell.setCellStyle(this.notehv10_BorderThin(wb,defaultFont10));
			
			nCell=nRow.createCell(3);
			nCell.setCellStyle(this.notehv10_BorderThin(wb, defaultFont10));
			
			//productDesc
			//纵向合并单元格
			region=new CellRangeAddress(curRow-1, curRow, 4, 4);
			
			nCell=nRow.createCell(4);
			nCell.setCellValue(printMap.get("ProductDesc"));
			nCell.setCellStyle(this.notehv10_BorderThin(wb, defaultFont10));
			
			
			//cNumber
			region=new CellRangeAddress(curRow-1, curRow, 5, 5);
			sheet.addMergedRegion(region);
			
			nCell=nRow.createCell(5);
			nCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			nCell.setCellValue(Double.parseDouble(printMap.get("Cnumber")));
			nCell.setCellStyle(this.notehv10_BorderThin(wb, defaultFont10));
			
			//Unit（单位）
			region=new CellRangeAddress(curRow-1, curRow, 6, 6);      //纵向合并单元格
			sheet.addMergedRegion(region);
			
			nCell=nRow.createCell(6);
			nCell.setCellValue(printMap.get("PackingUnit"));
			nCell.setCellStyle(this.moneyrv10_BorderThin(wb,defaultFont10,rmb4Format));
			
			
			//price
			region=new CellRangeAddress(curRow-1, curRow-1, 7, 7);
			sheet.addMergedRegion(region);
			
			nCell=nRow.createCell(7);
			nCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			nCell.setCellValue(Double.parseDouble(printMap.get("Price")));
			nCell.setCellStyle(this.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));

	
			//Amount(使用公式计算如：F11*H11)
			region=new CellRangeAddress(curRow-1, curRow, 8, 8);     //纵向合并单元格
			sheet.addMergedRegion(region);
			nCell=nRow.createCell(8);
			if(StringUtils.isNotEmpty(printMap.get("Cnumber")) && StringUtils.isNotEmpty(printMap.get("Price"))){
				nCell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
				nCell.setCellFormula("F"+String.valueOf(curRow)+"*H"+String.valueOf(curRow));
			}
			nCell.setCellStyle(this.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));
			
			
			//productNo
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(24);
			
			region=new CellRangeAddress(curRow-1, curRow-1, 1, 3);
			sheet.addMergedRegion(region);
			
			nCell=nRow.createCell(1);
			nCell.setCellValue(printMap.get("ProductNo"));
			nCell.setCellStyle(this.notehv10_BorderThin(wb, defaultFont10));
			
			
			for(int j=2;j<9;j++){
				nCell=nRow.createCell(j);
				nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));
			}
			
			
			if((contract.getPrintStyle()+"").equals("2")  && UtilFuns.isNotEmpty(printMap.get("ProductNo2"))){
				nRow = sheet.createRow(curRow++);
				nRow.setHeightInPoints(96);
				
				region = new CellRangeAddress(curRow-1, curRow-1, 1, 3);	//纵向合并单元格 
				sheet.addMergedRegion(region);
				
				//插入产品图片
				if(UtilFuns.isNotEmpty(printMap.get("ProductImage2"))){
					System.out.println(printMap.get("ProductImage2"));
					poiUtil.setPicture(wb, patriarch, path+"ufiles/jquery/"+printMap.get("ProductImage2"), curRow-1, 1, curRow, 3);
				}
				
				//ProductDesc
				region = new CellRangeAddress(curRow-1, curRow, 4, 4);	//纵向合并单元格 
				sheet.addMergedRegion(region);
				
				nCell = nRow.createCell(4);
				nCell.setCellValue(printMap.get("ProductDesc2"));
				nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));		
				
				//Cnumber
				region = new CellRangeAddress(curRow-1, curRow, 5, 5);	//纵向合并单元格 
				sheet.addMergedRegion(region);
				
				nCell = nRow.createCell(5);
				nCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				nCell.setCellValue(Double.parseDouble(printMap.get("Cnumber2")));
				nCell.setCellStyle(poiUtil.numberrv10_BorderThin(wb, defaultFont10));	
				
				//Unit
				region = new CellRangeAddress(curRow-1, curRow, 6, 6);	//纵向合并单元格 
				sheet.addMergedRegion(region);
				
				nCell = nRow.createCell(6);
				nCell.setCellValue(printMap.get("PackingUnit2"));
				nCell.setCellStyle(poiUtil.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));	
				
				//Price
				region = new CellRangeAddress(curRow-1, curRow, 7, 7);	//纵向合并单元格 
				sheet.addMergedRegion(region);
				
				nCell = nRow.createCell(7);
				nCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				nCell.setCellValue(Double.parseDouble(printMap.get("Price2")));
				nCell.setCellStyle(poiUtil.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));
				
				
				//Amount
				region = new CellRangeAddress(curRow-1, curRow, 8, 8);	//纵向合并单元格 
				sheet.addMergedRegion(region);
				
				nCell = nRow.createCell(8);
				if(UtilFuns.isNotEmpty(printMap.get("Cnumber2")) && UtilFuns.isNotEmpty(printMap.get("Price2"))){
					nCell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
					nCell.setCellFormula("F"+String.valueOf(curRow)+"*H"+String.valueOf(curRow));
				}
				nCell.setCellStyle(poiUtil.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));		
				
				curRow++;
				
				region = new CellRangeAddress(curRow-1, curRow-1, 1, 3);	//纵向合并单元格
				sheet.addMergedRegion(region);
				
				nRow = sheet.createRow(curRow-1);
				nRow.setHeightInPoints(24);
				
				nCell = nRow.createCell(1);
				nCell.setCellValue(printMap.get("ProductNo2"));
				nCell.setCellStyle(poiUtil.notecv10_BorderThin(wb, defaultFont10));			
				
				//合并单元格画线
				for(int j=2;j<9;j++){
					nCell = nRow.createCell(j);
					nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));
				}
			}
			
			
			//InputBy
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(24);
			
			nCell=nRow.createCell(1);
			nCell.setCellValue(printMap.get("InputBy"));
			nCell.setCellStyle(poiUtil.bnormalv12(wb, defaultFont12));
			
			
			//CheckBy+inspector
			
			nCell =nRow.createCell(4);
			nCell.setCellValue(printMap.get("CheckBy"));
			nCell.setCellStyle(poiUtil.bnormalv12(wb, defaultFont12));
			
			nCell=nRow.createCell(7);
			nCell.setCellValue("总金额: ");
			nCell.setCellStyle(bcv12(wb));
			
			//TotalAmount
			if(UtilFuns.isNotEmpty(printMap.get("Cnumber")) && UtilFuns.isNotEmpty(printMap.get("Price"))){
				nCell =nRow.createCell(8);
				nCell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
				nCell.setCellFormula("SUM(I"+String.valueOf(curRow-4)+":I"+String.valueOf(curRow-1)+")");
				nCell.setCellStyle(poiUtil.moneyrv12_BorderThin(wb,defaultFont12,rmb2Format));
			}
			
			//note
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(21);
			
			nCell=nRow.createCell(2);
			nCell.setCellValue(printMap.get("Remark"));
			nCell.setCellStyle(noteStyle(wb));
		
			//Crequest
			region=new CellRangeAddress(curRow,curRow,1,8);           //指定合并区域
			sheet.addMergedRegion(region);
			
			nRow=sheet.createRow(curRow++);
			float height=poiUtil.getCellAutoHeight(printMap.get("Crequest"), 12f);      //自动高度
			nRow.setHeightInPoints(height);
			
			nCell=nRow.createCell(1);
			nCell.setCellValue(printMap.get("Crequest"));
			nCell.setCellStyle(requestStyle(wb));
		
			//space  line
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(20);
			
			//duty
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(32);
			
			nCell=nRow.createCell(1);
			nCell.setCellValue("未按以上要求出货而导致客人索赔，由供方承担。");
			nCell.setCellStyle(dutyStyle(wb));
			
			//space line
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(32);
			
			//buyer
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(25);
			
			nCell=nRow.createCell(1);
			nCell.setCellValue("    收购方负责人：");
			nCell.setCellStyle(dutyStyle(wb));
		
			
			//seller
			nCell=nRow.createCell(5);
			nCell.setCellValue("供方负责人：");
			nCell.setCellStyle(dutyStyle(wb));
			
			curRow++;
			//--------------------------打印结束-------------------------------------
			
			ByteArrayOutputStream stream=new ByteArrayOutputStream();
			wb.write(stream);
		}
	}
	
	private HSSFCellStyle requestStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(true);  						//换行   
		HSSFFont curFont = wb.createFont();					//设置字体
		curFont.setFontName("宋体");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置
		
		curFont.setFontHeightInPoints((short)10);
		curStyle.setFont(curFont);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		
		return curStyle;
	}
	
	private HSSFCellStyle noteStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();					//设置字体
		curFont.setFontName("宋体");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置
		
		curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
		curFont.setFontHeightInPoints((short)12);
		curStyle.setFont(curFont);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		
		return curStyle;
	}



	private HSSFCellStyle dutyStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();					//设置字体
		curFont.setFontName("黑体");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置
		
		curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
		curFont.setFontHeightInPoints((short)16);
		curStyle.setFont(curFont);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		
		return curStyle;
	} 	
	
	public HSSFCellStyle bcv12(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();						//设置字体
		curFont.setFontName("Times New Roman");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);			//设置中文字体，那必须还要再对单元格进行编码设置
		
		curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);		//加粗
		curFont.setFontHeightInPoints((short)12);
		curStyle.setFont(curFont);
		
		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);				//实线
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);			//粗实线
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//实线
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线
		
		curStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);			    //样式居中设置
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		
		return curStyle;
	}



	private HSSFCellStyle moneyrv10_BorderThin(HSSFWorkbook wb,
			HSSFFont defaultFont10, Short rmb4Format) {
		HSSFCellStyle style=wb.createCellStyle();
		style.setFont(defaultFont10);
		style.setDataFormat(rmb4Format);
		
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);   //横向具右对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   //单元格垂直居中
		
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);    // 实线上边框
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  // 实线下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);    // 实线左边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);   // 实线右边框
		
		return style;
	}



	/**
	 * 设置样式
	 * @param wb
	 * @param defaultFont10
	 * @return
	 */
	private HSSFCellStyle notehv10_BorderThin(HSSFWorkbook wb,
			HSSFFont defaultFont10) {
		HSSFCellStyle style=wb.createCellStyle();
		//自动换行
		style.setWrapText(true);     //换行
		
		//把字体  应用到当前样式
		style.setFont(defaultFont10);
		
//		style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);         //横向具右对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中
		
		//设置边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);             //实线上边框
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);          //实线下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);            //实线左边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);            //实线右边框
		
		return style;
	}

	/**
	 * 设置标题字体大小
	 * @param wb
	 * @return
	 */
	private HSSFCellStyle thStyle(HSSFWorkbook wb) {
		HSSFCellStyle style=wb.createCellStyle();
		HSSFFont font=wb.createFont();
		
		font.setFontName("宋体");
		font.setCharSet(HSSFFont.DEFAULT_CHARSET);     //设置中文字体，那必须还要再对单元格进行编码设置
		
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  //加黑
		font.setFontHeightInPoints((short)12);         //字体大小12
		
		style.setFont(font);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}

	/**
	 * 收购方，生产厂家，合同号 等样式
	 * @param wb
	 * @param blackFont  字体
	 * @return
	 */
	private HSSFCellStyle titlev12(HSSFWorkbook wb, HSSFFont blackFont) {
		HSSFCellStyle style=wb.createCellStyle();
		
		style.setFont(blackFont);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);     // 单元格垂直居中
		return style;
	}

	private Short rmb4Format(HSSFWorkbook wb) {
		// TODO Auto-generated method stub
		return null;
	}

	private Short rmb2Format(HSSFWorkbook wb) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 设置黑体12号字体
	 * @param wb
	 * @return
	 */
	private HSSFFont blackFont12(HSSFWorkbook wb) {
		HSSFFont font=wb.createFont();
		
		font.setFontName("黑体");
		font.setCharSet(HSSFFont.DEFAULT_CHARSET);  // 设置中文字体，那必须还要再对单元格进行编码设置
		font.setFontHeightInPoints((short)12);
		return null;
	}

	/**
	 * 设置默认的12号字体
	 * @param wb
	 * @return
	 */
	private HSSFFont defaultFont12(HSSFWorkbook wb) {
		HSSFFont font=wb.createFont();
		
		font.setFontName("Times New Roman");
		font.setFontHeightInPoints((short)12);   
		font.setCharSet(HSSFFont.DEFAULT_CHARSET);   // 设置中文字体，那必须还要再对单元格进行编码设置
		return font;
	}

	/**
	 * 设置默认的10号字体
	 * @param wb
	 * @return
	 */
	private HSSFFont defaultFont10(HSSFWorkbook wb) {
		HSSFFont font=wb.createFont();
		font.setFontName("Times New Roman");
		font.setCharSet(HSSFFont.DEFAULT_CHARSET);   // 设置中文字体，那必须还要再对单元格进行编码设置
		font.setFontHeightInPoints((short)10);
		return font;
	}

	/**
	 * 设置购销合同标题头样式
	 * @param wb
	 * @return
	 */
	private HSSFCellStyle titleStyle(HSSFWorkbook wb) {
		HSSFCellStyle style=wb.createCellStyle();
		HSSFFont font =wb.createFont();
		
		font.setFontName("黑体");                       //设置字体名字
		font.setCharSet(HSSFFont.DEFAULT_CHARSET);     //设置中文字体，那必须还要再对单元格进行编码设置
		
		font.setFontHeightInPoints((short)18);         //设置字体大小
 		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  //加黑
 		
 		style.setFont(font);
 		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //设置水平垂直
 		return style;
	}

	/**
	 * 设置划线
	 * @param wb
	 * @param patriarch
	 * @param iRowStart  开始行
	 * @param iColStart  开始列
	 * @param iRowStop   结束行
	 * @param iColStop   结束列
	 */
	private void setLine(HSSFWorkbook wb, HSSFPatriarch patriarch, int iRowStart,
			int iColStart, int iRowStop, int iColStop) {
		//设置划线的位置(创建一个锚对象)
		HSSFClientAnchor anchor=new HSSFClientAnchor(0, 0, 350, 0, (short)iRowStart, iColStart, (short)iRowStop, iColStop);
		HSSFSimpleShape lineShape= patriarch.createSimpleShape(anchor);
		lineShape.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
	}

	/**
	 * 设置电话样式
	 * @param wb
	 * @return
	 */
	private HSSFCellStyle telStyle(HSSFWorkbook wb) {
		HSSFCellStyle style=wb.createCellStyle();
		HSSFFont font=wb.createFont();
		
		font.setFontName("宋体");                         //设置字体名
		font.setCharSet(HSSFFont.DEFAULT_CHARSET);       //设置中文字体，那必须还要再对单元格进行编码设置
		
		font.setFontHeightInPoints((short)9);            //设置字体大小
		style.setFont(font);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //设置垂直居中
		return style;
	}

	/**
	 * 设置字符串 " CO., LTD."的样式
	 * @param wb
	 * @return
	 */
	private HSSFCellStyle ltdStyle(HSSFWorkbook wb) {
		HSSFCellStyle style=wb.createCellStyle();
		HSSFFont font=wb.createFont();
		
		font.setFontName("Times New Roman");              //设置字体名
		font.setCharSet(HSSFFont.DEFAULT_CHARSET);        //设置中文字体，那必须还要再对单元格进行编码设置
		
		font.setFontHeight((short)16);                    //设置字体大小
		font.setItalic(true);                             //设置斜体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);     //设置字体加粗
		
		style.setFont(font);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //设置垂直居中
		return style;
	}

	/**
	 * 设置地址样式
	 * @param wb
	 * @return
	 */
	private HSSFCellStyle addressStyle(HSSFWorkbook wb) {
		HSSFCellStyle style=wb.createCellStyle();
		HSSFFont font=wb.createFont();
		
		font.setFontName("宋体");                       //设置字体名
		font.setCharSet(HSSFFont.DEFAULT_CHARSET);     //设置中文字体，那必须还要再对单元格进行编码设置
		
		font.setFontHeightInPoints((short)10);         //设置字体大小
		
		style.setFont(font);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   //设置垂直水平居中
		return style;
	}

	/**
	 * 设置标题样式
	 * @param wb
	 * @return
	 */
	private HSSFCellStyle tipStyle(HSSFWorkbook wb){
		HSSFCellStyle style=wb.createCellStyle();
		HSSFFont font=wb.createFont();
		font.setFontName("Georgia");                      //设置字体名
		font.setCharSet(HSSFFont.DEFAULT_CHARSET);        //设置中文字体，那必须还要在对单元格设置编码
		
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);     //加粗
		font.setFontHeightInPoints((short)28);            //设置字大小
	
		style.setFont(font);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);        //设置单元格垂直居中
		
		return style;
	}

	/**
	 * 设置头部字体样式
	 * @param wb
	 * @return
	 */
	private HSSFCellStyle headStyle(HSSFWorkbook wb) {
		HSSFCellStyle style=wb.createCellStyle();
		HSSFFont font=wb.createFont();
		font.setFontName("Comic Sans MS");            //设置字体的名字
		font.setCharSet(HSSFFont.DEFAULT_CHARSET);    //设置中文字字体，那必须还要在对单元格进行编码设置
		
		font.setItalic(true);                         //使用斜体
		font.setFontHeightInPoints((short)6);         //设置字体大小
		style.setFont(font);                          //在样式中设置字体
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);            //单元格垂直居中
  		return style;
	}

	/**
	 * 设置图片
	 * @param wb  Excel对象
	 * @param patriarch 
	 * @param pic
	 * @param iRowStart  图片所在位置的第一行
	 * @param iColStart  图片所在位置的第一列
	 * @param iRowStop   图片所在位置的第最后一行
	 * @param iColStop   图片所在位置的最后一列
	 * @throws Exception 
	 */
	private void setPicture(HSSFWorkbook wb, HSSFPatriarch patriarch,
			String pic, int iRowStart, int iColStart, int iRowStop, int iColStop) throws Exception {
		File imgFile=new File(pic);
		if(imgFile.exists()){ //如果文件存在
		
			ByteArrayOutputStream stream=new ByteArrayOutputStream();
			BufferedImage image=ImageIO.read(imgFile);  //将图片读到BufferImage中
			ImageIO.write(image, "jpg", stream);          //将图片写到字节流数组中
			
			//为图片设置放置在excel的位置(创建一个锚对象)
			// 左,上(0-255),右(0-1023),下(0-255) 表示前四个参数
			HSSFClientAnchor anchor=new HSSFClientAnchor(20, 1, 1018, 0, (short) (iColStart), iRowStart, (short) (iColStop), iRowStop);
			//HSSFWorkbook.PICTURE_TYPE_JPEG  表示图片的类型
			patriarch.createPicture(anchor,	wb.addPicture(stream.toByteArray(),HSSFWorkbook.PICTURE_TYPE_JPEG));
		}
	}
}
