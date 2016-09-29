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
	 * 保存生产厂家的信息
	 */
    public void saveFactory(Factory factory);
	
	/**
	 * 根据id获取生产厂家的信息
	 */
	public Factory findFactoryById(String factoryId);

	/**
	 * 根据id删除用户信息（其实就是修改厂家的状态，即：state值设置为0）
	 * @param factoryId
	 */
	public void deleteFactoryById(String factoryId);

}
