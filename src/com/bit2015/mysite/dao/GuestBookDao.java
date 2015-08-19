package com.bit2015.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2015.mysite.vo.GuestBookVo;

public class GuestBookDao {

	//singleton 패턴
		private static GuestBookDao instance=null;
		private GuestBookDao() {}//내부에서만사용가능하게해둠
		public static GuestBookDao getInstance(){
			if(instance==null)
				instance=new GuestBookDao();
			return instance;
		}
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private Connection getConnection() throws SQLException {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bitdb", "bitdb");
		} catch (ClassNotFoundException e) {
			System.out.println("db오류" + e);
		}

		return conn;
	}

	
	
	public List<GuestBookVo> getList() throws SQLException {

		List<GuestBookVo> list = new ArrayList<GuestBookVo>();

		conn = getConnection();
		stmt = conn.createStatement();

		String sql = "SELECT no, name, password, content, TO_CHAR (reg_date, 'YYYY-MMDD HH:MM:SS') FROM guest_table ORDER BY reg_date DESC";
		rs = stmt.executeQuery(sql);

		while (rs.next()) {
			int no = rs.getInt(1);
			String name = rs.getString(2);
			String password = rs.getString(3);
			String content = rs.getString(4);
			String reg_date=rs.getString(5);

			GuestBookVo vo = new GuestBookVo();
			vo.setNo(no);
			vo.setName(name);
			vo.setPassword(password);
			vo.setContent(content);
			vo.setReg_date(reg_date);

			list.add(vo);
		}

		rs.close();
		stmt.close();
		conn.close();
		return list;

	}

	public void insert(GuestBookVo vo) throws SQLException {

		conn = getConnection();
		String sql = "insert into guest_table values ( guest_table_no_seq.nextval, ?, ?, ?, sysdate)";
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPassword());
		pstmt.setString(3, vo.getContent());

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	public void delete(String no, String password) throws SQLException {
		
		int ino=Integer.parseInt(no);
		conn = getConnection();
		String sql = "DELETE FROM guest_table WHERE no=? AND password=?";
		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, ino);
		pstmt.setString(2, password);

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}
	
	
public GuestBookVo delSelect(String no) throws SQLException {
		
		int ino=Integer.parseInt(no);
		conn = getConnection();
		String sql = "SELECT password FROM guest_table WHERE no=?";
		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, ino);

		rs = pstmt.executeQuery();
		
		GuestBookVo vo = new GuestBookVo();
		
		while(rs.next()){
			String pass=rs.getString(1);
			
			vo.setPassword(pass);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		return vo;
	}
	
	
	
	
	
}
