package com.bit2015.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2015.mysite.vo.ReplyVo;
import com.bit2015.mysite.vo.UserVo;

public class ReplyDao {

	// singleton 패턴
	private static ReplyDao instance = null;

	private ReplyDao() {
	}// 내부에서만사용가능하게해둠

	public static ReplyDao getInstance() {
		if (instance == null)
			instance = new ReplyDao();
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
	

	public int totalPage() throws SQLException{

		conn = getConnection();
		stmt = conn.createStatement();

		String sql = "SELECT count(*) FROM reply_board where post_no=?";
		rs = stmt.executeQuery(sql);
		int total=0;
		if (rs.next()) {
			total= rs.getInt(1);
	}
		rs.close();
		stmt.close();
		conn.close();
		return total;

		
	}
	
	public void insert(ReplyVo vo) throws SQLException {

		conn = getConnection();
		String sql = "insert into reply_board values (reply_no_seq.nextval, ?, ?, ?, ?, sysdate)";
		pstmt = conn.prepareStatement(sql);

		
		pstmt.setString(1, vo.getContent());
		pstmt.setLong(2, vo.getMember_no());
		pstmt.setLong(3, vo.getPost_no());
		pstmt.setString(4, vo.getMember_name());
		
		
		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}


	public void delete(String no) throws SQLException {

		long lno = Long.parseLong(no);
		conn = getConnection();
		String sql = "DELETE FROM reply_board WHERE no=?";
		pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, lno);

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	public void update(long no, String title, String content) throws SQLException {

		Connection conn = getConnection();
		String sql = "UPDATE board SET title=?, content=? WHERE no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setLong(3, no);

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}
	
	
	public List<ReplyVo> getList(long post_no) throws SQLException {
		
		
		Connection conn = getConnection();
		List<ReplyVo> list = new ArrayList<ReplyVo>();
		
		String sql = "select no, content, member_no, member_name, TO_CHAR (reg_date, 'YYYY-MMDD HH:MM:SS') from reply_board where post_no=?";
		
		pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, post_no);
		
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			long no = rs.getLong(1);
			String content=rs.getString(2);
			long member_no = rs.getLong(3);
			String member_name = rs.getString(4);
			String reg_date = rs.getString(5);
			

			ReplyVo vo = new ReplyVo();
			vo.setNo(no);
			vo.setContent(content);
			vo.setMember_name(member_name);
			vo.setReg_date(reg_date);
			vo.setMember_no(member_no);

			list.add(vo);
		}
		
		
		pstmt.close();
		conn.close();
		rs.close();
		return list;
	}

	
	
	//"select * from ( select A.*, rownum as rnum, floor((rownum-1)/2+1) as page, count(*) over() as totcnt from( SELECT no, view_cnt, member_name, title, TO_CHAR (reg_date, 'YYYY-MM-DD HH:MI:SS'), member_no FROM board ORDER BY reg_date DESC) A) where page =1";
	/*
	 * public ReplyVo delSelect(String no) throws SQLException {
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
	 * ReplyVo vo = new ReplyVo();
	 * 
	 * while(rs.next()){ String pass=rs.getString(1);
	 * 
	 * vo.setMember_no(pass); }
	 * 
	 * rs.close(); pstmt.close(); conn.close(); return vo; }
	 * 
	 */

}
