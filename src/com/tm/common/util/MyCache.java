package com.tm.common.util;

import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;
 
 

/**
 * @author 符威
 * @email:15038067939@139.com 时间：2014-5-6 - 下午02:01:01
 */
public class MyCache {
	public static String memCachedServer;
	public static int memCachedExp;
	public static MemcachedClient mc;
	/**
	 * 单一缓存服务器
	 * */
	public static String ip;
	public static int port;

	 
	@SuppressWarnings("static-access")
	public MyCache() {
		this.ip = Parames.memcached_ip;
		this.memCachedExp = Parames.memcached_time;
		this.port = Parames.memcached_port;
		if (mc == null) {
			try {
				mc = new MemcachedClient(new InetSocketAddress(ip, port));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 缓存设置
	 * */
	public static void set(String key, Object value) {
		try {
			if (key != null && value != null) {
				mc.set(key, memCachedExp, value);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 自定义缓存时间
	 * */
	public static void set(String key, Object value, int time) {
		try {
			if (key != null && value != null) {
				mc.set(key, time, value);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 缓存读取
	 * */
	public static Object get(String key) {
		Object obj = null;
		try {
			if (key != null) {
				obj = mc.get(key);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	/**
	 * 是否为空
	 * */
	public static boolean isNull(Object obj) {
		if (obj != null && obj.toString().trim().length() > 0) {
			return false;
		} else {
			return true;
		}
	}
}
