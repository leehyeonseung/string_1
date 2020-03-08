package com_study.stringStudy_1;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoConnectionCountingTest {
	

	 public static void main( String[] args ) throws ClassNotFoundException, SQLException{
	    {
	    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
	         UserDao dao = context.getBean("userDao",UserDao.class);
	         
	         User user= new User();
	         user.setId("치412");
	         user.setName("피자t");
	         user.setPassword("8");
	         
	         dao.add(user);
	         
	         System.out.println(user.getId()+"등록 성공");
	         
	         User user2 = dao.get(user.getId());
	         System.out.println(user2.getName());
	         
	         CountingConnectionMaker ccm= context.getBean("connectionMaker", CountingConnectionMaker.class);
	         //총 2번에 insert 와 select가 진행됐길 때문에 2번의 카운트가 나온다 
	         System.out.println("Connection counter" + ccm.getCounter());
	         
	    }
}
}