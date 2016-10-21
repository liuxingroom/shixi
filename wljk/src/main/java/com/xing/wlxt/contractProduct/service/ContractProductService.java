package com.xing.wlxt.contractProduct.service;

import java.util.List;

import com.xing.wlxt.contractProduct.entity.ContractProduct;

public interface ContractProductService {

	/**
	 * 根据购销合同的id 来获取该购销合同下的货物信息
	 * @param contractId
	 * @return
	 */
	List<ContractProduct> findContractProductByContractId(String contractId);

	/**
	 * 新增购销合同中的货物信息
	 * @param contractProduct
	 */
	public void insert(ContractProduct contractProduct);

	/**
	 * 货物id来获取货物信息
	 * @param id
	 * @return
	 */
	ContractProduct findContractProductById(String id);
	
	/**
	 * 更新货物信息
	 * @param contractProduct
	 */
	public void update(ContractProduct contractProduct);

	/**
	 * 根据货物id来删除货物信息
	 * @param id
	 */
	public void deleteById(String id);
	
}
