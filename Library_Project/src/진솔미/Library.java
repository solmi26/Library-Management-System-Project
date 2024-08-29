package 진솔미;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Library 클래스는 도서 대출/반납, 도서 대출 현황, 도서 검색, 도서 현황 출력, 도서 추가/삭제/수정 기능을 관리합니다.
 */


public class Library {
	// 필드
	private Connection conn; // 데이터베이스 연결 객체
	
	// 생성자
    public Library(Connection conn) {
        this.conn = conn;
        
        try {
        	// 자동 커밋 설정
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			System.out.println("자동 커밋 실패");
			e.printStackTrace();
		}
    }

	// 메소드
	// 전체 도서 출력
//	public void showBooks() {
//		if(books.isEmpty()) {
//			System.out.println("등록된 도서가 없습니다.");
//		}else {
//			System.out.println("🐶전체 도서 목록🐶");
//			int count = 1; //  리스트 번호
//			for(Book book : books) {
//				System.out.println(count + ". " + book);
//				count++;
//			}
//		}
//	}

//	public void searchBook(String title) {
//		int count = 1; // 리스트 번호
//		boolean find = false; // 검색어로 찾은경우 true 못 찾으면 false
//
//		for(Book book : books) {
//			// 앞뒤 공백, 모든 공백 제거 후 소문자로 변환하여 검색(사용자가 원하는 데어터를 더 정확하게 출력하기 위함)
//	//		if(book.getTitle().contains(title)) {
//			// contains(CharSequence s) => 문자를 순차적으로 비교하여 부분 문자열을 찾음. 부분 문자열이 어느 위치에든 포함되어 있으면 true
//			if(book.getTitle().trim().replace(" ", "").toLowerCase().contains(title.trim().replace(" ", "").toLowerCase())) {
//				find = true;
//				System.out.println(count + ". " + book);
//				count++;
//			}			
//		}
//		if(!find) {System.out.println("해당 제목의 도서를 찾을 수 없습니다.");}
//		
//	}
	
