package com_study.stringStudy_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

public class UserDao {

	/* private SimpleConnectionMaker simpleConnectionMaker; */
	/* private ConnectionMaker connectionMaker; */
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	 * public UserDao(ConnectionMaker connectionMaker) {
	 * 
	 * this.simpleConnectionMaker = new SimpleConnectionMaker();
	 * this.connectionMaker = new DConnectionMaker(); this.connectionMaker =
	 * connectionMaker;
	 * 
	 * 
	 * }
	 */

	public void add(User user) throws ClassNotFoundException, SQLException {

		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement("INSERT INTO usertb(id, name, password) VALUES(?, ?, ?) ");

		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public User get(String id) throws ClassNotFoundException, SQLException {

		/* Connection c = simpleConnectionMaker.getConnection(); */
		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement("SELECT * FROM usertb WHERE id=? ");

		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));

		ps.close();
		c.close();

		return user;
	}

	/*
	 * public abstract Connection connection() throws ClassNotFoundException,
	 * SQLException; { Class.forName("oracle.jdbc.driver.OracleDriver"); Connection
	 * c =
	 * DriverManager.getConnection("Jdbc:oracle:thin:@nacinaci.cafe24.com:1522:xe",
	 * "hr","hr"); return c; }
	 * 
	 * public class NUserDao extends UserDao {
	 * 
	 * @Override public Connection connection() throws ClassNotFoundException,
	 * SQLException { // TODO Auto-generated method stub
	 * Class.forName("oracle.jdbc.driver.OracleDriver"); Connection c =
	 * DriverManager.getConnection("Jdbc:oracle:thin:@nacinaci.cafe24.com:1522:xe",
	 * "hr","hr"); return c; } }
	 * 
	 * 
	 * 
	 * 
	 * public class DUserDao extends UserDao {
	 * 
	 * @Override public Connection connection() throws ClassNotFoundException,
	 * SQLException { // TODO Auto-generated method stub
	 * Class.forName("oracle.jdbc.driver.OracleDriver"); Connection c =
	 * DriverManager.getConnection("Jdbc:oracle:thin:@nacinaci.cafe24.com:1522:xe",
	 * "hr","hr"); return c;
	 * 
	 * }
	 * 
	 * }
	 */

}
