package com.xing.wlxt.contractHis.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xing.wlxt.common.springdao.SqlDao;
import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contract.search.ContractSearch;
import com.xing.wlxt.contract.view.ContractVO;
import com.xing.wlxt.contractHis.dao.ContractHisMapper;
import com.xing.wlxt.utils.UtilFuns;

@Service
public class ContractHisServiceImpl implements ContractHisService{

	@Resource
	ContractHisMapper contractHisMapper;
	
	@Resource
	SqlDao sqlDao;
	
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
		
		sqlDao.batchSQL(this.doData(ids,"","_his"));                      //批量执行sql
	}

	

	@Override
	public void pigeouthole(String[] ids) {
		sqlDao.batchSQL(this.doData(ids, "_his", ""));
	}
	
	/**
	 * 拼接购销合同和购销合同历史表数据相互装换信息的sql
	 * @param ids 购销合同的id集合
	 * @param source 复制的源表
	 * @param target 复制的目的表
	 * @return
	 */
	private String[] doData(String[] ids, String source, String target) {
		StringBuffer sBuf=new StringBuffer();
		String inStr=UtilFuns.joinStr(ids,"'","'",",");	
		//将购销合同中的数据复制到到购销合同的历史表中
		sBuf.append("insert into contract").append(target).append("_c( select * from contract").append(source).append("_c where contract_id in (").append(inStr).append("));"); 
		//将购销合同所属的货物信息复制到购销合同历史表所属的货物表中
		sBuf.append("insert into contract_product").append(target).append("_c(select * from contract_product").append(source).append("_c  where contract_id in (").append(inStr).append("));");
		//将购销合同所属的附件信息复制到购销合同历史表的所对应的附件表中
		sBuf.append("insert into ext_cproduct").append(target).append("_c(select * from ext_cproduct").append(source).append("_c  where ext_cproduct_id in (select contract_product_id from contract_product").append(source).append("_c  where contract_id in(").append(inStr).append(")));");
		
		//删除购销合同中的附件信息
		sBuf.append("delete from ext_cproduct").append(source).append("_c where contract_product_id in (select contract_product_id from contract_product").append(source).append("_c where contract_id in (").append(inStr).append("));");
		//删除购销合同的货物信息
		sBuf.append("delete from contract_product").append(source).append("_c where contract_id in (").append(inStr).append(");");
		//删除购销合同的信息
		sBuf.append("delete from contract").append(source).append("_c where contract_id in (").append(inStr).append(");");
		return sBuf.toString().split(";");
	}
	
}
