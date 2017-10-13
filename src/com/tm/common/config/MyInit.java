package com.tm.common.config;

import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.tm.common.util.MyCache;
import com.tm.test.controller.PersonController;
import com.tm.test.dao.PersonDao;

/**
 * @author fuwei
 * 配置入口
 */
public class MyInit {
	/**
	 * @param me
	 * 配置控制器路由
	 */
	public static void configRoute(Routes me){
		me.add("test/person",PersonController.class);
	}
	/**
	 * @param arp
	 * 配置数据库实例 - 持久层配置
	 */
	public static void addMapping(ActiveRecordPlugin arp){
		arp.addMapping("account", PersonDao.class);
	}
	/**
	 * 全局初始化处理
	 */
	public static void init(){
		new MyCache();
	} 
}
