package com.xing.wlxt.contract.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xing.wlxt.contract.entity.Contract;

@Repository
public interface ContractMapper {
	
	/**
	 * 获取购销合同的list集合
	 * @return
	 */
	List<Contract> find();

}
