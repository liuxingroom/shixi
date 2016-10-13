package com.xing.wlxt.contract.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xing.wlxt.contract.entity.Contract;

@Repository
public interface ContractMapper {
	
	/**
	 * 获取购销合同的list集合
	 * @return
	 */
	public List<Contract> find();

	/**
	 * 根据购销合同id来获取购销合同的信息
	 * @param id
	 * @return
	 */
	public Contract get(String id);

	/**
	 * 根据id集合来删除购销合同信息
	 * @param ids
	 */
	public void delete(@Param("ids")String[] ids);

}
