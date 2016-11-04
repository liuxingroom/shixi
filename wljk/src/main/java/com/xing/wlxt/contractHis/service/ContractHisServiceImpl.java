package com.xing.wlxt.contractHis.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contract.search.ContractSearch;
import com.xing.wlxt.contract.view.ContractVO;
import com.xing.wlxt.contractHis.dao.ContractHisMapper;

@Service
public class ContractHisServiceImpl implements ContractHisService{

	@Resource
	ContractHisMapper contractHisMapper;
	
	@Override
	public List<Contract> find(ContractSearch search) {
		List<Contract> dataList=contractHisMapper.find(search);
		return dataList;
	}

	@Override
	public ContractVO view(String id) {
		ContractVO obj=contractHisMapper.view(id);
		return obj;
	}

	@Override
	public void pigeinhole(String[] ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pigeouthole(String[] id) {
		// TODO Auto-generated method stub
		
	}
	
}