    // 전체 도서 출력
    // 데이터베이스에서 모든 도서 정보를 조회하고 출력하는 메소드
    public void selectAllBooks() {
        String sql = "SELECT * FROM books";
       
		try {
			// rs.beforeFirst(); 사용 시 오라클은 전방향 오류가 나서  ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE을 넣어줌.
			PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery();
	        int count = 0;
            // ResultSet이 비어있는지 확인
            if (!rs.next()) {
            	System.out.println();
                System.out.println("등록된 도서가 없습니다.");
                return; // 메소드 즉시 종료
            }
            
               System.out.println("-----------------------------------------------------------------------------------------------");
        	   System.out.println("\t\t\t\t🐶 전체 도서 목록 🐶");
               System.out.println("-----------------------------------------------------------------------------------------------");
               
        	   
        	   rs.beforeFirst();
        	   while (rs.next()) {
        		   int bno = rs.getInt("bno");
        		   count++;
        		   String title = rs.getString("title");
        		   String writer = rs.getString("writer");
        		   String publisher = rs.getString("publisher");
        		   String isbn = rs.getString("isbn");
        		   int price = rs.getInt("price");
        		   int stock = rs.getInt("stock");
        		   int borrowedStock = rs.getInt("borrowedStock");
        		   
        		   // 도서 정보를 출력
        		   System.out.printf("%d. 제목: %s, 저자: %s, 출판사: %s, ISBN: %s, 가격: %d, 대출 가능한 권수: %d \n",
        				   count, title, writer, publisher, isbn, price, stock - borrowedStock);
        		   System.out.println();
        	   }
        	   
           
        	   pstmt.close();
          
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        }
    
    
    // 도서 검색
    // 제목으로 도서를 검색하여 출력하는 메소드
    public void searchBooks(String searchTitle) {
		// 부분 검색 할 수 있도록
    	// books의 title 칼럼을 대문자로 바꾸고 공백을 없애고 like로 부분 검색
    	String sql = "SELECT * FROM books WHERE REPLACE(UPPER(title), ' ', '') LIKE ?";
    	// 검색할 대상도 공백 없애고 대문자로 바꿈
        String upSearchTitle = "%" + searchTitle.trim().replace(" ", "").toUpperCase() + "%"; 
        int no = 0;
        
        try {
			PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        pstmt.setString(1, upSearchTitle);
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (!rs.next()) {
	        	System.out.println();
                System.out.println("찾으시는 도서가 없습니다.");
                return; // 메소드 즉시 종료
            }
            
               System.out.println("-----------------------------------------------------------------------------------------------");
        	   System.out.println("\t\t\t\t🐶 검색을 완료하였습니다. 🐶");
               System.out.println("-----------------------------------------------------------------------------------------------");
        	   
        	   rs.beforeFirst();
        	   while (rs.next()) {
        		   no++;
        		   String title = rs.getString("title");
        		   String writer = rs.getString("writer");
        		   String publisher = rs.getString("publisher");
        		   String isbn = rs.getString("isbn");
        		   int price = rs.getInt("price");
        		   int stock = rs.getInt("stock");
        		   int borrowedStock = rs.getInt("borrowedStock");
        		   
        		   // 도서 정보를 출력
        		   System.out.printf("%d. 제목: %s, 저자: %s, 출판사: %s, ISBN: %s, 가격: %d, 대출 가능한 권수: %d \n",
        				   no, title, writer, publisher, isbn, price, stock - borrowedStock);
        		   System.out.println();
        	   }
	        
	        
        	   pstmt.close();
	        
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        }
    
    
		// 대출 메소드
    	// 책 이름으로 대출
		public void borrowBook(String inputId, String inputTitle) {
			String sql = "SELECT stock, borrowedStock FROM books WHERE title = ?";
			
		    try {
				    PreparedStatement pstmt = conn.prepareStatement(sql);
				    pstmt.setString(1, inputTitle);
				    ResultSet rs = pstmt.executeQuery();
		        
			        if (rs.next()) {
			            int stock = rs.getInt("stock");
			            int borrowedStock = rs.getInt("borrowedStock");
			            
			            // 책의 재고가 0보다 큰지 확인(대출 가능 여부 판단)
			            if (stock - borrowedStock > 0) {
			                sql = "INSERT INTO borrow (bid, member_id, book_title, borrow_date) VALUES (seq_bid.nextval, ?, ?, SYSDATE)";
			                pstmt = conn.prepareStatement(sql);
			                pstmt.setString(1, inputId);
			                pstmt.setString(2, inputTitle);
			                pstmt.executeUpdate();
			                
			                // 대출 중 수량 증가
			                sql = "UPDATE books SET borrowedStock = borrowedStock + 1 WHERE title = ?";
			                pstmt = conn.prepareStatement(sql);
			                pstmt.setString(1, inputTitle);
			                pstmt.executeUpdate();
			                
			                System.out.println();
			                System.out.println("🐶 대출이 완료되었습니다. 재밌게 읽으세요! 🐶");
			            } else {
			            	System.out.println();
			            	System.out.println("현재 대출 가능한 재고가 없습니다.");	
			            }
			        } else {
			        	System.out.println();
			            System.out.println("해당 제목의 책이 없습니다.");
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			}

		
		// 도서 반납 메소드
		// 책 이름으로 반납
		public void returnBook(String inputId, String inputTitle) {
		    // 반납할 도서가 있는지 확인
		    String selectSql = "SELECT * FROM borrow WHERE member_id = ? AND book_title = ? AND return_date IS NULL";
		    // 반납 일자 추가
		    String updateSql = "UPDATE borrow SET return_date = SYSDATE WHERE member_id = ? AND book_title = ? AND return_date IS NULL";
		    // 대출 중 수량 줄이기
		    String updateBookSql = "UPDATE books SET borrowedStock = borrowedStock - 1 WHERE title = ?";

		    try {
		        PreparedStatement pstmt = conn.prepareStatement(selectSql);
		        pstmt.setString(1, inputId);
		        pstmt.setString(2, inputTitle);
		        ResultSet rs = pstmt.executeQuery();
		        
		        // 대출 기록이 존재하면 반납 처리
		        if (rs.next()) {
		            // 반납 일자 추가
		            pstmt = conn.prepareStatement(updateSql);
		            pstmt.setString(1, inputId);
		            pstmt.setString(2, inputTitle);
		            pstmt.executeUpdate();
		            
		            // 대출중 수량 줄이기
		            pstmt = conn.prepareStatement(updateBookSql);
		            pstmt.setString(1, inputTitle);
		            pstmt.executeUpdate();
		            
		            System.out.println();
		            System.out.println("🐶 도서 반납이 완료되었습니다. 감사합니다! 🐶");
		        } else {
		        	System.out.println();
		            System.out.println("해당 도서의 대출 기록이 없거나 이미 반납되었습니다.");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }}
		
		
		 // 대출현황
		 public void selectMyBorrow(String inputId) {
		        String sql = "SELECT * FROM borrow WHERE member_id = ?";
		        int count = 0;
				try {
					// rs.beforeFirst(); 사용 시 오라클은 전방향 오류가 나서  ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE을 넣어줌.
					PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
					pstmt.setString(1, inputId);
			        ResultSet rs = pstmt.executeQuery();
			        
		            // ResultSet이 비어있는지 확인
		            if (!rs.next()) {
		            	System.out.println();
		                System.out.println("대출/반납 기록이 없습니다.");
		                return; // 메소드 즉시 종료
		            }
		            
		               System.out.println("-----------------------------------------------------------------------------------------------");
		        	   System.out.println("\t\t\t\t🐶 나의 대출/반납 현황🐶");
		               System.out.println("-----------------------------------------------------------------------------------------------");
		               
		        	   rs.beforeFirst();
		        	   while (rs.next()) {
		        		   count++;
		        		   String title = rs.getString("book_title");
		        		   Date borrow_date = rs.getDate("borrow_date");
		        		   Date return_date = rs.getDate("return_date");
		        		    
		        		   // 반납일이 null인 경우 대출중으로 표시
		        		   String returnDate  = (return_date != null) ? return_date.toString() : "대출중";
		        		   
		        		   
		     
		        	      System.out.printf("%d. 제목: %s, 대출일자: %s, 반납일자: %s%n",count, title, borrow_date, returnDate);
		        	      System.out.println();
		                  }
		        	   
		           
		        	   pstmt.close();
		          
		            
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


		        }
		 
		 
		 	// 도서 추가
		    public void addBook(Book book) {
		        String sql = "INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) VALUES (seq_bno.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		        try {
		        	PreparedStatement pstmt = conn.prepareStatement(sql);
		            pstmt.setString(1, book.getTitle());
		            pstmt.setString(2, book.getWriter());
		            pstmt.setString(3, book.getPublisher());
		            pstmt.setString(4, book.getIsbn());
		            pstmt.setInt(5, book.getPrice());
		            pstmt.setInt(6, book.getStock());
		            
			        int rows = pstmt.executeUpdate();
			        
			        if(rows > 0) {
			        	System.out.println();
			        	System.out.println("🐶 도서 등록이 완료되었습니다. 🐶");
			        }else {
			        	System.out.println();
			        	System.out.println("도서 등록에 실패했습니다. 다시 시도해주세요.");
			        }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    
		 
		    // 도서 수정
		    public void updateBook(int bno, Book book) {
		        String sql = "UPDATE books SET title = ?, writer = ?, publisher = ?, isbn = ?, price = ?, stock = ? WHERE bno = ?";
		        try {PreparedStatement pstmt = conn.prepareStatement(sql);
		            pstmt.setString(1, book.getTitle());
		            pstmt.setString(2, book.getWriter());
		            pstmt.setString(3, book.getPublisher());
		            pstmt.setString(4, book.getIsbn());
		            pstmt.setInt(5, book.getPrice());
		            pstmt.setInt(6, book.getStock());
		            pstmt.setInt(7, bno);
		            
		            int rows = pstmt.executeUpdate();
			        
			        if(rows > 0) {
			        	System.out.println();
			        	System.out.println("🐶 도서 수정이 완료되었습니다. 🐶");
			        }else {
			        	System.out.println();
			        	System.out.println("도서 수정에 실패했습니다. 다시 시도해주세요.");
			        }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    
		 
		    // 도서 삭제
			public void deleteBook(int deleteBno) {
				   String sql = "DELETE FROM books WHERE bno = ?";
			        try { 
			        	PreparedStatement pstmt = conn.prepareStatement(sql);
			            pstmt.setInt(1, deleteBno);
			            int rows = pstmt.executeUpdate();
			            
				        if(rows > 0) {
				        	System.out.println();
				        	System.out.println("🐶 도서 삭제가 완료되었습니다. 🐶");
				        }else {
				        	System.out.println();
				        	System.out.println("도서 삭제에 실패했습니다. 다시 시도해주세요.");
				        }
			            
			            
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }
			}
			    
}

 


 
    
   
	
//	// JDBC 활용 전 코드
//	   public void addBook(Book book) {
//	        books.add(book);
//	    }
	
	
	

