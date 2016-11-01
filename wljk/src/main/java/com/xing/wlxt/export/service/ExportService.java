package com.xing.wlxt.export.service;

import java.util.List;

import com.xing.wlxt.contract.entity.Contract;
import com.xing.wlxt.export.entity.Export;
import com.xing.wlxt.export.search.ExportSearch;
import com.xing.wlxt.export.view.ExportVO;

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

	/**
	 * 报运单上报
	 * @param ids 
	 */
	public void submit(String[] ids);

	/**
	 * 报运单取消上报
	 * @param ids 
	 */
	public void cancel(String[] ids);
	
	/**
	 * 根据报运单id来获取报单信息（包括报运单中的货物信息，以及附件信息）
	 * @param id
	 * @return
	 */
	public ExportVO view(String id);

	/**
	 * 想报运单中插入一条记录
	 * @param contractIds
	 */
	public void insert(String[] contractIds);
	
}
