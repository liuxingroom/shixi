package com.xing.wlxt.exportProduct.entity;

import java.io.Serializable;

/**
 * 报运下的货物信息
 * EXPORT_PRODUCT_C
 */
public class ExportProduct implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5569717080307240614L;
	/**报运货物id*/
	private String exportProductId;
	/**报运id*/
	private String exportID;
	/**生产厂家ID*/
	private String factoryID;
	/**厂家名称(冗余字段)*/
	private String factoryNA;
	/**货号*/
	private String factoryNO;
	/**包装单位*/
	private String packingUnit;
	/**数量(分次走货在此体现)*/
	private Integer cnumber;
	/**件数(就是合同中的箱数)*/
	private Integer boxNum;
	/**毛重*/
	private Double grossWeight;
	/**净重*/
	private Double netWight;
	/**尺寸长*/
	private Double sizeLength;
	/**尺寸宽*/
	private Double sizeWidth;
	/**尺寸高*/
	private Double sizeHeight;
	/**出口单价（手填）*/
	private Double exPrice;
	/**单价*/
	private Double price;
	/**含税*/
	private Double tax;
	/**排序号*/
	private Integer orderNo;
	public String getExportProductId() {
		return exportProductId;
	}
	public void setExportProductId(String exportProductId) {
		this.exportProductId = exportProductId;
	}
	public String getExportID() {
		return exportID;
	}
	public void setExportID(String exportID) {
		this.exportID = exportID;
	}
	public String getFactoryID() {
		return factoryID;
	}
	public void setFactoryID(String factoryID) {
		this.factoryID = factoryID;
	}
	public String getFactoryNA() {
		return factoryNA;
	}
	public void setFactoryNA(String factoryNA) {
		this.factoryNA = factoryNA;
	}
	public String getFactoryNO() {
		return factoryNO;
	}
	public void setFactoryNO(String factoryNO) {
		this.factoryNO = factoryNO;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public Integer getCnumber() {
		return cnumber;
	}
	public void setCnumber(Integer cnumber) {
		this.cnumber = cnumber;
	}
	public Integer getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(Integer boxNum) {
		this.boxNum = boxNum;
	}
	public Double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public Double getNetWight() {
		return netWight;
	}
	public void setNetWight(Double netWight) {
		this.netWight = netWight;
	}
	public Double getSizeLength() {
		return sizeLength;
	}
	public void setSizeLength(Double sizeLength) {
		this.sizeLength = sizeLength;
	}
	public Double getSizeWidth() {
		return sizeWidth;
	}
	public void setSizeWidth(Double sizeWidth) {
		this.sizeWidth = sizeWidth;
	}
	public Double getSizeHeight() {
		return sizeHeight;
	}
	public void setSizeHeight(Double sizeHeight) {
		this.sizeHeight = sizeHeight;
	}
	public Double getExPrice() {
		return exPrice;
	}
	public void setExPrice(Double exPrice) {
		this.exPrice = exPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
