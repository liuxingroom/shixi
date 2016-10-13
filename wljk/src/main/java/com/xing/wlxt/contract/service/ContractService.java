package com.xing.wlxt.contract.service;

import java.util.List;

import com.xing.wlxt.contract.entity.Contract;

public interface ContractService {

	/**
	 * 获取购销合同的list集合
	 * @return
	 */
	List<Contract> find();

}
