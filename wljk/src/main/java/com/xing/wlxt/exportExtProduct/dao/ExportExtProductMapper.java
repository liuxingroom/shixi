package com.xing.wlxt.exportExtProduct.dao;

import org.apache.ibatis.annotations.Param;

public interface ExportExtProductMapper {

	/**
	 * 根据报运id来删除报运下的附件信息
	 * @param ids
	 */
	public void deleteByExportId(@Param("ids")String[] ids);
	
}
