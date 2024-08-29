package 진솔미;

import java.sql.Connection;
import java.util.Scanner;

public class MangoLibraryMain {

	public static void main(String[] args) {
		
		Connection conn = DatabaseConnection.getConn(); // DB 연동 및 객체 생성
		Library library = new Library(conn); // 도서관 객체 생성
		Member member =  new Member(conn); // 회원 객체 생성
		Scanner scanner = new Scanner(System.in);
		boolean run = true; // 프로그램 실행/종료를 결정
		int role = -1; // 사용자 권한 (-1은 비로그인 상태)
		String id = ""; // 로그인 중인 회원의 id 저장


        System.out.println("🐶 망고의 도서관에 오신 것을 환영합니다. 🐶");
        System.out.println("🐶 비회원은 도서 검색만 가능하오니 기타 서비스는 로그인이나 회원가입 후 이용해주세요. 🐶");
//        System.out.println();
		
		while(run) {
			try {
			// 비회원 메뉴 (role == -1)
			if(role == -1) {
			System.out.println("----------------------------------------");
			System.out.println("1.로그인 2.회원가입 3.비회원 도서 검색 4.종료");
			System.out.println("----------------------------------------");
			System.out.print("선택> ");
			
		
				int choiceMenu = Integer.parseInt(scanner.nextLine());
				
				switch (choiceMenu) {
				// 로그인 로직
				case 1:
					System.out.print("아이디 : ");
					id = scanner.nextLine();
					
					System.out.print("비밀번호 : ");
					String pwd = scanner.nextLine();
					
					// member 객체의 로그인 메소드 호출(role 반환)
					role = member.login(id,pwd);
					break;
					
				// 회원가입 로직
				case 2:
					System.out.print("사용하실 아이디 : ");
					String inputId = scanner.nextLine();
					
					System.out.print("사용하실 비밀번호 : ");
					String inputPwd = scanner.nextLine();
					
					System.out.print("이름 : ");
					String inputName = scanner.nextLine();
							
					// member 객체의 회원가입 메소드 호출
					member.join(inputId, inputPwd, inputName);
		
					break;
					
				// 비회원 도서 검색 로직
				case 3:
					System.out.println("--------------------------------------");
					System.out.println("1.보유 도서 전체 목록 2.도서 검색 3.종료");
					System.out.println("--------------------------------------");
					System.out.print("선택> ");
					int searchChoice = Integer.parseInt(scanner.nextLine());
					
					switch (searchChoice) {
					// 전체 도서 목록 출력
					case 1:
						library.selectAllBooks();
						break;
						
					// 책 제목으로 검색
					case 2:
						System.out.print("검색할 책의 제목을 입력해주세요 : ");
						String title = scanner.nextLine();
						library.searchBooks(title);
						break;
						
					// 종료 로직
					case 3:
						run = false;
						System.out.println();
						System.out.println("🐶 이용해주셔서 감사합니다. 🐶");
						break;
						
					default:
						System.out.println();
						System.out.println("1 ~ 3을 선택해주새요.");
						break;
					}
					
					break;
					
				// 종료 로직
				case 4:
					run = false;
					System.out.println();
					System.out.println("🐶 이용해주셔서 감사합니다. 🐶");
					break;

				default:
					System.out.println();
					System.out.println("1 ~ 4를 입력해주세요.");
					break;
				}
			}
				// 일반 회원 메뉴 (role == 1)
				else if(role == 1) {
					System.out.println("-------------------------------------------------------------------------------");
					System.out.println("1.보유 도서 전체 목록 2.도서 검색 3.대출하기 4.반납하기 5.대출/반납 현황 6.게시판 7.종료");
					System.out.println("-------------------------------------------------------------------------------");
					System.out.print("선택> ");
					int choiceMenu = Integer.parseInt(scanner.nextLine());
					
					switch (choiceMenu) {
					// 보유 도서 전체 목록
					case 1:
						library.selectAllBooks();
						break;
						
					// 책 제목으로 검색
					case 2:
						System.out.print("검색할 책의 제목을 입력해주세요 : ");
						String title = scanner.nextLine();
						library.searchBooks(title);
						break;
						
					// 책 대출 로직
					case 3:
//						System.out.println(id);
						System.out.print("대출하실 책 제목을 입력해주세요. : ");
						String inputTitle = scanner.nextLine();
						library.borrowBook(id, inputTitle);
						break;
						
					// 책 반납 로직
					case 4:
						System.out.print("반납하실 책 제목을 입력해주세요. : ");
						String inputTitle2 = scanner.nextLine();
						library.returnBook(id, inputTitle2);
						break;
						
					// 대출/반납 현황 확인
					case 5:
						library.selectMyBorrow(id);
						break;
						
						
					// 게시판 로직(미구현)
					case 6:
						System.out.println();
						System.out.println("게시판 서비스 준비 중입니다.");
						break;
						
					// 종료 로직
					case 7:
						run = false;
						System.out.println();
						System.out.println("🐶 이용해주셔서 감사합니다. 🐶");
						break;

					default:
						System.out.println();
						System.out.println("1 ~ 7을 입력해주세요.");
						break;
					}
					
				// 관리자 메뉴 (role == 0)
				}else if(role == 0) {
					System.out.println("----------------------------");
					System.out.println("1.도서관리 2.회원관리 3.종료");
					System.out.println("----------------------------");
					System.out.print("선택> ");
					int choiceMenu = Integer.parseInt(scanner.nextLine());
					
					switch (choiceMenu) {
					// 도서관리
					case 1:
						System.out.println("----------------------------------------------------------------------");
						System.out.println("1.보유 도서 전체 목록 2.도서 검색 3.도서 추가 4.도서 정보 수정 5.도서 삭제 6.종료");
						System.out.println("----------------------------------------------------------------------");
						System.out.print("선택> ");
						int searchChoice = Integer.parseInt(scanner.nextLine());
						
						switch (searchChoice) {
						// 보유 도서 전체 목록
						case 1:
							library.selectAllBooks();
							break;
							
						// 책 제목으로 검색
						case 2:
							System.out.print("검색할 책의 제목을 입력해주세요 : ");
							String title = scanner.nextLine();
							library.searchBooks(title);
							break;
							
						// 도서 추가
						case 3:
							System.out.println("추가할 책의 정보를 입력해주세요.");
							System.out.print("제목 : ");
                            String addTitle = scanner.nextLine();

                            System.out.print("저자 : ");
                            String writer = scanner.nextLine();

                            System.out.print("출판사 : ");
                            String publisher = scanner.nextLine();

                            System.out.print("ISBN : ");
                            String isbn = scanner.nextLine();

                            System.out.print("가격 : ");
                            int price = Integer.parseInt(scanner.nextLine());

                            System.out.print("재고 수량 : ");
                            int stock = Integer.parseInt(scanner.nextLine());

                            // Book 객체 생성
                            Book newBook = new Book(addTitle, writer, publisher, isbn, price, stock);
                            library.addBook(newBook);
                            break;
							
                         // 도서 정보 수정
						case 4:
							System.out.print("수정할 책의 bno을 입력해주세요. : ");
							int bno = Integer.parseInt(scanner.nextLine());
							System.out.println();
							System.out.println("수정할 내용을 입력해주세요.");
							System.out.print("제목 : ");
                            String updateTitle = scanner.nextLine();

                            System.out.print("저자 : ");
                            String updateWriter = scanner.nextLine();

                            System.out.print("출판사 : ");
                            String updatePublisher = scanner.nextLine();

                            System.out.print("ISBN : ");
                            String updateIsbn = scanner.nextLine();

                            System.out.print("가격 : ");
                            int updatePrice = Integer.parseInt(scanner.nextLine());

                            System.out.print("재고 수량 : ");
                            int updateStock = Integer.parseInt(scanner.nextLine());

                            // Book 객체 생성
                            Book updatedBook = new Book(updateTitle, updateWriter, updatePublisher, updateIsbn, updatePrice, updateStock);
                            library.updateBook(bno, updatedBook);
                            break;
                            
                         // 도서 삭제
						case 5:
							System.out.print("삭제할 책의 bno을 입력해주세요. : ");
							int deleteBno = Integer.parseInt(scanner.nextLine());
							library.deleteBook(deleteBno);
							break;
							
						// 종료 로직
						case 6:
							run = false;
							System.out.println();
							System.out.println("🐶 이용해주셔서 감사합니다.  🐶");
							break;

						default:
							System.out.println();
							System.out.println("1 ~ 6을 입력해주세요.");
							break;
						}
						break;
						
					// 회원 관리	
					case 2:
						System.out.println("----------------------------------------------------------------------");
						System.out.println("1.회원 목록 2.회원 권한 변경 3.종료");
						System.out.println("----------------------------------------------------------------------");
						System.out.print("선택> ");
						int searchChoice2 = Integer.parseInt(scanner.nextLine());
						
						switch (searchChoice2) {
						// 회원 목록 출력
						case 1:
							member.printMembers();
							break;
							
						// 회원 권한 변경
						case 2:
							System.out.print("권한을 변경할 회원의 아이디를 입력해주세요 : ");
							String inputId = scanner.nextLine();
							System.out.println("권한을 어떻게 수정하시겠습니까?");
							System.out.print("1(회원) / 0(관리자) : ");
							int inputRole = Integer.parseInt(scanner.nextLine());
							member.changeRole(inputId, inputRole);
							
							break;
							
						// 종료 로직
						case 3:
							run = false;
							System.out.println();
							System.out.println("🐶 이용해주셔서 감사합니다. 🐶");
							break;

						default:
							System.out.println();
							System.out.println("1 ~ 3을 입력해주세요.");
							break;
						}

						break;
						
					// 종료 로직
					case 3:
						run = false;
						System.out.println();
						System.out.println("🐶 이용해주셔서 감사합니다. 🐶");
						break;

					default:
						System.out.println();
						System.out.println("1 ~ 3을 입력해주세요.");
						break;
					}
				}
			
			}catch (NumberFormatException e) {
				System.out.println();
				System.out.println("숫자를 입력해주세요");
			}
		}
		
		// 프로그램 종료 시 리소스 해제
		scanner.close();
		DatabaseConnection.closeConnection(conn);
		

	}

}
