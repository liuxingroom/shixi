package com.xing.wlxt.exportExtProduct.entity;

import java.io.Serializable;

/**
 *  报运下的附件信息
 *  EXT_EPRODUCT_C
 */
public class ExportExtProduct implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7822813142691181774L;
	
	/**报运货物下附件id*/
	private String id;
	/**报运货物id*/
	private String exportProductID;
	/**生产厂家ID*/
	private String factoryID;
	/**厂家名称*/
	private String factoryNA;
	/**类型*/
	private int cType;
	/**货号*/
	private String productNo;
	/**货物照片*/
	private String productImage;
	/**货物描述*/
	private String productDesc;
	/**数量*/
	private int cNumber;
	/**包装单位*/
	private String prackingNuit;
	/**单价*/
	private double price;
	/**总金额*/
	private double amount;
	/**要求*/
	private String productRequest;
	/**排序号*/
	private int orderNO;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getExportProductID() {
		return exportProductID;
	}
	public void setExportProductID(String exportProductID) {
		this.exportProductID = exportProductID;
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
	public int getcType() {
		return cType;
	}
	public void setcType(int cType) {
		this.cType = cType;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public int getcNumber() {
		return cNumber;
	}
	public void setcNumber(int cNumber) {
		this.cNumber = cNumber;
	}
	public String getPrackingNuit() {
		return prackingNuit;
	}
	public void setPrackingNuit(String prackingNuit) {
		this.prackingNuit = prackingNuit;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getProductRequest() {
		return productRequest;
	}
	public void setProductRequest(String productRequest) {
		this.productRequest = productRequest;
	}
	public int getOrderNO() {
		return orderNO;
	}
	public void setOrderNO(int orderNO) {
		this.orderNO = orderNO;
	}
	
	
}
