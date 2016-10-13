package com.xing.wlxt.contract.service;

import java.util.List;

import com.xing.wlxt.contract.entity.Contract;

public interface ContractService {

	/**
	 * 获取购销合同的list集合
	 * @return
	 */
	List<Contract> find();

	/**
	 * 根据购销合同id来获取购销合同的信息
	 * @param id
	 * @return
	 */
	Contract get(String id);
	
	/**
	 * 根据id集合来删除购销合同 
	 * @param ids
	 */
	public void delete(String[] ids);

}
