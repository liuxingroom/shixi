package com.xing.wlxt.factory.entity;

import java.io.Serializable;
import java.util.Date;

public class Factory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**生产厂家ID*/
	private  String factoryId;
	/**厂家全称*/
	private String fullName;
	/**厂家名称缩写*/
	private String factoryNA;
	/**联系人(生产厂家方面)*/
	private String contacts;
	/**电话*/
	private String phone;
	/**手机*/
	private String mobile;
	/**传真*/
	private String fax;
	/**说明*/
	private String note;
	/**验货员(物流方分配到生产厂家的驻场人员)*/
	private String inspector;
	/**
	 * 状态  表示物流公司与该厂家的合作关系
	 * 1（正常）表示现在在合作，0（停用）现在没有合作
	 * */
	private char state;
	/**排序号*/
	private Integer orderNo;
	
	//一下是三个权限字段
	/**创建人*/
	private String createBy;
	/**创建部门*/
	private String createDept;
	/**创建日期*/
	private Date createTime;
	public String getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFactoryNA() {
		return factoryNA;
	}
	public void setFactoryNA(String factoryNA) {
		this.factoryNA = factoryNA;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getInspector() {
		return inspector;
	}
	public void setInspector(String inspector) {
		this.inspector = inspector;
	}
	public Character getState() {
		return state;
	}
	public void setState(Character state) {
		this.state = state;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
	
}
