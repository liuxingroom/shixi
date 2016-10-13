package com.xing.wlxt.contractProduct.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractProductMapper {

	/**
	 * 根据购销合同id来删除合同下的附件信息
	 * @param ids
	 */
	public void delete(@Param("ids")String[] ids);

}
