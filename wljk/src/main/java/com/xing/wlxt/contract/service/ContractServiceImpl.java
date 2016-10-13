package com.xing.wlxt.contract.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xing.wlxt.contract.dao.ContractMapper;
import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contractExtProduct.dao.ContractExtProductMapper;
import com.xing.wlxt.contractProduct.dao.ContractProductMapper;

@Service
public class ContractServiceImpl implements ContractService{

	@Resource
	ContractMapper contractMapper;
	
	@Resource
	ContractProductMapper contractProductMapper;
	
	@Resource
	ContractExtProductMapper contractExtProductMapper;
	
	@Override
	public List<Contract> find() {
		List<Contract> dataList=contractMapper.find();
		return dataList;
	}

	@Override
	public Contract get(String id) {
		Contract obj=contractMapper.get(id);
		return obj;
	}

	@Override
	public void delete(String[] ids) {
		contractExtProductMapper.delete(ids);    //删除购销合同下的附件信息
		contractProductMapper.delete(ids);       //删除购销合同下的货物信息
		contractMapper.delete(ids);              //删除购销合同
	}
	
}
