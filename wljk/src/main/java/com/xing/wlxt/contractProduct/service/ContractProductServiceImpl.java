package com.xing.wlxt.contractProduct.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xing.wlxt.contractExtProduct.dao.ContractExtProductMapper;
import com.xing.wlxt.contractProduct.dao.ContractProductMapper;
import com.xing.wlxt.contractProduct.entity.ContractProduct;
import com.xing.wlxt.utils.UtilFuns;
import com.xing.wlxt.utils.WLXTUtils;

@Service
public class ContractProductServiceImpl implements ContractProductService{

	@Resource
	ContractProductMapper contractProductMapper;
	
	@Resource
	ContractExtProductMapper contractExtProductMapper;
	
	@Override
	public List<ContractProduct> findContractProductByContractId(
			String contractId) {
		List<ContractProduct> dataList=contractProductMapper.findContractProductByContractId(contractId);
		return dataList;
	}
	
	
	@Override
	public void insert(ContractProduct contractProduct) {
		contractProduct.setId(WLXTUtils.createPrimary());
		//如果货物中数量和单价的字段如果不为空    就计算货物总金额
		if(UtilFuns.isNotEmpty(contractProduct.getPrice()) && UtilFuns.isNotEmpty(contractProduct.getcNumber())){
			//设置货物总金额
			contractProduct.setAmount(contractProduct.getPrice()*contractProduct.getcNumber());
		}
		contractProductMapper.insert(contractProduct);
	}


	@Override
	public ContractProduct findContractProductById(String id) {
		ContractProduct obj=contractProductMapper.findContractProductById(id);
		return obj;
	}


	@Override
	public void update(ContractProduct contractProduct) {
		contractProductMapper.update(contractProduct);
	}


	@Override
	public void deleteById(String id) {
		String[] ids={id};
		contractExtProductMapper.deleteExtByContractProductId(ids);      //根据货物id 删除该货物下的所有附件信息
		contractProductMapper.deleteById(ids);
	}

}
