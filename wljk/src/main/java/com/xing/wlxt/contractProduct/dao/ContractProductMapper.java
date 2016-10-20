package com.xing.wlxt.contractProduct.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xing.wlxt.contractProduct.entity.ContractProduct;

@Repository
public interface ContractProductMapper {

	/**
	 * 根据购销合同id来删除合同下的附件信息
	 * @param ids
	 */
	public void delete(@Param("ids")String[] ids);

	/**
	 * 通过购销合同id来获取该购销合同下所有的货物信息
	 * @param contractId
	 * @return
	 */
	public List<ContractProduct> findContractProductByContractId(
			@Param("contractId")String contractId);

	/**
	 * 新增购销合同中的货物信息
	 * @param contractProduct
	 */
	public void insert(ContractProduct contractProduct);

	/**
	 * 通过货物id来获取获取信息
	 * @param id
	 * @return
	 */
	public ContractProduct findContractProductById(String id);

}
