package com.xing.wlxt.contractExtProduct.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.xing.wlxt.contractExtProduct.dao.ContractExtProductMapper;
import com.xing.wlxt.contractExtProduct.entity.ContractExtProduct;
import com.xing.wlxt.utils.UtilFuns;
import com.xing.wlxt.utils.WLXTUtils;

@Service
public class ContractExtProductServiceImpl implements ContractExtProductService {
	
	@Resource
	ContractExtProductMapper contractExtProductMapper;
	
	
	@Override
	public List<ContractExtProduct> findExtByProductId(String contractProductID) {
		List<ContractExtProduct> dataList=contractExtProductMapper.findExtByProductId(contractProductID);
		return dataList;
	}


	@Override
	public void insert(ContractExtProduct extProduct) {
		extProduct.setId(WLXTUtils.createPrimary());         //设置附件的主键
		//给附件信息添加总金额字段
		if(UtilFuns.isNotEmpty(extProduct.getPrice()) && UtilFuns.isNotEmpty(extProduct.getcNumber())){
			//如果附件的数量和单价不为空    则计算附件的总金额
			extProduct.setAmount(extProduct.getPrice()*extProduct.getcNumber());
		}
		
 		contractExtProductMapper.insert(extProduct);     //新增
	}


	@Override
	public ContractExtProduct get(String id) {
		ContractExtProduct obj=contractExtProductMapper.get(id);
		return obj;
	}


	@Override
	public void update(ContractExtProduct extProduct) {
		contractExtProductMapper.update(extProduct);
	}


	@Override
	public void deleteById(String id) {
		contractExtProductMapper.deleteById(id);
	}
	
}
