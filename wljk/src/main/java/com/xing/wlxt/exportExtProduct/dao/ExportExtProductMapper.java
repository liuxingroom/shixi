package com.xing.wlxt.exportExtProduct.dao;

import org.apache.ibatis.annotations.Param;

import com.xing.wlxt.exportExtProduct.entity.ExportExtProduct;

public interface ExportExtProductMapper {

	/**
	 * 根据报运id来删除报运下的附件信息
	 * @param ids
	 */
	public void deleteByExportId(@Param("ids")String[] ids);

	/**
	 * 新增报运下所属货物的附件信息
	 * @param extep
	 */
	public void insert(ExportExtProduct extep);
	
}
