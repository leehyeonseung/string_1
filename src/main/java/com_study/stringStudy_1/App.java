package com_study.stringStudy_1;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
    	//User DAO = 추상 클래스 이므로 객체를 직접 생성할 수 없다
    	// 그러므로 상속한 클래스를 주입해주어야 한다
       UserDao dao= new UserDao.NUserDao();
      
       
       User user= new User();
       user.setId("던킨");
       user.setName("도넛");
       user.setPassword("8");
       
       dao.add(user);
       
       System.out.println(user.getId()+"등록 성공");
       
       User user2 = dao.get(user.getId());
       System.out.println(user2.getName());
    }
}
