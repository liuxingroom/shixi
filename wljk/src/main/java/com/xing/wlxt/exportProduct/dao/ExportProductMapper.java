package com.xing.wlxt.exportProduct.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xing.wlxt.export.entity.Export;
import com.xing.wlxt.exportProduct.entity.ExportProduct;

@Repository
public interface ExportProductMapper {

	/**
	 * 根据报运信息来删除报运下的货物信息
	 * @param ids
	 */
	public void deleteByExportId(@Param("ids")String[] ids);

	/**
	 * 新增报运下的货物信息
	 * @param ep
	 */
	public void insert(ExportProduct ep);

}
