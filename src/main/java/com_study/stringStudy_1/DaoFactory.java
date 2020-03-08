package com_study.stringStudy_1;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

//@Configuration 어노테이션은 스프링 IOC Container 에게 해당 클래스를
// Bean 구성 Class임을 알려주는 것이다

@Configuration
public class DaoFactory {

	//Bean 어노테이션의 경우 개발자가 직접 제어가 불가능한 외부 라이브러리 등을
	//Bean으로 만들려 할 떄 사용한다
	
	@Bean
	public UserDao userDao() {
		/* UserDao userDao = new UserDao(new DConnectionMaker()); */
		/* return new UserDao(connectionMaker()); */
		/* return new UserDao(dataSource()); */
		UserDao userDao=new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	}
	
	/*
	 * @Bean public ConnectionMaker connectionMaker() { return new
	 * DConnectionMaker(); }
	 */
	
	@Bean
	public ConnectionMaker connectionMaker() {
		//DI 주입
		//운영 개발 로컬 접속정보를 쉽게 주입하여 변경할 수 있다.
		return new LocalDBConnectionMaker();
	}
	
	@Bean
	public DataSource dataSource() {
		
	 SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
	 
	 dataSource.setDriverClass(oracle.jdbc.driver.OracleDriver.class);
	 dataSource.setUrl("Jdbc:oracle:thin:@nacinaci.cafe24.com:1522:xe");
	 dataSource.setUsername("hr");
	 dataSource.setPassword("hr");
	 
		return dataSource;
		

	}
	
}
