package com_study.stringStudy_1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
@DirtiesContext
public class UserDaoTest {
	@Autowired
	UserDao dao;
	
	@Autowired
	SimpleDriverDataSource dataSource;
	
//	@Autowired
//	DataSource dataSource;
//	private UserDao dao;
//	private User user1;
//	private User user2;
//	private User user3;
	
//	@Autowired
//	private ApplicationContext context;

	
	@Before
	public void setUp() throws SQLException, ClassNotFoundException {
//		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//		this.dao = context.getBean("userDao", UserDao.class);
//		this.dao = this.dao.getBean("userDao", UserDao.class);
//		this.user1 = new User("1","2","3");
//		this.user2= new User("3","4","5");
//		this.user3 = new User("6","7","8");
//		
//		System.out.println(this.context);
//		System.out.println(this);
	}
	
	
//	@Test
//	public void addAndGet() throws SQLException, ClassNotFoundException {
//
////		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
////		UserDao dao = context.getBean("userDao", UserDao.class);
//		// User user = new User("babo", "우우", "321");
//		User user1 = new User("babo", "우우", "2");
//		User user2 = new User("ba1o", "우우1", "1");
//		dao.deleteAll();
//		assertThat(dao.getCount(), is(0));
//
//		/* User user= new User(); */
////		user.setId("나42");
////		user.setName("뿌리42");
////		user.setPassword("8");
////
////		dao.add(user);
//		dao.add(user1);
//		dao.add(user2);
//		assertThat(dao.getCount(), is(2));
//
////		System.out.println(user.getId() + "등록 성공");
//
//		// 1장까지 기본적으로 하는 테스트
//
////		User user2 = dao.get(user.getId());
////		assertThat(user2.getName(), is(user.getName()));
////		assertThat(user2.getPassword(), is(user.getPassword()));
//
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
//	}

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
	public void getUserFailure() throws SQLException, ClassNotFoundException {
//		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//		UserDao dao = context.getBean("userDao", UserDao.class);
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		dao.get("unknown_id");
	}


}
