package com.xing.wlxt.print;

import java.awt.Point;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import com.mysql.jdbc.Util;
import com.xing.wlxt.contract.view.ContractVO;
import com.xing.wlxt.contractProduct.view.ContractProductVO;
import com.xing.wlxt.utils.DownloadUtil;
import com.xing.wlxt.utils.UtilFuns;
import com.xing.wlxt.utils.file.PoiUtil;

/**
 *	打印购销合同
 */
public class ContractPrint {

	public void print(ContractVO contract, String path,
			HttpServletResponse response) throws Exception {
		
		//相同厂家的信息一起打印
		List<ContractProductVO> oList=contract.getContractProduct();
		UtilFuns utilFuns=new UtilFuns();
		String tempXlsFile=path+"/make/xlsprint/tCONTRACT.xls";     //获取打印模板
		
		//----------------------------整理购销合同打印的信息---------------------------------
		//填写每页的内容，之后在循环每页读取打印
		Map<String, String> pageMap=null;                 //打印购销合同每一页代表一个map集合
		List<Map> pageList=new ArrayList<Map>();          //将封装购销合同信息的map集合调价到list集合中
		
		ContractProductVO oProduct=null;
		String stars="";
		for(int i=0;i<contract.getImportNum();i++){    //获取该购销合同的重要程度
			stars+="★";
		}
		
		String  oldFactory="";                        //用于记录购销合同中第一个货物的生产厂家
		for(int i=0;i<oList.size();i++){
			oProduct=oList.get(i);                   //获取第一个货物
			pageMap=new HashMap<String, String>();   //创建存放购销合同中货物信息的容器
			
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
			
			oldFactory=oProduct.getFactoryID().getFactoryNA();
			
			if((contract.getPrintStyle()+"").equals("2")){   //判断打印模板是一张两个
				i++;               //读取第二个货物信息
				if(i<oList.size()){    //如果下一个货物信息是否存在
					oProduct=oList.get(i);
					
					if(oProduct.getFactoryID()!=null && oProduct.getFactoryID().getFactoryNA()!=null){
						if(oProduct.getFactoryID().getFactoryNA().equals(oldFactory)){    //如果厂家相同
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
						}else{ //如果厂家不同
							i--;	//tip:list退回
						}
					}
				}else{//如果下一个货物信息不存在
					pageMap.put("ProductNo2", null);	//后面打印时依据此判断是否有第二个货物
				}
			}
			
			pageMap.put("ContractDesc", stars+" 货物描述");  //重要程度 + 货物描述
		
			pageList.add(pageMap);
		}
		
		//----------------------------------整理打印的信息结束-----------------------------------------
		
		int cellheight=96;   //一个货物的高度			用户需求，一个货物按192高度打印，后来又嫌难看，打印高度和2款高度一样。
//		if(contract.getPrintStyle().equals("2")){
//	    	cellHeight = 96;	//两个货物的高度
//      }
		
		PoiUtil poiUtil=new PoiUtil();
		HSSFWorkbook wb=new HSSFWorkbook(new FileInputStream(tempXlsFile));         //打开excel文件
		HSSFFont defaultFont10=poiUtil.defaultFont10(wb);                           //设置字体	
		HSSFFont defaultFont12=poiUtil.defaultFont12(wb);                           //设置字体	
		HSSFFont blackFont = poiUtil.blackFont12(wb);			                    //设置字体
		Short rmb2Format=poiUtil.rmb2Format(wb);								    //设置格式
		Short rmb4Format=poiUtil.rmb4Format(wb);                                    //设置格式
		
		
		HSSFSheet sheet=wb.getSheetAt(0);          //选择第一个工作簿
		wb.setSheetName(0, "购销合同");               //设置工作簿的名称
		
		
		//sheet.setDefaultColumnWidth((short) 20); 		// 设置每列默认宽度
		
		//		POI分页符有BUG，必须在模板文件中插入一个分页符，然后再此处删除预设的分页符；最后在下面重新设置分页符。
		//		sheet.setAutobreaks(false);
		//		int iRowBreaks[] = sheet.getRowBreaks();
		//		sheet.removeRowBreak(3);
		//		sheet.removeRowBreak(4);
		//		sheet.removeRowBreak(5);
		//		sheet.removeRowBreak(6);
		
		/**
		 * 合并单元格的参数解析：由左至右     起始行号，终止行号， 起始列号，终止列号
		 * CellRangeAddress region=new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
		 */

		CellRangeAddress region=null;
		HSSFPatriarch patriarch=sheet.createDrawingPatriarch();        //add  picture
		HSSFRow nRow=null;
		HSSFCell nCell=null;
		int curRow=0;
		
		//打印每页
		Map<String, String> printMap=null;
		for(int p=0;p<pageList.size();p++){
			printMap=pageList.get(p);       //获取封装数据的map集合
			
			if(p>0){
				sheet.setRowBreak(curRow++);    //在第startRow行设置分页符
			}
			 
			//设置logo图片
			poiUtil.setPicture(wb, patriarch, path+"/make/xlsprint/logo.jpg", curRow,2, curRow+4,2);
			
			//header
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(21);
			
			nCell =nRow.createCell(3);
			nCell.setCellValue("SHAANXI");
			nCell.setCellStyle(headStyle(wb));
			
			
			//header
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(41);
			
			nCell =nRow.createCell(3);
			nCell.setCellValue("    JK INTERNATIONAL ");
			nCell.setCellStyle(tipStyle(wb));
			
			curRow++;
			
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
			
			//line
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(7);
			
			poiUtil.setLine(wb, patriarch, curRow, 2,curRow, 8);      //draw line
			
			//header
			nRow =sheet.createRow(curRow++);
			nRow.setHeightInPoints(30);
			
			nCell=nRow.createCell(4);
			nCell.setCellValue("    购   销   合   同");
			nCell.setCellStyle(titleStyle(wb));

			
			
			//Offeror
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(20);
			
			nCell=nRow.createCell(1);
			nCell.setCellValue(printMap.get("offeror"));
			nCell.setCellStyle(poiUtil.titlev12(wb, blackFont));
			
			
			//factory
			nCell=nRow.createCell(5);
			nCell.setCellValue(printMap.get("Factory"));
			nCell.setCellStyle(poiUtil.titlev12(wb, blackFont));
		
			//contractNo
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(20);
			
			nCell =nRow.createCell(1);
			nCell.setCellValue(printMap.get("ContractNo"));
			nCell.setCellStyle(poiUtil.titlev12(wb, blackFont));
			
			//Contacts
			nCell=nRow.createCell(5);
			nCell.setCellValue(printMap.get("Contacts"));
			nCell.setCellStyle(poiUtil.titlev12(wb, blackFont));
		
			//SigningDate
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(20);
			
			nCell =nRow.createCell(1);
			nCell.setCellValue(printMap.get("SigningDate"));
			nCell.setCellStyle(poiUtil.titlev12(wb, blackFont));
			
			//phone
			nCell=nRow.createCell(5);
			nCell.setCellValue(printMap.get("Phone"));
			nCell.setCellStyle(poiUtil.titlev12(wb, blackFont));
			
			//inputNum
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(24);
			
			//将第10行的第一列和第三列的单元格合并
			region=new CellRangeAddress(curRow-1, curRow-1, 1, 3);
			sheet.addMergedRegion(region);
			
			nCell=nRow.createCell(1);
			nCell.setCellValue("产品");
			nCell.setCellStyle(thStyle(wb));
			
			//start
			/**
			 * 下面的代码只是为单元格设置样式    如果没有设置样式  会导致产品的第2-3列没有黑实线
			 */
			nCell=nRow.createCell(2);
			nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));
			
