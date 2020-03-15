package com_study.stringStudy_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.runner.JUnitCore;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserDao {

	/* private SimpleConnectionMaker simpleConnectionMaker; */
	/* private ConnectionMaker connectionMaker; */
	private DataSource dataSource;
	
	// 3장 3-22 jdbccontext 를 DI 받아서 사용하도록 만든 UserDao
	// jdbcContext를 DI 받도록 만든다.
	private jdbcContext jdbcContext;
	
	//3장 3-25 JdbcContext 생성과 DI 작업을 수행하는 setDataSource()메소드
	public void setDataSource(DataSource dataSource) { // 생상저 메소드이면서 jdbcCOntextㅇ 대한 생성
														// DI 작업을 동시에 수행한다.
		this.jdbcContext = new jdbcContext(); //jdbcContext  생성 (Ioc)
		
		this.jdbcContext.setDataSource(dataSource); // 의존 오브젝트 주입 DI
		
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

	


	public void add(final User user) throws ClassNotFoundException, SQLException {

//		Connection c = dataSource.getConnection();
//
//		PreparedStatement ps = c.prepareStatement("INSERT INTO usertb(id, name, password) VALUES(?, ?, ?) ");
//
//		ps.setString(1, user.getId());
//		ps.setString(2, user.getName());
//		ps.setString(3, user.getPassword());
//
//		ps.executeUpdate();
//
//		ps.close();
//		c.close();

		// 3장 리스트 3-15 실습
//		class AddStatement implements StatementStrategy{
////			User user;
////			public AddStatement(User user) {
////				this.user = user;
////			}
//			
//			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
//				PreparedStatement ps = c.prepareStatement("INSERT INTO usertb(id, name, password) VALUES(?, ?, ?) ");
//
//				ps.setString(1, user.getId());
//				ps.setString(2, user.getName());
//				ps.setString(3, user.getPassword());
//
//				ps.executeUpdate();
//				return ps;
//			}
//		}
//		StatementStrategy st =new AddStatement(); // 선정한 전략 클래스의 오브젝트 생성
//		jdbcContextWithStatementStrategy(st); // 컨텍스트 호출 . 전력오브젝트 전달

		//jdbcContextWithStatementStrategy(
		//3장 리스트 3-22 DI 받은 jdbcContext의 컨텍스트 메소드를 사용하도록 변경한다
		this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {

			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				PreparedStatement ps = c.prepareStatement("INSERT INTO usertb(id, name, password) VALUES(?, ?, ?) ");

				ps.setString(1, user.getId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getPassword());

				ps.executeUpdate();
				return ps;

			}
		});
	}

	public User get(String id) throws ClassNotFoundException, SQLException {

		/* Connection c = simpleConnectionMaker.getConnection(); */
		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement("SELECT * FROM usertb WHERE id=? ");

		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		User user = null;
//		rs.next();
//		User user = new User();
//		user.setId(rs.getString("id"));
//		user.setName(rs.getString("name"));
//		user.setPassword(rs.getString("password"));

		if (rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}

		ps.close();
		c.close();
		if (user == null)
			throw new EmptyResultDataAccessException(1);
		return user;
	}

	public void deleteAll() throws SQLException {
//		Connection c = null;
//		PreparedStatement ps = null;
//		try {
//		Connection c = dataSource.getConnection();
//		PreparedStatement ps = c.prepareStatement("DELETE FROM usertb");
//			c = dataSource.getConnection();

//			ps = c.prepareStatement("DELETE FROM usertb");
//			ps = makeStatement(c);
		// 3장 실습
//			StatementStrategy strategy = new DeleteAllStatement();
//			ps = strategy.makePreparedStatement(c);
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			throw e;
//		} finally {
//			if (ps != null) {
//				try {
//					ps.close();
//				} catch (SQLException e) {
//
//				}
//			}
//			if (c != null) {
//				try {
//					c.close();
//				} catch (SQLException e) {
//
//				}
//			}
//		}

		// 3장 리스트 3-12 실습
//		StatementStrategy st =new DeleteAllStatement(); // 선정한 전략 클래스의 오브젝트 생성
//		jdbcContextWithStatementStrategy(st); // 컨텍스트 호출 . 전력오브젝트 전달

		// 3장 리스트 3-20 실습
		//jdbcContextWithStatementStrategy(
		//3장 리스트 3-22 DI 받은 jdbcContext의 컨텍스트 메소드를 사용하도록 변경한다
		this.jdbcContext.workWithStatementStrategy(new StatementStrategy() {
			public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
				// TODO Auto-generated method stub
				return c.prepareStatement("DELETE FROM usertb");
			}
		});
	}

	public int getCount() throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
//			Connection c = dataSource.getConnection();
//			PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) from usertb");
//			ResultSet rs = ps.executeQuery();
			c = dataSource.getConnection();
			ps = c.prepareStatement("SELECT COUNT(*) from usertb");
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {

				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {

				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {

				}
			}
		}

//		rs.next();
//		int count = rs.getInt(1);

//		rs.close();
//		ps.close();
//		c.close();
//
//		return count;
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

	private PreparedStatement makeStatement(Connection c) throws SQLException {

		PreparedStatement ps;
		ps = c.prepareStatement("DELETE FROM usertb");
		return ps;
	}

	// 3장 리스트 3-11 실습중
	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		try {

			c = dataSource.getConnection();
			ps = stmt.makePreparedStatement(c);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
	}
}
