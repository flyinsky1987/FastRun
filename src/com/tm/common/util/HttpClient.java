package com.tm.common.util;

import java.io.BufferedReader; 
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson; 

/**
 * @author fuwei 
 * 网络访问
 */
public class HttpClient {
	
	final static Gson gson=new Gson();
	/**
	 * 网络消息发送，回执信息
	 */
	public static String sendMsg(String msg){
		String surl="msg";
		return getCode(surl);
		
	}
	protected static String getCode(String urlString) {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(3*1000);
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			
			for (String line = null; (line = reader.readLine()) != null;)
				sb.append(line + "\n");

			reader.close(); 
		} catch (Exception e) {
			sb.append("指向消息服务器的网络超时！");
		}  
		return sb.toString();
	} 
}
