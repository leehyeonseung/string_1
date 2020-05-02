package com_study.stringStudy_1;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;

import static com_study.stringStudy_1.UserService.MIN_LOGCOUNT_FOR_SILVER;
import static com_study.stringStudy_1.UserService.MIN_LOGCOUNT_FOR_GOLD;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserServiceTest {
	@Autowired
	UserService userService;

	@Autowired
	UserDao userDao;

	List<User> users;

	@Autowired
	DataSource dataSource;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	@Autowired
	MailSender mailSender;
	
	
	

	@Test
	public void bean() {
		assertThat(this.userService, is(notNullValue()));
	}

	@Before
	public void setUp() {
		users = Arrays.asList(new User("aa", "9", "1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER - 1, 0,"xhdnckdn@hanmail.net"),
				new User("bb", "8", "2", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0,"xhdnckdn@naver.com"),
				new User("cc", "7", "3", Level.SILVER, 60, MIN_LOGCOUNT_FOR_GOLD - 1,"xhdnckdn@hanmail.net"),
				new User("dd", "6", "4", Level.SILVER, 60, MIN_LOGCOUNT_FOR_GOLD,"xhdnckdn@naver.com"),
				new User("ee", "5", "5", Level.GOLD, 100, Integer.MAX_VALUE,"rmflstmzls@gmail.com"));
	}

	@Test
	@DirtiesContext
	public void upgradeLevels() throws Exception {
		userDao.deleteAll();
		for (User user : users)
			userDao.add(user);
		MockMailSender mockMailSender = new MockMailSender();
		userService.setMailSender(mockMailSender);

		userService.upgradeLevels();

//		checkLevel(users.get(0),Level.BASIC);
//		checkLevel(users.get(1),Level.SILVER);
//		checkLevel(users.get(2),Level.SILVER);
//		checkLevel(users.get(3),Level.GOLD);
//		checkLevel(users.get(4),Level.GOLD);
		checkLevelUpgraded(users.get(0), false);
		checkLevelUpgraded(users.get(1), true);
		checkLevelUpgraded(users.get(2), false);
		checkLevelUpgraded(users.get(3), true);
		checkLevelUpgraded(users.get(4), false);
		
		List<String> request = mockMailSender.getRequests();
		
		assertThat(request.size() , is(2));
		assertThat(request.get(0), is(users.get(1).getEmail()));
		assertThat(request.get(1), is(users.get(3).getEmail()));

	}

	private void checkLevelUpgraded(User user, boolean upgraded) {
		User userUpdate = userDao.get(user.getId());
		if (upgraded) {
			assertThat(userUpdate.getLevelInt(), is(user.getLevelInt().nextLevel()));
		} else {
			assertThat(userUpdate.getLevelInt(), is(user.getLevelInt()));
		}

	}

	private void checkLevel(User user, Level expectedLevel) {
		User userUpdate = userDao.get(user.getId());
		assertThat(userUpdate.getLevelInt(), is(expectedLevel));

	}

	@Test
	public void add() {
		userDao.deleteAll();

		User userWithLevel = users.get(4);
		User userWithoutLevel = users.get(0);
		userWithoutLevel.setLevelInt(null);

		userService.add(userWithLevel);
		userService.add(userWithoutLevel);

		User userWithLevelRead = userDao.get(userWithLevel.getId());
		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

		assertThat(userWithLevelRead.getLevelInt(), is(userWithLevel.getLevelInt()));
		assertThat(userWithoutLevelRead.getLevelInt(), is(Level.BASIC));

	}

	static class TestUserService extends UserService {
		private String id;

		private TestUserService(String id) {
			this.id = id;
		}

		public void upgradeLevel(User user) {
			if (user.getId().equals(this.id))
				throw new TestUserServiceException();
			super.upgradeLevel(user);
		}
	}

	static class TestUserServiceException extends RuntimeException {

	}

	@Test
	public void upgradeAllOrNothing() throws Exception {
		UserService testUserService = new TestUserService(users.get(3).getId());
		testUserService.setUserDao(this.userDao);
		testUserService.setTransactionManager(transactionManager);
		testUserService.setMailSender(mailSender);
		
		userDao.deleteAll();
		for (User user : users)
			userDao.add(user);

		try {
			testUserService.upgradeLevels();
			fail("TestUserServiceException expected");
		} catch (TestUserServiceException e) {

		}
		checkLevelUpgraded(users.get(1), false);
	}
	

	
	
}
