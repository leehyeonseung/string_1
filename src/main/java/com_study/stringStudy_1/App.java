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
       UserDao dao= new UserDao();
       
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
