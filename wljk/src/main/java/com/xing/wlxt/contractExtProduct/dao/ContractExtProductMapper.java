package com.xing.wlxt.contractExtProduct.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

}
