package com.xing.wlxt.exportProduct.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportProductMapper {

	/**
	 * 根据报运信息来删除报运下的货物信息
	 * @param ids
	 */
	public void deleteByExportId(@Param("ids")String[] ids);

}
