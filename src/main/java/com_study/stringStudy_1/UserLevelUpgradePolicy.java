package com_study.stringStudy_1;

public interface UserLevelUpgradePolicy {
	
	public boolean canUpgradeLevel(User user);
	
    public void upgradeLevel(User user);
	
	public void setUserDao(UserDao userDao);
	
	public void add(User user);
	
	public void upgradeLevels() throws Exception;
}
