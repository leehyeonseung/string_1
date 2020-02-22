package com_study.stringStudy_1;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
    	//User DAO = 추상 클래스 이므로 객체를 직접 생성할 수 없다
    	// 그러므로 상속한 클래스를 주입해주어야 한다 ( user dao 에 class 앞에 abstract 가 있을 경우 에러)
		/* UserDao dao= new DaoFactory().userDao(); */
    
      ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
      UserDao dao = context.getBean("userDao",UserDao.class);
       
       User user= new User();
       user.setId("치킨");
       user.setName("피자");
       user.setPassword("8");
       
       dao.add(user);
       
       System.out.println(user.getId()+"등록 성공");
       
       User user2 = dao.get(user.getId());
       System.out.println(user2.getName());
    }
}