			nCell=nRow.createCell(3);
			nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));
			//end
			
			nCell=nRow.createCell(4);
			nCell.setCellValue(printMap.get("ContractDesc"));
			nCell.setCellStyle(thStyle(wb));
			/**
			 * 合并单元格的参数解析：由左至右     起始行号，终止行号， 起始列号，终止列号
			 */
			region=new CellRangeAddress(curRow-1, curRow-1, 5, 6);        //纵向合并单元格
			sheet.addMergedRegion(region);
			
			nCell=nRow.createCell(5);
			nCell.setCellValue("数量");
			nCell.setCellStyle(thStyle(wb));
			
			/**
			 * 同上面280行
			 */
			nCell=nRow.createCell(6);
			nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));
			
			nCell=nRow.createCell(7);
			nCell.setCellValue("单价");
			nCell.setCellStyle(thStyle(wb));
			
			nCell=nRow.createCell(8);
			nCell.setCellValue("总金额");
			nCell.setCellStyle(thStyle(wb));
			
			nRow=sheet.createRow(curRow++);
			nRow.setHeightInPoints(96);
			
			region=new CellRangeAddress(curRow-1, curRow-1, 1, 3);         //横向合并单元格
			sheet.addMergedRegion(region);
			
			
			//插入产品图片
			if(UtilFuns.isNotEmpty(printMap.get("ProdductImage"))){
				poiUtil.setPicture(wb, patriarch, path+"ufiles/jquery/"+printMap.get("ProductImage"), curRow-1, 1, curRow, 3);
			}
			
			//start
			/**
			 * 同上面280行
			 */
			nCell=nRow.createCell(2);
			nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));
		
			nCell=nRow.createCell(3);
			nCell.setCellStyle(poiUtil.notecv10_BorderThin(wb, defaultFont10));
			//end
			
			//ProductDesc
			region=new CellRangeAddress(curRow-1,curRow,4,4);       //纵向合并单元格
			sheet.addMergedRegion(region);
			
			nCell=nRow.createCell(4);
			nCell.setCellValue(printMap.get("ProductDesc"));
			nCell.setCellStyle(poiUtil.notehv10_BorderThin(wb, defaultFont10));
			
			//cNumber    纵向合并单元格
			region=new CellRangeAddress(curRow-1,curRow,5,5);
			sheet.addMergedRegion(region);
			
			nCell=nRow.createCell(5);
			nCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			nCell.setCellValue(Double.parseDouble(printMap.get("Cnumber")));
			nCell.setCellStyle(poiUtil.numberrv10_BorderThin(wb, defaultFont10));
			
			//Unit
			region=new CellRangeAddress(curRow-1,curRow,6,6);     //纵向合并单元格
			sheet.addMergedRegion(region);
			
			nCell=nRow.createCell(6);
			nCell.setCellValue(printMap.get("PackingUnit"));
			nCell.setCellStyle(poiUtil.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));
	
			
			//Price
			region=new CellRangeAddress(curRow-1,curRow,7,7);     //纵向合并单元格
			sheet.addMergedRegion(region);
			
			nCell=nRow.createCell(7);
			nCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			if(StringUtils.isNotEmpty(printMap.get("Price"))){
				nCell.setCellValue(Double.parseDouble(printMap.get("Price")));
			}
			nCell.setCellStyle(poiUtil.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));
			
			
			//Amount
			region=new CellRangeAddress(curRow-1,curRow,8,8);   //纵向合并单元格
			sheet.addMergedRegion(region);
			
			nCell=nRow.createCell(8);
			if(UtilFuns.isNotEmpty(printMap.get("Cnumber")) && UtilFuns.isNotEmpty(printMap.get("Price"))){
				nCell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
				nCell.setCellFormula("F"+String.valueOf(curRow)+"*H"+String.valueOf(curRow));
			}
			
			nCell.setCellStyle(poiUtil.moneyrv10_BorderThin(wb, defaultFont10, rmb4Format));
			
			curRow++;
			
			region=new CellRangeAddress(curRow-1,curRow-1,1,3);      //纵向合并单元格
			sheet.addMergedRegion(region);
			
			//productNo
			nRow=sheet.createRow(curRow-1);
			nRow.setHeightInPoints(24);
			
			nCell=nRow.createCell(1);
			nCell.setCellValue(printMap.get("ProductNo"));
			nCell.setCellStyle(poiUtil.notecv10_BorderThin(wb, defaultFont10));
			
			/**
			 * 同上面280行
			 */
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
		}
		
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();        //生成流对象
		wb.write(byteArrayOutputStream);
		
		//工具类，封装弹出下载框
		String outFile="购销合同.xls";
		DownloadUtil down=new DownloadUtil();
		down.download(byteArrayOutputStream, response, outFile);
	}

	private HSSFCellStyle leftStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(true);  						//换行   
		HSSFFont curFont = wb.createFont();					//设置字体
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置
		//fTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
		curFont.setFontHeightInPoints((short)10);
		curStyle.setFont(curFont);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线右边框
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);				//实线右边框
		
		return curStyle;
	}  
	
	private HSSFCellStyle headStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();					//设置字体  创建字体对象
		curFont.setFontName("Comic Sans MS");               //设置字体的名字
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置
		
		curFont.setItalic(true);						    
		curFont.setFontHeightInPoints((short)16);           //设置字体大小
		curStyle.setFont(curFont);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		return curStyle;
	}  
	
	private HSSFCellStyle tipStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();					//设置字体
		curFont.setFontName("Georgia");						//设置字体名
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置
		
		curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
		curFont.setFontHeightInPoints((short)28);           //设置字体大小
		curStyle.setFont(curFont);                          //在样式对象中设置字体样式
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		
		return curStyle;
	}  
	
	private HSSFCellStyle addressStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();					//设置字体
		curFont.setFontName("宋体");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置
		
		//fTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
		curFont.setFontHeightInPoints((short)10);
		curStyle.setFont(curFont);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		
		return curStyle;
	}  
	
	private HSSFCellStyle ltdStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();					//设置字体
		curFont.setFontName("Times New Roman");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置
		
		curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
		curFont.setItalic(true);
		curFont.setFontHeightInPoints((short)16);
		curStyle.setFont(curFont);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		
		return curStyle;
	} 	
	
	private HSSFCellStyle telStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();					//设置字体
		curFont.setFontName("宋体");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置
		
		//fTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
		curFont.setFontHeightInPoints((short)9);
		curStyle.setFont(curFont);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		
		return curStyle;
	} 	
	
	private HSSFCellStyle titleStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();					//设置字体
		curFont.setFontName("黑体");
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置
		
		curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
		curFont.setFontHeightInPoints((short)18);
		curStyle.setFont(curFont);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		
		return curStyle;
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
	
	public HSSFCellStyle thStyle(HSSFWorkbook wb){
		HSSFCellStyle curStyle = wb.createCellStyle();
		HSSFFont curFont = wb.createFont();					//设置字体
		curFont.setFontName("宋体");
		curFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
		curFont.setFontHeightInPoints((short)12);
		curFont.setCharSet(HSSFFont.DEFAULT_CHARSET);		//设置中文字体，那必须还要再对单元格进行编码设置
		
		curStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);				//实线右边框
		curStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);				//实线右边框
		curStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//实线右边框
		curStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//实线右边框
		
		curStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
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
		
		curStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);		//单元格垂直居中
		
		return curStyle;
	}		
	
}
