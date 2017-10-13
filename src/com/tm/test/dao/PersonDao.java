package com.tm.test.dao;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;

@SuppressWarnings("serial")
public class PersonDao extends Model<PersonDao>{
	public static final PersonDao dao=new PersonDao();
	public List<Record> query(){
		return Db.find("select id,name,sex,address,phone from person");
	}
}
