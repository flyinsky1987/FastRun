package com.tm.test.controller;

import com.tm.common.base.BaseController;
import com.tm.common.base.BaseControllerInterface;
import com.tm.test.dao.PersonDao;

public class PersonController extends BaseController implements BaseControllerInterface{
	public void query(){
		renderText(gson.toJson(PersonDao.dao.query()));
	}
	public void save(){
		getModel(PersonDao.class,"person").save();
		query();
	}
	public void delete() {
		getModel(PersonDao.class,"person").delete();
		renderText("操作成功！");
	}
	public void update() {
		getModel(PersonDao.class,"person").update();
	} 
	public void upload(){
		String newFile=super.uploadFile();
		super.renderText(newFile);
	}
}