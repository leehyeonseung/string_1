package com_study.stringStudy_1;


import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
@DirtiesContext
public class UserDaoTest {
	@Autowired
	UserDaoJdbc dao;
	
//	User user1;
//	User user2;
//	User user3;
//	@Autowired
//	SimpleDriverDataSource dataSource;
	
	@Autowired
	DataSource dataSource;
//	private UserDao dao;
	private User user1;
	private User user2;
	private User user3;
	
	@Autowired
	private ApplicationContext context;

	
	@Before
	public void setUp() throws SQLException, ClassNotFoundException {
		//ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//		this.dao = context.getBean("userDao", UserDao.class);
//		this.dao = this.dao.getBean("userDao", UserDao.class);
		//this.dao= context.getBean("userDao",UserDaoJdbc.class);
		this.user1 = new User("99","nn","2",Level.BASIC,1,0,"xhdnckdn@hanmail.net");
		this.user2= new User("09","4","5",Level.SILVER,55,10,"xhdnckdn@naver.com");
		this.user3 = new User("67","7","8",Level.GOLD,100,40,"rmflstmzls@gamil.com");
		
		System.out.println(this.context);
		System.out.println(this);
		
	}
	
	
	@Test
	@Ignore
	public void addAndGet() throws SQLException, ClassNotFoundException {

//		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//		UserDao dao = context.getBean("userDao", UserDao.class);
		// User user = new User("babo", "우우", "321");
//		User user1 = new User("d2","4","5",Level.SILVER,2,3);
//		User user2 = new User("d3","7","8",Level.GOLD,3,4);
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		/* User user= new User(); */
//		user.setId("나42");
//		user.setName("뿌리42");
//		user.setPassword("8");
//
//		dao.add(user);
		dao.add(user1);
		dao.add(user2);
//		assertThat(dao.getCount(), is(2));

//		System.out.println(user.getId() + "등록 성공");

		// 1장까지 기본적으로 하는 테스트

//		User user2 = dao.get(user.getId());
//		assertThat(user2.getName(), is(user.getName()));
//		assertThat(user2.getPassword(), is(user.getPassword()));

//		// 2장 테스트
//		User userget1 = dao.get(user1.getId());
//		assertThat(user1.getName(), is(user1.getName()));
//		assertThat(user1.getPassword(), is(user1.getPassword()));
//
//		User userget2 = dao.get(user2.getId());
//		assertThat(user2.getName(), is(user2.getName()));
//		assertThat(user2.getPassword(), is(user2.getPassword()));
//		// 191p 리스트2-20 테스트
//		DataSource dataSource = new SingleConnectionDataSource("Jdbc:oracle:thin:@nacinaci.cafe24.com:1522:xe","hr","hr", true);
//		dao.setDataSource(dataSource);
		
		User userget1 = dao.get(user1.getId());
		checkSameUser(userget1, user1);
		
		User userget2 = dao.get(user2.getId());
		checkSameUser(userget2, user2);
	}

//	@Test
//	public void count() throws SQLException, ClassNotFoundException {
////		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
////		UserDao dao = context.getBean("userDao", UserDao.class);
//		User user1 = new User("12", "23", "4");
//		User user2 = new User("13", "21", "2");
////		User user3=new User("14","23","4");
//
//		dao.deleteAll();
//		assertThat(dao.getCount(), is(0));
//
//		dao.add(user1);
////		assertThat(dao.getCount(), is(1));
//
//		dao.add(user2);
//		assertThat(dao.getCount(), is(2));
//
////		dao.add(user3);
////		assertThat(dao.getCount(), is(2));
//
//		User userget1 = dao.get(user2.getId());
//		assertThat(user2.getName(), is(user2.getName()));
//		assertThat(user2.getPassword(), is(user2.getPassword()));
//	}

	@Test(expected = EmptyResultDataAccessException.class)
	@Ignore
	public void getUserFailure() throws SQLException, ClassNotFoundException {
//		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//		UserDao dao = context.getBean("userDao", UserDao.class);
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		dao.get("unknown_id");
	}
	
	@Test
	@Ignore
	public void getAll() throws SQLException, ClassNotFoundException {
		dao.deleteAll();
		
//		dao.add(user1); //
//		List<User> users1 = dao.getAll();
//		assertThat(users1.size(), is(1));
//		
//		
//		dao.add(user2); //
//		List<User> users2 = dao.getAll();
//		assertThat(users2.size(), is(2));
//		checkSameUser(user1,users2.get(0));
//		checkSameUser(user2,users2.get(1));
//		
//		dao.add(user3); //
//		List<User> users3 = dao.getAll();
//		assertThat(users3.size(), is(3));
//		checkSameUser(user3,users3.get(2));
//		checkSameUser(user1,users3.get(0));
//		checkSameUser(user2,users3.get(1));

		// 3장 템플릿 270p 리스트 3-54 데이터가 없는 경우에 대한 검증 코드가 추가된 getAll() 테스트
		List<User> users0 = dao.getAll();
		assertThat(users0.size(), is(0));
	}
	private void checkSameUser(User user1, User user2 ) {
		
		
		System.out.println("1"+user1.toString());

		
		
		
		assertThat(user1.getId(), is(user2.getId()));
		assertThat(user1.getName(), is(user2.getName()));
		assertThat(user1.getPassword(), is(user2.getPassword()));
		assertThat(user1.getLevelInt(), is(user2.getLevelInt()));
		assertThat(user1.getLogin(), is(user2.getLogin()));
		assertThat(user1.getRecommend(), is(user2.getRecommend()));
		
	}

	@Test(expected = DataAccessException.class)
	@Ignore
	public void duplicatekey() {
		dao.deleteAll();
		
		
		dao.add(user1);
		dao.add(user1);
	
	}
	
	@Test
	@Ignore
	public void sqlExceptionTranslate() {
		dao.deleteAll();
		
		try {
			dao.add(user1);
			dao.add(user1);
		}catch(DuplicateKeyException ex) {
			SQLException sqlEx = (SQLException)ex.getRootCause();
			SQLExceptionTranslator set = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);
			assertThat(set.translate(null, null, sqlEx), is(instanceOf(DuplicateKeyException.class)));
		}
	}

	@Test
	public void update() {
		dao.deleteAll();
		System.out.println(user1.toString()+"4");
		dao.add(user1);
		dao.add(user2);
//		dao.add(user3);
		
		user1.setName("die");
		user1.setPassword("9");
		user1.setLevelInt(Level.GOLD);
		user1.setLogin(1000);
		user1.setRecommend(999);
		System.out.println("update be"+user1.toString());
		dao.update(user1);
		System.out.println("update af"+user1.toString());
//		user2.setName("de");
//		user2.setPassword("9");
//		user2.setLevelInt(Level.GOLD);
//		user2.setLogin(1000);
//		user2.setRecommend(999);
//		dao.update(user2);
		
		
		User user1update = dao.get(user1.getId());
		checkSameUser(user1, user1update);
		
		User user2same = dao.get(user2.getId());
		checkSameUser(user2, user2same);
		
//		User user3same = dao.get(user3.getId());
//		checkSameUser(user3, user3same);
	}

	
}
