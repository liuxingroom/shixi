package com.xing.wlxt.contract.service;

import java.util.List;

import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contract.search.ContractSearch;
import com.xing.wlxt.contract.view.ContractVO;

public interface ContractService {

	/**
	 * 获取购销合同的list集合
	 * @param object 
	 * @return
	 */
	List<Contract> find(ContractSearch search);

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

	/**
	 * 上报购销合同
	 * @param ids
	 */
	public void submit(String[] ids);
	
	/**
	 * 取消已上报的购销合同
	 */
	public void cancel(String [] ids);

	/**
	 * 查看购销合同的详细信息（包括购销合同下的货物和附件信息）
	 * @param id
	 * @return
	 */
	public ContractVO view(String id);

	/**
	 * 添加信息购销合同信息
	 * @param contract
	 */
	public void insert(Contract contract);

	/**
	 * 修改购销合同
	 * @param contract
	 */
	public void update(Contract contract);

}
