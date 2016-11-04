package com.xing.wlxt.contractHis.service;

import java.util.List;

import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contract.search.ContractSearch;
import com.xing.wlxt.contract.view.ContractVO;

public interface ContractHisService {

	/**
	 * 获取实例购销合同
	 * 
	 * @param object
	 * @return
	 */
	List<Contract> find(ContractSearch search);

	/**
	 * 查看历史的购销合同信息，（包括历史购销合同中的货物信息和附件信息）
	 * @param id
	 * @return
	 */
	ContractVO view(String id);

	/**
	 * 购销合同归档
	 * @param ids
	 */
	public void pigeinhole(String[] ids);

	/**
	 * 取消购销合同归档
	 * @param id
	 */
	public void pigeouthole(String[] ids);

}
