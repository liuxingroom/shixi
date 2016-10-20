package com.xing.wlxt.contract.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.contract.view.ContractVO;

@Repository
public interface ContractMapper {
	
	/**
	 * 获取购销合同的list集合
	 * @return
	 */
	public List<Contract> find();

	/**
	 * 根据购销合同id来获取购销合同的信息
	 * @param id
	 * @return
	 */
	public Contract get(String id);

	/**
	 * 根据id集合来删除购销合同信息
	 * @param ids
	 */
	public void delete(@Param("ids")String[] ids);

	/**
	 * 更新购销合同上报状态
	 * @param map
	 */
	public void updateState(Map map);

	/**
	 * 获取购销合同的详细信息（包括购销合同下的货物和附件信息）
	 * @param id
	 * @return
	 */
	public ContractVO view(String id);

	/**
	 * 添加新的购销合同信息
	 * @param contract
	 */
	public void insert(Contract contract);
	
	/**
	 * 修改购销合同信息
	 * @param contract
	 */
	public void update(Contract contract);

}
