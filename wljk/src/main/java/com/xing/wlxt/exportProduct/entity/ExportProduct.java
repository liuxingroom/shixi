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
	private String id;
	/**报运id*/
	private String exportID;
	/**生产厂家ID*/
	private String factoryID;
	/**厂家名称(冗余字段)*/
	private String factoryName;
	/**产品名称*/
	private String productName;
	/**货号*/
	private String productNO;
	/**价格*/
	private Double price;
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
	/**含税*/
	private Double tax;
	/**排序号*/
	private Integer orderNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	public String getProductNO() {
		return productNO;
	}
	public void setProductNO(String productNO) {
		this.productNO = productNO;
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
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
