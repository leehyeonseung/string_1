package com_study.stringStudy_1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {

	@Bean
	public UserDao userDao() {
		/* UserDao userDao = new UserDao(new DConnectionMaker()); */
		return new UserDao(connectionMaker());
	}
	

	
	@Bean
	public ConnectionMaker connectionMaker() {
		//DI 주입
		//운영 개발 로컬 접속정보를 쉽게 주입하여 변경할 수 있다.
		return new CountingConnectionMaker(realConnectionMaker());	
	}
	@Bean
	public ConnectionMaker realConnectionMaker() {
		//DI 주입
		//운영 개발 로컬 접속정보를 쉽게 주입하여 변경할 수 있다.
		return new DConnectionMaker();
	}
	
}
