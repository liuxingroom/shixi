package com.xing.wlxt.contract.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.xing.wlxt.contractProduct.entity.ContractProduct;
import com.xing.wlxt.contractProduct.view.ContractProductVO;

public class ContractVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**购销合同id*/
	private String contractID;
	/**购销合同下的货物信息*/
	private List<ContractProductVO> contractProduct;
	/**收购方*/
	private String offer;
	/**
	 * 合同号(自动产生)
	 * */
	private String contractNO;
	/**签单日期*/
	private Date signingDate;
	/**制单人*/
	private String inputBy;
	/**审单人*/
	private String checkBy;
	/**验货员*/
	private String inspector;
	/**总金额
	 * 冗余，自动计算
	 * */
	private Double totalAmount;
	/**重要程度
	 * 打印时标识几个星,对应说明中的内容
	 * */
	private Integer inportNum;
	/**要求*/
	private String crequest;
	/**客户名称
	 * 出货表中的字段
	 * */
	private String customName;
	/**交货期限
	 * 出货表中的字段
	 * */
	private Date deliveryPeriod;
	/**船期
	 * 出货表中的字段
	 * */
	private Date shipName;
	/**贸易条款
	 * 出货表中的字段
	 * */
	private String tradeTerms;
	/**说明*/
	private String remark;
	/**打印版式*/
	private char printStyle;
	/**归档前状态*/
	private int oldState;
	/**状态
	 * 0草稿 1已上报待报运
	 * */
	private int state;
	/**走货状态
	 * 0未走货 1部分 2全部
	 * */
	private int outState;
	/**创建人*/
	private String createBy;
	/**创建部门*/
	private String createDept;
	/**创建日期*/
	private String createTime;
	public String getContractID() {
		return contractID;
	}
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	public List<ContractProductVO> getContractProduct() {
		return contractProduct;
	}
	public void setContractProduct(List<ContractProductVO> contractProduct) {
		this.contractProduct = contractProduct;
	}
	public String getOffer() {
		return offer;
	}
	public void setOffer(String offer) {
		this.offer = offer;
	}
	public String getContractNO() {
		return contractNO;
	}
	public void setContractNO(String contractNO) {
		this.contractNO = contractNO;
	}
	public Date getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}
	public String getInputBy() {
		return inputBy;
	}
	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}
	public String getCheckBy() {
		return checkBy;
	}
	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}
	public String getInspector() {
		return inspector;
	}
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getInportNum() {
		return inportNum;
	}
	public void setInportNum(Integer inportNum) {
		this.inportNum = inportNum;
	}
	public String getCrequest() {
		return crequest;
	}
	public void setCrequest(String crequest) {
		this.crequest = crequest;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public Date getDeliveryPeriod() {
		return deliveryPeriod;
	}
	public void setDeliveryPeriod(Date deliveryPeriod) {
		this.deliveryPeriod = deliveryPeriod;
	}
	public Date getShipName() {
		return shipName;
	}
	public void setShipName(Date shipName) {
		this.shipName = shipName;
	}
	public String getTradeTerms() {
		return tradeTerms;
	}
	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public char getPrintStyle() {
		return printStyle;
	}
	public void setPrintStyle(char printStyle) {
		this.printStyle = printStyle;
	}
	public int getOldState() {
		return oldState;
	}
	public void setOldState(int oldState) {
		this.oldState = oldState;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getOutState() {
		return outState;
	}
	public void setOutState(int outState) {
		this.outState = outState;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDept() {
		return createDept;
	}
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
