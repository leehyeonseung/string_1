package com_study.stringStudy_1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	User user;
	
	@Before
	public void setUp() {
		user = new User();
	}
	
	@Test()
	public void upgradeLevel() {
		Level[] levels = Level.values();
		for(Level level : levels) {
			if(level.nextLevel() == null) continue;
			user.setLevelInt(level);
			user.upgradeLevel();
			assertThat(user.getLevelInt(), is(level.nextLevel()));
		}
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void cannotUpgradeLevel() {
		Level[] levels = Level.values();
		for(Level level : levels) {
			if(level.nextLevel() != null) continue;
			user.setLevelInt(level);
			user.upgradeLevel();
		}
	}
}
