package com.xing.wlxt.factory.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xing.wlxt.factory.dao.FactoryMapper;
import com.xing.wlxt.factory.entity.Factory;

@Service
public class FactoryServiceImpl implements FactoryService{

	@Resource
	FactoryMapper factoryMapper;	
	
	@Override
	public Factory findFactory() {
		Factory factory=factoryMapper.findFactory();
		return factory;
	}
	
}

