package com_study.stringStudy_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStatement implements StatementStrategy {
	
	User user;
	public AddStatement(User user) {
		this.user = user;
	}
	public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
		
		PreparedStatement ps = c.prepareStatement("INSERT INTO usertb(id, name, password) VALUES(?, ?, ?) ");

		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();
		return ps;
	}

}
