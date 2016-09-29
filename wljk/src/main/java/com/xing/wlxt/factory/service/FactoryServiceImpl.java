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
	public void saveFactory(Factory factory) {
		factory.setFactoryId(WLXTUtils.createPrimary());
		factoryMapper.saveFactory(factory);
	}

	@Override
	public Factory findFactoryById(String factoryId) {
		return factoryMapper.findFactoryById(factoryId);
	}

	@Override
	public void deleteFactoryById(String factoryId) {
		factoryMapper.deleteFactoryById(factoryId);
	}
	
}

