package com_study.stringStudy_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
@DirtiesContext
public class jdbcContext {

	private DataSource dataSource;

	// 3장 리스트 3-21 실습
	// DataSource 타입 빈을 DI 받을 수 있게 준비해둔다
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
    //템플릿
	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = this.dataSource.getConnection();
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
	//3장 245p  리스트 3-27 실습
	public void executeSql(final String query)throws SQLException {
		workWithStatementStrategy(new StatementStrategy() {
		public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
			
			return c.prepareStatement(query);
		}
	});
		
	}
	
}
