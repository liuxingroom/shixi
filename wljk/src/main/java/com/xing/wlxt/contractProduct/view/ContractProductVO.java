package com.xing.wlxt.contractProduct.view;

import java.io.Serializable;
import java.util.List;

import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contractExtProduct.view.ContractExtProductVO;
import com.xing.wlxt.factory.entity.Factory;

/**
 * CONTRACT_PRODUCT_C
 * 购销合同下的货物信息
 */
public class ContractProductVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1154381400030107887L;
	/**合同下货物id*/
	private String id; 
	/**生产厂家ID*/
	private Factory factoryID;
	/**货物所属的购销合同*/
	private Contract contractID;
	/**货物和附件的关联关系
	 * 一个货物可以有多个附件
	 * */
	private List<ContractExtProductVO> contractExtProduct;
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
	/**总金额*/
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
	private Integer orderNo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Factory getFactoryID() {
		return factoryID;
	}
	public void setFactoryID(Factory factoryID) {
		this.factoryID = factoryID;
	}
	public Contract getContractID() {
		return contractID;
	}
	public void setContractID(Contract contractID) {
		this.contractID = contractID;
	}
	public List<ContractExtProductVO> getContractExtProduct() {
		return contractExtProduct;
	}
	public void setContractExtProduct(List<ContractExtProductVO> contractExtProduct) {
		this.contractExtProduct = contractExtProduct;
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
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
}
