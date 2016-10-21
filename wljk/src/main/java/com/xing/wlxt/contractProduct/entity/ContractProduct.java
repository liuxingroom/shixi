package com.xing.wlxt.contractProduct.entity;

import java.io.Serializable;

/**
 * CONTRACT_PRODUCT_C
 * 购销合同下的货物信息
 */
public class ContractProduct implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1154381400030107887L;
	/**合同下货物id*/
	private String id; 
	/**生产厂家ID*/
	private String factoryID;
	/**购销合同id*/
	private String contractID;
	/**厂家名称*/
	private String factoryNa;
	/**货号*/
	private String productNo;
	/**货物照片*/
	private String productImage;
	/**获取描述*/
	private String productDesc;
	/**数量*/
	private Integer cNumber;
	/**实际出货数量*/
	private Integer outNumber;
	/**裝率*/
	private String loadingRate;
	/**箱数*/
	private String boxNum;
	/**包装单位*/
	private String packingUnit;
	/**单价*/
	private Double price;
	/**总金额 （冗余字段   在新增货物信息时进行计算     数量*单价）*/
	private Double amount;
	/**是否出货完毕
	 * 0未完成1完成
	 * */
	private Integer finished;
	/**附件
	 * 冗余，出处：出货表
	 * */
	private String exts;
	/**排序号*/
	private Integer orderNO;
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
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public String getFactoryNa() {
		return factoryNa;
	}
	public void setFactoryNa(String factoryNa) {
		this.factoryNa = factoryNa;
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
	public Integer getOutNumber() {
		return outNumber;
	}
	public void setOutNumber(Integer outNumber) {
		this.outNumber = outNumber;
	}
	public String getLoadingRate() {
		return loadingRate;
	}
	public void setLoadingRate(String loadingRate) {
		this.loadingRate = loadingRate;
	}
	public String getBoxNum() {
		return boxNum;
	}
	public void setBoxNum(String boxNum) {
		this.boxNum = boxNum;
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
	public Integer getFinished() {
		return finished;
	}
	public void setFinished(Integer finished) {
		this.finished = finished;
	}
	public String getExts() {
		return exts;
	}
	public void setExts(String exts) {
		this.exts = exts;
	}
	public Integer getOrderNO() {
		return orderNO;
	}
	public void setOrderNO(Integer orderNO) {
		this.orderNO = orderNO;
	}
	
}
