package 진솔미;

public class Book {
	// 필드
	private int bno; // 책 no
	private String title; // 제목
	private String writer; // 저자
	private String publisher; // 출판사
	private String isbn; // isbn
	private int price; // 가격
	
	private int stock; // 보유 권수
	private int borrowedStock = 0; // 대출된 책의 수량
	
	
	// 생성자
    public Book(String title, String writer, String publisher, String isbn, int price, int stock) {
        this.title = title;
        this.writer = writer;
        this.publisher = publisher;
        this.isbn = isbn;
        this.price = price;
        this.stock = stock;
    }

	// 메소드
    // Getter/Setter
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getBorrowedStock() {
		return borrowedStock;
	}

	public void setBorrowedStock(int borrowedStock) {
		this.borrowedStock = borrowedStock;
	}
	
	
//	// toString 오버라이드하여 출력문으로 사용
//	@Override
//	public String toString() {
//		  return String.format("제목: %s, 저자: %s, 출판사: %s, 대출 가능한 수량: %d, isbn: %s, 가격: %d", title, writer, publisher, stock, isbn, price);
//	}
	
 
	
	

}
