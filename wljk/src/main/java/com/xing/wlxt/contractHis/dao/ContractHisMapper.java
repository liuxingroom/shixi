package com.xing.wlxt.contractHis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contract.search.ContractSearch;
import com.xing.wlxt.contract.view.ContractVO;

@Repository
public interface ContractHisMapper {

	/**
	 * 获取所有的历史购销合同信息
	 * @param search
	 * @return
	 */
	List<Contract> find(ContractSearch search);

	/**
	 * 查看历史的购销合同信息，（包括历史购销合同中的货物信息和附件信息）
	 * @param id
	 * @return
	 */
	ContractVO view(String id);

}
