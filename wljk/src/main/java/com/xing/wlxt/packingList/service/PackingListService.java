package com.xing.wlxt.packingList.service;

import java.util.List;

import com.xing.wlxt.packingList.entity.PackingList;
import com.xing.wlxt.packingList.search.PackingListSearch;

public interface PackingListService {

	/**
	 * 查询所有的装箱记录
	 * @param object
	 * @return
	 */
	List<PackingList> find(PackingListSearch search);

	/**
	 * 根据id来删除装箱单
	 */
	public void delete(String[] ids);

	/**
	 * 上报报运单
	 * @param id 
	 */
	public void submit(String id);

	/**
	 * 取消上传报运单
	 */
	public void cancel(String id);

	/**
	 * 根据报运单id来获取报运单信息
	 * @param id
	 * @return
	 */
	public PackingList get(String id);

	/**
	 * 修改装箱单信息
	 * @param packingList
	 */
	public void update(PackingList packingList);

}
