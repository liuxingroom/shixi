package com.xing.wlxt.contractExtProduct.entity;

import java.io.Serializable;

/**
 * 购销合同下的附信息
 * EXT_CPRODUCT_C
 *
 */
public class ContractExtProduct implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9033787039839471882L;
	/**附件id*/
	private String id;
	/**生产厂家ID*/
	private String factoryID;
	/**合同下货物id*/
	private String contractProductID;
	/**
	 * 厂家名称
	 * 冗余字段
	 * */
	private String factoryNA;
	/**类型*/
	private Integer ctype;
	/**货号*/
	private String productNo;
	/**货物照片*/
	private String productImage;
	/**货物描述*/
	private String productDesc;
	/**数量*/
	private Integer cNumber;
	/**包装单位*/
	private String packingUnit;
	/**单价*/
	private Double price;
	/**总金额*/
	private Double amount;
	/**要求*/
	private String productRequest;
	/**排序号*/
	private Integer orderNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFactoryID() {
		return factoryID;
	}
	public void setFactoryID(String factoryID) {
		this.factoryID = factoryID;
	}
	public String getContractProductID() {
		return contractProductID;
	}
	public void setContractProductID(String contractProductID) {
		this.contractProductID = contractProductID;
	}
	public String getFactoryNA() {
		return factoryNA;
	}
	public void setFactoryNA(String factoryNA) {
		this.factoryNA = factoryNA;
	}
	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
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
	public Integer getcNumber() {
		return cNumber;
	}
	public void setcNumber(Integer cNumber) {
		this.cNumber = cNumber;
	}
	public String getPackingUnit() {
		return packingUnit;
	}
	public void setPackingUnit(String packingUnit) {
		this.packingUnit = packingUnit;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getProductRequest() {
		return productRequest;
	}
	public void setProductRequest(String productRequest) {
		this.productRequest = productRequest;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
