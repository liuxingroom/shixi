package com.xing.wlxt.exportProduct.view;

import java.io.Serializable;
import java.util.List;

import com.xing.wlxt.export.entity.Export;
import com.xing.wlxt.exportExtProduct.entity.ExportExtProduct;
import com.xing.wlxt.exportExtProduct.view.ExportExtProductVO;
import com.xing.wlxt.factory.entity.Factory;

/**
 * 报运下的货物信息
 * EXPORT_PRODUCT_C
 */
public class ExportProductVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5569717080307240614L;
	/**报运货物id*/
	private String id;
	/**报运单*/
	private Export export;
	/**生产厂家*/
	private Factory factory;
	/**所属该货物的附件*/
	private List<ExportExtProductVO> extProducts;
	/**厂家名称(冗余字段)*/
	private String factoryName;
	/**产品名称*/
	private String productName;
	/**货号*/
	private String productNo;
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
	
	public Export getExport() {
		return export;
	}
	public void setExport(Export export) {
		this.export = export;
	}
	public Factory getFactory() {
		return factory;
	}
	public void setFactory(Factory factory) {
		this.factory = factory;
	}
	public List<ExportExtProductVO> getExtProducts() {
		return extProducts;
	}
	public void setExtProducts(List<ExportExtProductVO> extProducts) {
		this.extProducts = extProducts;
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
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
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
	
	
}
