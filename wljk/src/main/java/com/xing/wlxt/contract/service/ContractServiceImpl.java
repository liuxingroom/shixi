package com.xing.wlxt.contract.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xing.wlxt.contract.dao.ContractMapper;
import com.xing.wlxt.contract.entity.Contract;

@Service
public class ContractServiceImpl implements ContractService{

	@Resource
	ContractMapper contractMapper;
	
	@Override
	public List<Contract> find() {
		List<Contract> dataList=contractMapper.find();
		return dataList;
	}
	
}
