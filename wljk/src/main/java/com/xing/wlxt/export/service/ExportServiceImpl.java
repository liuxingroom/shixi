package com.xing.wlxt.export.service;

import java.util.List;

import javax.annotation.Resource;

import org.aspectj.weaver.Utils;
import org.springframework.stereotype.Service;

import com.xing.wlxt.contract.dao.ContractMapper;
import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contract.search.ContractSearch;
import com.xing.wlxt.contract.view.ContractVO;
import com.xing.wlxt.export.dao.ExportMapper;
import com.xing.wlxt.export.entity.Export;
import com.xing.wlxt.export.search.ExportSearch;
import com.xing.wlxt.export.view.ExportVO;
import com.xing.wlxt.exportExtProduct.dao.ExportExtProductMapper;
import com.xing.wlxt.exportProduct.dao.ExportProductMapper;
import com.xing.wlxt.utils.UtilFuns;

@Service
public class ExportServiceImpl implements ExportService{
	
	@Resource
	ContractMapper contractMapper;
	
	@Resource
	ExportMapper exportMapper;
	
	@Resource
	ExportExtProductMapper exportExtProductMapper;
	
	@Resource
	ExportProductMapper exportProductMapper;
	
	@Override
	public List<Contract> getContractList() {
		ContractSearch search=new ContractSearch();  //封装查询条件
		search.setState(1);
		List<Contract> dataList=contractMapper.find(search);//获取已上报的购销合同集合
		return dataList;
	}

	@Override
	public List<Export> find(ExportSearch search) {
		List<Export> dataList=exportMapper.find(search);
		return dataList;
	}

	@Override
	public Export get(String id) {

		Export export=exportMapper.get(id);
		return export;
	}

	@Override
	public void update(Export export) {
		exportMapper.update(export);
	}

	@Override
	public void delete(String[] ids) {
		
		exportExtProductMapper.deleteByExportId(ids);  //根据报运id来删除报运下的附件信息
		
		exportProductMapper.deleteByExportId(ids);   //根据报运id来删除报运下的货物信息
 		exportMapper.delete(ids);    //删除报运单信息
	}

	@Override
	public void submit(String [] ids) {
		for(String id:ids){
			Export export=new Export();
			export.setId(id);
			export.setState(1);
			exportMapper.update(export);
		}
		
	}

	@Override
	public void cancel(String [] ids) {
		for(String id:ids){
			Export export=new Export();
			export.setId(id);
			export.setState(0);
			exportMapper.update(export);
		}
	}

	@Override
	public ExportVO view(String id) {
		ExportVO obj=exportMapper.view(id);
		return obj;
	}

	@Override
	public void insert(String[] contractIds) {
		/*
		 * 步骤：
		 * 1、根据合同id获得合同对象，获取合同号
		 * 2、将合同下的货物信息搬家到报运下的货物表中
		 * 3、将合同下的附件信息搬家到报运下的附件表中
		 */
		
		//拼接合同号，报运号
		
		String contractNos="";
		for(int i=0;i<contractIds.length;i++){
			ContractVO contractVO=contractMapper.view(contractIds[i]);
			contractNos+=contractVO.getContractNO()+",";
		}
		contractNos=UtilFuns.delLastChar(contractNos);         //使用工具类删除最后一个字符
		
	}

}
