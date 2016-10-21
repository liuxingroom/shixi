package com.xing.wlxt.contractExtProduct.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xing.wlxt.contractExtProduct.dao.ContractExtProductMapper;
import com.xing.wlxt.contractExtProduct.entity.ContractExtProduct;

@Service
public class ContractExtProductServiceImpl implements ContractExtProductService {
	
	@Resource
	ContractExtProductMapper contractExtProductMapper;
	
	
	@Override
	public List<ContractExtProduct> findExtByProductId(String contractProductID) {
		List<ContractExtProduct> dataList=contractExtProductMapper.findExtByProductId(contractProductID);
		return dataList;
	}
	
}
