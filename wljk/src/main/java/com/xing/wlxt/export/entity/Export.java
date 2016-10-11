package com.xing.wlxt.export.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 出口报运单的相关信息
 *
 */
public class Export implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**报运id*/
	private String  exportID;
	/**制单日期*/
	private Date inputDate;
	/**合同编号集合*/
	private String contractIDS;
	/**合同或确认书号*/
	private String customerContract;
	/**用证号L/C T/T*/
	private String LCNO;
	/**收货人及地址*/
	private String consignee;
	/**唛头*/
	private String marks;
	/**备注*/
	private String remark;
	/**装运港*/
	private String shipmentPort;
	/**目的港  */
	private String destinationPort;
	/**运输条件  SEA/AIR*/
	private String transportMode;
	/**价格条件  FOB/CIF*/
	private String priceCondition;
	/**状态     0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务*/
	private int state;
	/**总毛重    冗余，为委托服务，一个报运的总毛重*/
	private double grossWeight;
	/**总净重   冗余，为委托服务，一个报运的总净重*/
	private double netWeight;
	/**体积M3  冗余，为委托服务，一个报运的总体积 */
	private double measurement;
	/**创建人*/
	private String createBy;
	/**创建部门*/
	private String createDept;
	/**创建时间*/
	private String createTime;
	public String getExportID() {
		return exportID;
	}
	public void setExportID(String exportID) {
		this.exportID = exportID;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public String getContractIDS() {
		return contractIDS;
	}
	public void setContractIDS(String contractIDS) {
		this.contractIDS = contractIDS;
	}
	public String getCustomerContract() {
		return customerContract;
	}
	public void setCustomerContract(String customerContract) {
		this.customerContract = customerContract;
	}
	public String getLCNO() {
		return LCNO;
	}
	public void setLCNO(String lCNO) {
		LCNO = lCNO;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getShipmentPort() {
		return shipmentPort;
	}
	public void setShipmentPort(String shipmentPort) {
		this.shipmentPort = shipmentPort;
	}
	public String getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}
	public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	public String getPriceCondition() {
		return priceCondition;
	}
	public void setPriceCondition(String priceCondition) {
		this.priceCondition = priceCondition;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public double getGrossWeight() {
		return grossWeight;
	}
	public void setGrossWeight(double grossWeight) {
		this.grossWeight = grossWeight;
	}
	public double getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(double netWeight) {
		this.netWeight = netWeight;
	}
	public double getMeasurement() {
		return measurement;
	}
	public void setMeasurement(double measurement) {
		this.measurement = measurement;
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
