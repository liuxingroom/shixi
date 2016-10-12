package com.xing.wlxt.factory.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xing.wlxt.factory.entity.Factory;

public interface FactoryMapper {
	/**
	 * 获取生产厂家的信息
	 * @return
	 */
	public List<Factory> findFactory();

	/**
	 * 添加新的生产厂家
	 * @param factory
	 */
	public void insert(Factory factory);
	
	/**
	 * 根据id获取生产厂家的信息
	 */
	public Factory findFactoryById(String id);
	/**
	 * 根据id删除用户信息
	 * @param factoryId
	 */
	public void deleteFactoryById(String id);

	/**
	 * 根据id集合来删除生产厂家信息
	 * @param ids
	 */
	public void delete(@Param("ids")String[] ids);
	
	/**
	 * 启用该生产厂家
	 * @param id
	 */
	public void start(String id);

	/**
	 * 停用该生产厂家
	 * @param id
	 */
	public void stop(String id);

	/**
	 * 修改生产厂家信息
	 * @param factory
	 */
	public void update(Factory factory);
}
