package com.xing.wlxt.contractProduct.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xing.wlxt.contractProduct.dao.ContractProductMapper;
import com.xing.wlxt.contractProduct.entity.ContractProduct;
import com.xing.wlxt.utils.WLXTUtils;

@Service
public class ContractProductServiceImpl implements ContractProductService{

	@Resource
	ContractProductMapper contractProductMapper;
	
	@Override
	public List<ContractProduct> findContractProductByContractId(
			String contractId) {
		List<ContractProduct> dataList=contractProductMapper.findContractProductByContractId(contractId);
		return dataList;
	}
	
	
	@Override
	public void insert(ContractProduct contractProduct) {
		contractProduct.setId(WLXTUtils.createPrimary());
		contractProductMapper.insert(contractProduct);
	}


	@Override
	public ContractProduct findContractProductById(String id) {
		ContractProduct obj=contractProductMapper.findContractProductById(id);
		return obj;
	}

}
