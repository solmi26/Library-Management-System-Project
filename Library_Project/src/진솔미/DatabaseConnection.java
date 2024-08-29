package 진솔미;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	static final String URL = "jdbc:oracle:thin:@192.168.0.70:1521/xe";
	static final String USER = "library";
	static final String PWD = "1234";
	
	
	// DB 연결
    public static Connection getConn() {
    	Connection conn = null;
        try {
        	conn = DriverManager.getConnection(URL, USER, PWD);
//			System.out.println("연결 성공");
//			System.out.println("----------------------------------------------------------------------------");
//			System.out.println();
			
		} catch (SQLException e) {
			System.out.println("연결 실패");
			System.out.println();
			e.printStackTrace();
		}
		return conn;
    }
    
    // 연결 종료
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
//                System.out.println("연결 종료");
            } catch (SQLException e) {
                System.out.println("연결 종료 실패");
                e.printStackTrace();
            }
        }
    }

//	public static void main(String[] args) {
//		
//		// JDBC 연결
//		Connection conn = null;
//		
//		// JDBC 등록
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//			
//			// 연결
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521/xe",
//					"library",
//					"1234");
//			System.out.println("연결 성공");
//		} catch (Exception e) {
//			System.out.println("연결오류");
//		} finally {
//			if(conn != null) {
//				// 연결 끊기
//				try {
//					conn.close();
//					System.out.println("연결끊기");
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
		

		
//	}

}
