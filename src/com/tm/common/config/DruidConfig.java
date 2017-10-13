package com.tm.common.config;  

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.render.ViewType;
import com.tm.common.util.Parames;

/**
 * 系统主控配置
 */
public class DruidConfig extends JFinalConfig {

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("jdbc.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setError404View("/index.jsp");
		me.setError500View("/index.jsp");
		me.setViewType(ViewType.JSP);
		 
		Parames.memcached_ip=PropKit.get("memcached_ip","127.0.0.1");
		Parames.memcached_port=PropKit.getInt("memcached_port",11211);
		Parames.memcached_time=PropKit.getInt("memcached_time",100);
		 
		Parames.filePath=PropKit.get("filePath","d:/tmlbs");
		
		MyInit.init();
	 
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {  
		MyInit.configRoute(me);
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		 
		
		/**
		 * mysql 数据源
		 * */
		{
			// 配置druid数据库连接池插件
			DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
			dp.setDriverClass("com.mysql.jdbc.Driver");
			// 连接池配置,后面的数字是没有配置的时候，默认值
			dp.setInitialSize(PropKit.getInt("initialSize", 10));
			dp.setMinIdle(PropKit.getInt("minIdle", 10));
			dp.setMaxActive(PropKit.getInt("maxActive", 30)); 
			dp.addFilter(new StatFilter());
			WallFilter wall = new WallFilter();
			wall.setDbType("mysql");
			dp.addFilter(wall);
			me.add(dp); 
			// 配置ActiveRecord插件
			ActiveRecordPlugin arp = new ActiveRecordPlugin(dp); 
			arp.setShowSql(false);
			arp.setDialect(new MysqlDialect());
			// 设置字段小写
			arp.setContainerFactory(new CaseInsensitiveContainerFactory(true)); 
			me.add(arp);
			 
			 
			MyInit.addMapping(arp);
		}
		
	}

	/**
	 * 配置 action 拦截器
	 */
	public void configInterceptor(Interceptors me) {  
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) { 
		DruidStatViewHandler dsvh = new DruidStatViewHandler("/druid/");
		me.add(dsvh);   
	}
}
