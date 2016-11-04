package com.xing.wlxt.packingList.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xing.wlxt.packingList.entity.PackingList;
import com.xing.wlxt.packingList.search.PackingListSearch;

@Repository
public interface PackingListMapper {

	/**
	 * 查询所有的装箱信息
	 * @param search
	 * @return
	 */
	List<PackingList> find(PackingListSearch search);

	/**
	 * 更新装箱单
	 * @param packingList
	 */
	public void update(PackingList packingList);

	/**
	 * 根据id来删除报运信息
	 * @param ids
	 */
	public void delete(@Param("ids")String[] ids);

	/**
	 * 通过报运单id来获取报运信息
	 * @param id
	 * @return
	 */
	public PackingList get(String id);
	
}
