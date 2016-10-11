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
	private String extProductID;
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
	private Integer cType;
	/**货号*/
	private String productNO;
	/**货物照片*/
	private String productImage;
	/**货物描述*/
	private String productDesc;
	/**数量*/
	private Integer cNumber;
	/**包装单位*/
	private String packingNuit;
	/**单价*/
	private Double price;
	/**总金额*/
	private Double amount;
	/**要求*/
	private String productRequest;
	/**排序号*/
	private Integer orderNo;
	public String getExtProductID() {
		return extProductID;
	}
	public void setExtProductID(String extProductID) {
		this.extProductID = extProductID;
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
	public Integer getcType() {
		return cType;
	}
	public void setcType(Integer cType) {
		this.cType = cType;
	}
	public String getProductNO() {
		return productNO;
	}
	public void setProductNO(String productNO) {
		this.productNO = productNO;
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
	public String getPackingNuit() {
		return packingNuit;
	}
	public void setPackingNuit(String packingNuit) {
		this.packingNuit = packingNuit;
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
