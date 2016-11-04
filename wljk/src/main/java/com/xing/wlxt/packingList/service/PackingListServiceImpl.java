package com.xing.wlxt.packingList.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xing.wlxt.packingList.dao.PackingListMapper;
import com.xing.wlxt.packingList.entity.PackingList;
import com.xing.wlxt.packingList.search.PackingListSearch;

@Service
public class PackingListServiceImpl implements PackingListService{

	@Resource
	PackingListMapper packingListMapper;
	
	@Override
	public List<PackingList> find(PackingListSearch search) {
		List<PackingList> dataList=packingListMapper.find(search);
		return dataList;
	}

	@Override
	public void delete(String[] ids) {
		packingListMapper.delete(ids);
	}

	@Override
	public void submit(String id) {
		PackingList packingList=new PackingList();
		packingList.setState(1);                       //设置报运单上报
		packingList.setId(id);
		packingListMapper.update(packingList);
	}

	@Override
	public void cancel(String id) {
		PackingList packingList=new PackingList();
		packingList.setState(0);                       //上报取消
		packingList.setId(id);
		packingListMapper.update(packingList);
	}

	@Override
	public PackingList get(String id) {
		PackingList obj=packingListMapper.get(id);     //根据id来获取报运单
		return obj;
	}

	@Override
	public void update(PackingList packingList) {
		packingListMapper.update(packingList);
	}

}
