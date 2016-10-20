package com.xing.wlxt.contract.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xing.wlxt.contract.dao.ContractMapper;
import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contract.view.ContractVO;
import com.xing.wlxt.contractExtProduct.dao.ContractExtProductMapper;
import com.xing.wlxt.contractProduct.dao.ContractProductMapper;
import com.xing.wlxt.utils.WLXTUtils;

@Service
public class ContractServiceImpl implements ContractService{

	@Resource
	ContractMapper contractMapper;
	
	@Resource
	ContractProductMapper contractProductMapper;
	
	@Resource
	ContractExtProductMapper contractExtProductMapper;
	
	@Override
	public List<Contract> find() {
		List<Contract> dataList=contractMapper.find();
		return dataList;
	}

	@Override
	public Contract get(String id) {
		Contract obj=contractMapper.get(id);
		return obj;
	}

	@Override
	public void delete(String[] ids) {
		contractExtProductMapper.delete(ids);    //删除购销合同下的附件信息
		contractProductMapper.delete(ids);       //删除购销合同下的货物信息
		contractMapper.delete(ids);              //删除购销合同
	}

	@Override
	public void submit(String[] ids) {
		Map map=new HashMap();
		map.put("state",1);                     //已上报
		map.put("ids", ids);
		contractMapper.updateState(map);
	}

	@Override
	public void cancel(String[] ids) {
		Map map=new HashMap();
		map.put("state", 0);                       //草稿
		map.put("ids", ids);
		contractMapper.updateState(map);
	}

	@Override
	public ContractVO view(String id) {
		ContractVO obj=contractMapper.view(id);
		return obj;
	}

	@Override
	public void insert(Contract contract) {
		contract.setId(WLXTUtils.createPrimary());
		contract.setState(0);                        //0草稿 1已上报
		contractMapper.insert(contract);
	}

	@Override
	public void update(Contract contract) {
		contractMapper.update(contract);
		System.out.println();
	}
	
	
}
