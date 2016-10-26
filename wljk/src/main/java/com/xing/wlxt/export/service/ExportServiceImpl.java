package com.xing.wlxt.export.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xing.wlxt.contract.dao.ContractMapper;
import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contract.search.ContractSearch;

@Service
public class ExportServiceImpl implements ExportService{
	
	@Resource
	ContractMapper contractMapper;
	
	@Override
	public List<Contract> getContractList() {
		
		
		ContractSearch search=new ContractSearch();  //封装查询条件
		contractMapper.find(search);//获取已上报的购销合同集合
		return null;
	}

}
