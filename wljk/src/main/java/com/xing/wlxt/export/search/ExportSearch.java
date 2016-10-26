package com.xing.wlxt.export.search;

/**
 *	报运下的查询条件 
 */
public class ExportSearch {
	/**
	 * 报运单状态 0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务
	 */
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
}
