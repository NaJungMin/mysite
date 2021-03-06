package simpleDao;

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

public class BoardDao {

	// singleton 패턴
	private static BoardDao instance = null;

	private BoardDao() {
	}// 내부에서만사용가능하게해둠

	public static BoardDao getInstance() {
		if (instance == null)
			instance = new BoardDao();
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

		String sql = "SELECT count(*) FROM board";
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
	
	public BoardVo getView(long no) throws SQLException {

		conn = getConnection();
		BoardVo vo = new BoardVo();

		String sql = "SELECT no FROM board where my_no=?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, no);

		rs = pstmt.executeQuery();

		if (rs.next()) {
			long rsno = rs.getLong(1);
			long view_cnt = rs.getLong(2);
			long member_no = rs.getLong(3);
			String member_name = rs.getString(4);
			String title = rs.getString(5);
			String content = rs.getString(6);
			String reg_date = rs.getString(7);

			vo.setNo(rsno);
			vo.setView_cnt(view_cnt);
			vo.setMember_no(member_no);
			vo.setMember_name(member_name);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setReg_date(reg_date);

		}

		rs.close();
		pstmt.close();
		conn.close();
		return vo;

	}

	public int[] getList() throws SQLException {

		int[] list = new int[14];

		conn = getConnection();
		stmt = conn.createStatement();

		String sql = "SELECT no FROM board where my_no=0";
		rs = stmt.executeQuery(sql);

		int i=0;
		while(rs.next()){
				int no = rs.getInt(1);
				list[i]=no;
				i++;
			}	
		rs.close();
		stmt.close();
		conn.close();
		return list;

	}
	
	
	public List<BoardVo> getList(int num) throws SQLException {

		List<BoardVo> list = new ArrayList<BoardVo>();

		conn = getConnection();

		String sql = "  select * from ( select A.*, rownum as rnum, floor((rownum-1)/2+1) as page, count(*) over() as totcnt from( SELECT no, view_cnt, member_name, title, TO_CHAR (reg_date, 'YYYY-MM-DD HH:MI:SS'), member_no, dap FROM board where dap = 1 ORDER BY reg_date DESC) A) where page = ?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, num);
			
			
		rs = pstmt.executeQuery();
		
		
		
		while (rs.next()) {
			long no = rs.getLong(1);
			long view_cnt = rs.getLong(2);
			String member_name = rs.getString(3);
			String title = rs.getString(4);
			String reg_date = rs.getString(5);
			long member_no = rs.getLong(6);
			long dap = rs.getLong(7);

			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setView_cnt(view_cnt);
			vo.setMember_name(member_name);
			vo.setTitle(title);
			vo.setReg_date(reg_date);
			vo.setMember_no(member_no);
			vo.setDap(dap);
			
			list.add(vo);
		}

		rs.close();
		pstmt.close();
		conn.close();
		return list;

	}

	public void insert(BoardVo vo) throws SQLException {

		conn = getConnection();
		String sql = "insert into board values (board_no_seq.nextval, ?, ?, ?, ?, 0, sysdate, ?, ?)";
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setLong(3, vo.getMember_no());
		pstmt.setString(4, vo.getMember_name());
		pstmt.setLong(5, vo.getDap());
		pstmt.setLong(6, vo.getMy_no());

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}


	public void delete(String no) throws SQLException {

		long lno = Long.parseLong(no);
		conn = getConnection();
		String sql = "DELETE FROM board WHERE no=?";
		pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, lno);

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}

	public void update(int no) throws SQLException {

		Connection conn = getConnection();
		String sql = "UPDATE board SET my_no=? where my_no=0 and no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1,no);
		pstmt.setInt(2, no);

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}
	
	public void plusCnt(long no, long cnt) throws SQLException {

		Connection conn = getConnection();
		String sql = "UPDATE board SET view_cnt=? WHERE no=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		long incnt= cnt+1;
		pstmt.setLong(1, incnt);
		pstmt.setLong(2, no);

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}
	
	public List<BoardVo> search(String kwd) throws SQLException {
		
		
		Connection conn = getConnection();
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		String sql = "select no, view_cnt, member_name, title, TO_CHAR (reg_date, 'YYYY-MMDD HH:MM:SS'), member_no from board where title like ? or content like ? or member_name like ?";
		//String sql = "select no, view_cnt, member_name, title, TO_CHAR (reg_date, 'YYYY-MMDD HH:MM:SS'), member_no from board where title like '%와아아%' or content like '%와아아%' or member_name like '%?%'";
		
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, "%"+kwd+"%");
		pstmt.setString(2, "%"+kwd+"%");
		pstmt.setString(3, "%"+kwd+"%");
		
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			long no = rs.getLong(1);
			long view_cnt = rs.getLong(2);
			String member_name = rs.getString(3);
			String title = rs.getString(4);
			String reg_date = rs.getString(5);
			long member_no = rs.getLong(6);

			BoardVo vo = new BoardVo();
			vo.setNo(no);
			vo.setView_cnt(view_cnt);
			vo.setMember_name(member_name);
			vo.setTitle(title);
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
