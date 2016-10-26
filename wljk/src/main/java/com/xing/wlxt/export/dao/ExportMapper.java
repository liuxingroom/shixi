package com.xing.wlxt.export.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xing.wlxt.export.entity.Export;
import com.xing.wlxt.export.search.ExportSearch;

public interface ExportMapper {

	/**
	 * 查看报运表的信息
	 * @param search
	 * @return
	 */
	List<Export> find(ExportSearch search);

	/**
	 * 通过报运单id来获取报运信息
	 * @param id
	 * @return
	 */
	Export get(@Param("id")String id);

	/**
	 * 更新报运信息
	 * @param export
	 */
	public void update(Export export);
	
	/**
	 * 根据报运id来删除报运信息
	 * @param ids
	 */
	public void delete(@Param("ids")String[] ids);
	
}
