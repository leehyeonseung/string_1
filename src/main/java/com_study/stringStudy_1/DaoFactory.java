package com_study.stringStudy_1;

public class DaoFactory {

	public UserDao userDao() {
		UserDao userDao = new UserDao(new DConnectionMaker());
		return userDao;
	}
}
