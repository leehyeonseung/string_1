package com_study.stringStudy_1;

import java.sql.SQLException;

import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Hello world!
 *
 */

public class App {
	public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {  
		JUnitCore.main("com_study.stringStudy_1.UserDaoTest");
    	//User DAO = 추상 클래스 이므로 객체를 직접 생성할 수 없다
    	// 그러므로 상속한 클래스를 주입해주어야 한다 ( user dao 에 class 앞에 abstract 가 있을 경우 에러)
		/* UserDao dao= new DaoFactory().userDao(); */
    	
    	//abstract  추상 메소드를 의미한다. 하위 클래스에 의해 구현된다.
    	
    	//static 이란?
    	// static 키워드를 사용한 변수는 클래스가 메모리에 올라갈 때 자동으로 생성이 된다.
    	// 즉 인스턴스(객체)생성 없이 바로 사용가능 하다
    	
    	// static 사용하는 이유는?
    	// 자주 변하지 않는 일정한 값이나 설정 정보같은 공용자원에 대한 접근에 있어서 매번 메모리에 로딩하거나
    	// 값을 읽어들이는 것보다 일종의 '전역변수'와 같은 개념을 통해서 접근하는 것
    	// 인스턴스 생성 없이 바로 사용 가능하기 때문에
    	// 프로그램 내에서 공통으로 사용되는 데이터를 관리 할 때 이용한다
    	
    	
		/*
		 * AnnotationConfigApplicationContext context = new
		 * AnnotationConfigApplicationContext(CountingDaoFactory.class); UserDao dao =
		 * context.getBean("userDao",UserDao.class);
		 * 
		 * User user= new User(); user.setId("치킨1"); user.setName("피자`");
		 * user.setPassword("8");
		 * 
		 * dao.add(user);
		 * 
		 * System.out.println(user.getId()+"등록 성공");
		 * 
		 * User user2 = dao.get(user.getId()); System.out.println(user2.getName());
		 * 
		 * CountingConnectionMaker ccm= context.getBean("connectionMaker",
		 * CountingConnectionMaker.class); //총 2번에 insert 와 select가 진행됐길 때문에 2번의 카운트가
		 * 나온다 System.out.println("Connection counter" + ccm.getCounter());
		 */
        
        
      ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
      UserDaoJdbc dao = context.getBean("userDao",UserDaoJdbc.class);
       
       User user= new User();
       user.setId("1나무3");
       user.setName("뿌리");
       user.setPassword("8");
       
       dao.add(user);
       
       System.out.println(user.getId()+"등록 성공");
 
       
       // 1장까지 기본적으로 하는 테스트
		
		  User user2 = dao.get(user.getId()); 
       System.out.println(user2.getName());
		  System.out.println(user2.getPassword());
		  
		  System.out.println(user2.getId()+ " 조회성공 ");
		 
       
       //2장 테스트
       if(!user.getName().equals(user2.getName())) {
    	   System.out.println("테스트 실패 (name)");
       }else if(!user.getPassword().equals(user2.getPassword())) {
      System.out.println("테스트 실패  (password)");
       }else {System.out.println("조회 테스트 성공");
    }
  	 JUnitCore.main("com_study.stringStudy_1.UserDaoTest");
 }
 
}
