package com.bit2015.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2015.mysite.vo.BoardVo;
import com.bit2015.mysite.vo.UserVo;

public class DapDao {

	// singleton 패턴
	private static DapDao instance = null;

	private DapDao() {
	}// 내부에서만사용가능하게해둠

	public static DapDao getInstance() {
		if (instance == null)
			instance = new DapDao();
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
	public List<BoardVo> getList() throws SQLException {

		List<BoardVo> list = new ArrayList<BoardVo>();

		conn = getConnection();

		String sql = "SELECT no, view_cnt, member_name, title, TO_CHAR (reg_date, 'YYYY-MMDD HH:MM:SS'), member_no, dap, my_no FROM board where dap!= 1 ORDER BY reg_date asc";
		pstmt = conn.prepareStatement(sql);
			
		rs = pstmt.executeQuery();
		
		
		
		while (rs.next()) {
			long no = rs.getLong(1);
			long view_cnt = rs.getLong(2);
			String member_name = rs.getString(3);
			String title = rs.getString(4);
			String reg_date = rs.getString(5);
			long member_no = rs.getLong(6);
			long dap = rs.getLong(7);
			long my_no=rs.getLong(8);

			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setView_cnt(view_cnt);
			vo.setMember_name(member_name);
			vo.setTitle(title);
			vo.setReg_date(reg_date);
			vo.setMember_no(member_no);
			vo.setDap(dap);
			vo.setMy_no(my_no);
			
			list.add(vo);
		}

		rs.close();
		pstmt.close();
		conn.close();
		return list;

	}
	
	//"select * from ( select A.*, rownum as rnum, floor((rownum-1)/2+1) as page, count(*) over() as totcnt from( SELECT no, view_cnt, member_name, title, TO_CHAR (reg_date, 'YYYY-MM-DD HH:MI:SS'), member_no FROM board ORDER BY reg_date DESC) A) where page =1";
	/*
	 * public BoardVo delSelect(String no) throws SQLException {
	 * 
	 * 
	 * int ino=Integer.parseInt(no); conn = getConnection(); String sql =
	 * "SELECT password FROM guest_table WHERE no=?"; pstmt =
	 * conn.prepareStatement(sql);
	 * 
	 * pstmt.setInt(1, ino);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * BoardVo vo = new BoardVo();
	 * 
	 * while(rs.next()){ String pass=rs.getString(1);
	 * 
	 * vo.setMember_no(pass); }
	 * 
	 * rs.close(); pstmt.close(); conn.close(); return vo; }
	 * 
	 */

}
