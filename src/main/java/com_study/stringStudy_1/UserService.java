package com_study.stringStudy_1;

import java.util.List;

import javax.swing.text.ChangedCharSetException;

public class UserService {

	UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void upgradeLevels() {
		List<User> users = userDao.getAll();
		for (User user : users) {
			Boolean changed = null;
			if (user.getLevelInt() == Level.BASIC && user.getLogin() >= 50) {
				user.setLevelInt(Level.SILVER);
				changed = true;
			} else if (user.getLevelInt() == Level.SILVER && user.getRecommend() >= 30) {
				user.setLevelInt(Level.GOLD);
				changed = true;

			} else if (user.getLevelInt() == Level.GOLD) {
				changed = false;
			} else {
				changed = false;
			}
			if (changed) {
				userDao.update(user);
			}

		}
	}

	
	public void add(User user) {
		if(user.getLevelInt() == null) user.setLevelInt(Level.BASIC);
		userDao.add(user);
	}
}