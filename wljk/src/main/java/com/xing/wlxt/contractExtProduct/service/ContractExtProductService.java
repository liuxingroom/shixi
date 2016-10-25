package com.xing.wlxt.contractExtProduct.service;

import java.util.List;

import com.xing.wlxt.contractExtProduct.entity.ContractExtProduct;

public interface ContractExtProductService {

	/**
	 * 根据货物id来获取该货物下的所有附件集合
	 * @param contractProductID
	 * @return
	 */
	List<ContractExtProduct> findExtByProductId(String contractProductID);

	/**
	 * 新增附件信息
	 * @param extProduct
	 */
	public void insert(ContractExtProduct extProduct);

}
