package com.xing.wlxt.factory.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xing.wlxt.factory.dao.FactoryMapper;
import com.xing.wlxt.factory.entity.Factory;
import com.xing.wlxt.utils.WLXTUtils;

@Service
public class FactoryServiceImpl implements FactoryService{
	Logger logger=Logger.getLogger(FactoryServiceImpl.class);
	@Resource
	FactoryMapper factoryMapper;	
	
	@Override
	public List<Factory> findFactory() {
		List<Factory> factorys=factoryMapper.findFactory();
		return factorys;
	}

	@Override
	public void insert(Factory factory) {
		factory.setId(WLXTUtils.createPrimary());       //设置主键id
		factory.setState(1);                          //新增的生产厂家默认是启用状态
		factoryMapper.insert(factory);
	}

	@Override
	public Factory findFactoryById(String id) {
		return factoryMapper.findFactoryById(id);
	}

	@Override
	public void deleteFactoryById(String id) {
		factoryMapper.deleteFactoryById(id);
	}
	
	@Override
    public void delete(String []ids){
		factoryMapper.delete(ids);
	}

	@Override
	public void start(String id) {
		factoryMapper.start(id);
	}

	@Override
	public void stop(String id) {
		factoryMapper.stop(id);
	}

	@Override
	public void update(Factory factory) {
		factoryMapper.update(factory);
	}
	
}

