package com.bit2015.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bit2015.mysite.vo.UserVo;

public class UserDao {
	private static UserDao instance;

	private UserDao() {

	}

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻기
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bitdb", "bitdb");

		} catch (ClassNotFoundException ex) {
			System.out.println("드라이버 오류 :" + ex);
		}

		return conn;
	}
	
	// get
	public UserVo get(String email) throws SQLException {
		Connection conn = getConnection();

		String sql = "select no, name, email, password, gender from member where email=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, email);

		ResultSet rs = pstmt.executeQuery();

		UserVo vo = null;

		if (rs.next()) {

			vo = new UserVo();
			vo.setNo(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setEmail(rs.getString(3));
			vo.setPassword(rs.getString(4));
			vo.setGender(rs.getString(5));

		}
		rs.close();
		pstmt.close();
		conn.close();
		return vo;
	}
	
	public UserVo get(long no) throws SQLException {
		Connection conn = getConnection();

		String sql = "select no, name, email, password, gender from member where no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, no);

		ResultSet rs = pstmt.executeQuery();

		UserVo vo = null;

		if (rs.next()) {

			vo = new UserVo();
			vo.setNo(rs.getLong(1));
			vo.setName(rs.getString(2));
			vo.setEmail(rs.getString(3));
			vo.setPassword(rs.getString(4));
			vo.setGender(rs.getString(5));

		}
		rs.close();
		pstmt.close();
		conn.close();
		return vo;
	}

	public UserVo get(String email, String password) throws SQLException {
		Connection conn = getConnection();

		String sql = "select no, name from member where email=? and password=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, email);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();

		UserVo vo = null;

		if (rs.next()) {

			vo = new UserVo();
			vo.setNo(rs.getLong(1));
			vo.setName(rs.getString(2));

		}
		rs.close();
		pstmt.close();
		conn.close();

		return vo;

	}

	// insert
	public void insert(UserVo vo) throws SQLException {
		Connection conn = getConnection();

		String sql = "insert into member values ( member_no_seq.nextval, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getEmail());
		pstmt.setString(3, vo.getPassword());
		pstmt.setString(4, vo.getGender());

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();

	}
	
	//update
	public void update(UserVo vo, String email) throws SQLException {

		Connection conn = getConnection();
		String sql = "UPDATE MEMBER SET name=?, password=?, gender=? WHERE email=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPassword());
		pstmt.setString(3, vo.getGender());
		pstmt.setString(4, email);

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}
	/*
	 * //delete public void delete( UserVo vo ) throws SQLException { Connection
	 * conn = getConnection();
	 * 
	 * String sql = "delete from user where no=? and password=?";
	 * PreparedStatement pstmt = conn.prepareStatement( sql );
	 * 
	 * pstmt.setLong( 1, vo.getNo() ); pstmt.setString( 2, vo.getPassword() );
	 * 
	 * pstmt.executeUpdate();
	 * 
	 * pstmt.close(); conn.close(); }
	 * 
	 * //getList public List<UserVo> getList() throws SQLException {
	 * List<UserVo> list = new ArrayList<UserVo>();
	 * 
	 * Connection conn = getConnection(); Statement stmt =
	 * conn.createStatement();
	 * 
	 * String sql = "   select no," + "          name," + "          password,"
	 * + "          message," +
	 * "          to_char(reg_date, 'YYYY-MM-DD HH:MM:SS')" + "     from user" +
	 * " order by reg_date desc"; ResultSet rs = stmt.executeQuery( sql );
	 * 
	 * while( rs.next() ) { long no = rs.getLong( 1 ); String name =
	 * rs.getString( 2 ); String email = rs.getString( 3 ); String password =
	 * rs.getString( 4 ); String gender = rs.getString( 5 );
	 * 
	 * UserVo vo = new UserVo(); vo.setNo(no); vo.setName( name );
	 * vo.setEmail(email); vo.setPassword(password); vo.setGender(gender);
	 * 
	 * list.add( vo ); }
	 * 
	 * rs.close(); stmt.close(); conn.close();
	 * 
	 * return list; }
	 */

}
