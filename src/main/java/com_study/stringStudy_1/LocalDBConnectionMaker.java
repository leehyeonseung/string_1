package com_study.stringStudy_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalDBConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		
		//로컬 접속 정보
		Class.forName("oracle.jdbc.driver.OracleDriver");
	    Connection c = DriverManager.getConnection("Jdbc:oracle:thin:@nacinaci.cafe24.com:1522:xe","hr","hr");
	    return c;
		
	}

}
