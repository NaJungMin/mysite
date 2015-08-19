package simpleDao;

import java.sql.SQLException;

public class sipleMain {

	public static void main(String[] args) {
		try {
		BoardDao dao = BoardDao.getInstance();
		
			int [] list = dao.getList();
			System.out.println("리스트불러옴");
			
			for (int i = 0; i < list.length; i++) {
				dao.update(list[i]);
			}
		System.out.println("리스트집어넣음");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
