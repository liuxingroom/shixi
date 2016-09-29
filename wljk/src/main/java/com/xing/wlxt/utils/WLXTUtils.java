package com.xing.wlxt.utils;

import java.util.UUID;

/**
 * 工具类
 *
 */
public class WLXTUtils {
	/**
	 * 用于生产主键
	 */
	public static String createPrimary(){
		return UUID.randomUUID().toString();
	}
}
