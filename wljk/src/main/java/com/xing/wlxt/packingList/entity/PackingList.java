package com.xing.wlxt.packingList.entity;

import java.io.Serializable;
import java.util.Date;

public class PackingList implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8880334195002216515L;
	/**装箱单id*/
	private String id;
	/**卖方*/
	private String seller;
	/**买方*/
	private String buyer;
	/**发票号*/
	private String invoiceNo;
	/**发票日期*/
	private Date invoiceDate;
	/**唛头*/
	private String marks;
	/**描述*/
	private String desriptions;
	/**报运ID集合*/
	private String exportIds;
	/**报运NO集合   存储报运号*/
	private String exportNos;
	/**状态0草稿 1已上报*/
	private Integer state;
	/**创建人*/
	private String createBy;
	/**创建部门*/
	private String createDept;
	/**创建日期*/
	private Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getDesriptions() {
		return desriptions;
	}
	public void setDesriptions(String desriptions) {
		this.desriptions = desriptions;
	}
	public String getExportIds() {
		return exportIds;
	}
	public void setExportIds(String exportIds) {
		this.exportIds = exportIds;
	}
	public String getExportNos() {
		return exportNos;
	}
	public void setExportNos(String exportNos) {
		this.exportNos = exportNos;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
