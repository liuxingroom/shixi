package com.xing.wlxt.contractExtProduct.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xing.wlxt.contractExtProduct.entity.ContractExtProduct;

@Repository
public interface ContractExtProductMapper {
	
	/**
	 * 根据购销合同id来删除附件信息
	 * @param ids
	 */
	public void delete(@Param("ids")String[] ids);

	/**
	 * 根据货物id 来删除附件信息
	 * @param ids
	 */
	public void deleteExtByContractProductId(@Param("ids")String[] ids);

	/**
	 * 根据货物id来获取该货物下的附件信息
	 * @param contractProductID
	 * @return
	 */
	public List<ContractExtProduct> findExtByProductId(String contractProductID);

}
