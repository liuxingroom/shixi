package com.xing.wlxt.export.service;

import java.util.List;

import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.export.entity.Export;
import com.xing.wlxt.export.search.ExportSearch;

public interface ExportService {

	/**
	 * 获取购销合同列表
	 * @return
	 */
	List<Contract> getContractList();

	/**
	 * 查询报运信息
	 * @param search
	 * @return
	 */
	List<Export> find(ExportSearch search);

	/**
	 * 通过报运id来获取报运信息
	 * @param id
	 * @return
	 */
    Export get(String id);

    /**
     * 修改报运单信息
     * @param export
     */
	public void update(Export export);

	/**
	 * 根据报运单id来删除报运信息
	 * @param ids 报运单id的集合
	 */
	public void delete(String[] ids);
	
}
