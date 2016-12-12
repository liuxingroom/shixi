package com.xing.wlxt.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xing.wlxt.export.dao.ExportMapper;

public class UserTest {
	
	/**
	 * 通过手动加载配置文件（applicationContext.xml）  然后测试项目搭建是否走通
	 */
	@Test
	public void userTest(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		ExportMapper chartController=applicationContext.getBean(ExportMapper.class);
		System.out.println(chartController.toString());
	}
}
