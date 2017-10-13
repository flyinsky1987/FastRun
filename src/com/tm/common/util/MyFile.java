package com.tm.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class MyFile {
	/**
	 * @param path			文件路径
	 * @param fileName		文件名字
	 * @param message		文件内容
	 */
	public static void create(String path,String fileName,String message){
		try{
			File file=new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			File json=new File(file,fileName);
			if(!json.exists()){
				json.createNewFile();
			}
			FileWriter writer=new FileWriter(file+"/"+fileName);
			
			 BufferedWriter bufferWritter = new BufferedWriter(writer);
             bufferWritter.write(message);
             bufferWritter.close();

			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
