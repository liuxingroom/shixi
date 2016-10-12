package com.xing.wlxt.factory.service;

import java.util.List;

import com.xing.wlxt.factory.entity.Factory;

public interface FactoryService {

	/**
	 * 获取生产厂家的相关信息
	 * @return
	 */
	public List<Factory> findFactory();

	/**
	 * 添加新的生产厂家的信息
	 */
    public void insert(Factory factory);
	
	/**
	 * 根据id获取生产厂家的信息
	 */
	public Factory findFactoryById(String factoryId);

	/**
	 * 根据id删除用户信息（其实就是修改厂家的状态，即：state值设置为0）
	 * @param factoryId
	 */
	public void deleteFactoryById(String factoryId);

	/**
	 * 根据id集合来删除多条生产厂家信息
	 * @param ids
	 */
	public void delete(String[] ids);
	
	/**
	 * 启用生产厂家
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
