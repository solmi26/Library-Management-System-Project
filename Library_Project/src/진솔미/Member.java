package 진솔미;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Member 클래스는 회원 관리와 관련된 기능을 제공합니다.
 * 회원가입, 로그인, 회원 목록 출력, 회원 권한 변경 등의 기능을 관리합니다.
 */

public class Member {
	// 필드
	private String id; // 회원 id
	private String pwd; // 회원 패스워드
	private String name; // 회원 이름
	private int role; // 1 = 일반회원 / 0 = 관리자
	Connection conn; // 데이터베이스 연결 객체

	// 생성자
    public Member(Connection conn) {
        this.conn = conn;
        
        try {
        	// 자동 커밋 설정
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("자동 커밋 실패");
			e.printStackTrace();
		}
    }
	
	// 메소드
    // getter/setter 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	// 로그인 메소드	
	// 로그인 시 role 값을 반환하여 권한 확인
	// 아이디가 잘못 되었는지 비밀번호가 잘못 되었는지 확인 가능
	public int login(String inputId, String inputPwd) {
	
        String sql = "SELECT * FROM members where id = ?";
       
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputId);			
	        ResultSet rs = pstmt.executeQuery();
	        
            if (rs.next()) {
                String pwd = rs.getString("pwd");
                int role = rs.getInt("role");
                if (pwd.equals(inputPwd)) {
                	System.out.println();
                    System.out.println("🐶 " + inputId +"님 환영합니다. 🐶");
                    return role;
                } else {
                	System.out.println();
                    System.out.println("비밀번호가 잘못되었습니다.");
                    return -1;
                }
            } else {
            	System.out.println();
                System.out.println("존재하지 않는 아이디입니다.");
                return -1;
            }
              
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}

		
        }
	

	// 회원가입 메소드
	public void join(String inputId, String inputPwd, String inputName) {
		String idCheckSql = "SELECT * FROM members WHERE id = ?";
		String insertSql = "INSERT INTO members (id, pwd, name) VALUES (?, ?, ?)";
		
	
		try {
			PreparedStatement pstmt = conn.prepareStatement(idCheckSql);
			pstmt.setString(1, inputId);			
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	        	System.out.println();
	            System.out.println("이미 존재하는 아이디입니다.");
	            return;
	        }
	        
	        pstmt = conn.prepareStatement(insertSql);
	        pstmt.setString(1, inputId);	
	        pstmt.setString(2, inputPwd);	
	        pstmt.setString(3, inputName);	
	        
	        int rows = pstmt.executeUpdate();
	        
	        if(rows > 0) {
	        	System.out.println();
	        	System.out.println("🐶 회원가입이 완료되었습니다. 🐶");
	        }else {
	        	System.out.println();
	        	System.out.println("회원가입에 실패했습니다. 다시 시도해주세요.");
	        }
	        pstmt.close();
	        
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	
	
	// 전체 회원 출력
    // 데이터베이스에 등록된 모든 회원 정보를 출력
    public void printMembers() {
        String sql = "SELECT * FROM members";
        int count = 0;
        String roleString = ""; // 0 | 1인 회원 역할을 문자열로 저장할 변수
       
		try {
			// rs.beforeFirst(); 사용 시 오라클은 전방향 오류가 나서  ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE을 넣어줌.
			PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery();
	        
            // ResultSet이 비어있는지 확인
            if (!rs.next()) {
                System.out.println("등록된 회원이 없습니다.");
                return; // 메소드 즉시 종료
            }
            	System.out.println("------------------------------------------------------------------------");
        	   System.out.println("\t\t\t\t🐶 전체 회원 목록 🐶");
        	   System.out.println("------------------------------------------------------------------------");
        	   
        	   rs.beforeFirst(); // 커서를 첫 번째 행 이전으로 이동
        	   
        	   while (rs.next()) {
        		   count++;
        		   String id = rs.getString("id");
//        		   String pwd = rs.getString("pwd");
        		   String name = rs.getString("name");
        		   int role = rs.getInt("role");
        		   
        		   if(role == 0) {roleString = "관리자";}
        		   else if(role == 1) {roleString = "회원";}
        		   
        		   // 회원 정보를 출력
        		   System.out.printf("%d. id: %s, name: %s, 권한: %s \n",
        				   count, id, name, roleString);
        		   System.out.println();
        	   }
        	   
           
        	   pstmt.close();
          
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        }
    
    
	// 회원 권한 변경
	public void changeRole(String inputId, int inputRole) {
		// 입력 값이 0|1이 아닐 경우 메소드 즉시 종료
		if (inputRole != 0 && inputRole != 1) {
        	System.out.println();
		    System.out.println("잘못된 권한 값입니다. 권한 값은 0 또는 1이어야 합니다.");
		    return;
		}

		String updateSql = "UPDATE members SET role = ? WHERE id = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(updateSql);
			pstmt.setInt(1, inputRole);		
			pstmt.setString(2, inputId);
	
	        int rows = pstmt.executeUpdate();
	        
	        if(rows > 0) {
	        	System.out.println();
	        	System.out.println("🐶 회원 권한이 정상적으로 수정되었습니다. 🐶");
	        	
	        }else {
	        	System.out.println();
	        	System.out.println("존재하지 않는 아이디입니다. 다시 시도해세요");
	        }
	        pstmt.close();
	        
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	
	
	
	
	
	

}
