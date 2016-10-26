package com.xing.wlxt.export.service;

import java.util.List;

import com.xing.wlxt.contract.entity.Contract;

public interface ExportService {

	/**
	 * 获取购销合同列表
	 * @return
	 */
	List<Contract> getContractList();
	
}
