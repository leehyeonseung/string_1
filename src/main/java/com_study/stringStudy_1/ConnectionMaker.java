package com_study.stringStudy_1;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	
	public Connection makeConnection()throws ClassNotFoundException, SQLException;

	
	
}
