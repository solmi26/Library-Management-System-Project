📚 Library Management System

### **프로젝트 개요**

망고 도서관 관리 시스템(Mango Library Management System)은

도서관의 책을 **검색하고, 대출 및 반납을 관리하며,  도서 정보, 회원 정보**를 관리할 수 있도록 설계된 자바 콘솔 애플리케이션입니다. 이 시스템은 `JDBC`를 이용하여 오라클 데이터베이스와 연결되어 있으며, 비회원/회원/관리자로 접근 제한을 두어 사용할 수 있는 기능을 구분하였습니다.

---

### **🔧 개발 환경**

- **언어**: Java
- **데이터베이스**: Oracle Database
- **기술**: JDBC

---

### **⚙️ 클래스 구조**

### 1. **`Book` 클래스**

**역할**:

- 도서의 정보를 저장하는 객체를 생성하는 클래스입니다.

**주요 필드**:

- `title`: 책의 제목
- `writer`: 책의 저자
- `publisher`: 책의 출판사
- `isbn`: 국제 표준 도서 번호
- `price`: 가격

**주요 메소드**:

- `getters` 및 `setters`: 각 필드에 접근하고 수정하기 위한 메소드들
- `toString()`: 도서의 정보를 문자열로 출력할 수 있는 메소드

### 2. **`DatabaseConnection` 클래스**

**역할**:

- 오라클 데이터베이스와의 연결을 관리하는 유틸리티 클래스입니다.

**주요 메소드**:

- `getConn()`: 데이터베이스와 연결을 설정하고 `Connection` 객체를 반환하는 메소드
- `closeConnection(Connection conn)`: 데이터베이스 연결을 종료하는 메소드

### 3. **`Library` 클래스**

**역할**:

- 도서 관련 기능을 관리하는 클래스입니다. 도서 검색, 추가, 삭제, 대출, 반납 등의 기능을 제공합니다.

**주요 메소드**:

- `selectAllBooks()`: 모든 보유 도서 목록을 출력
- `searchBooks(String title)`: 제목을 기준으로 도서를 검색
- `addBook(Book book)`: 새로운 도서를 추가
- `updateBook(String isbn, Book updatedBook)`: 도서 정보를 수정
- `deleteBook(String isbn)`: 도서를 삭제
- `borrowBook(String inputId, String inputTitle)`: 도서를 대출
- `returnBook(String inputId, String inputTitle)`: 도서를 반납
- `selectMyBorrow(String inputId)` : 해당 사용자의 모든 대출/반납 현황 출력

### 4. **`Member` 클래스**

**역할**:

- 회원 관리 기능을 담당하는 클래스입니다. 회원 가입, 로그인, 권한 수정 등의 기능을 제공합니다.

**주요 메소드**:

- `login(String id, String pwd)`: 회원의 로그인 처리
- `join(String inputId, String inputPwd, String inputName)`: 회원 가입 처리
- `updateRole(String userId, int newRole)`: 회원의 권한을 수정
- `printMembers()` : 전체 회원 출력

### 5. **`MangoLibraryMain` 클래스**

**역할**:

- 메인 애플리케이션으로, 사용자 인터페이스를 제공하며 사용자가 선택한 메뉴에 따라 다른 기능을 호출 및 실행합니다.

---

### **🛠 프로젝트 기능**

### **1. 회원 관리**

- **회원 가입**: 새로운 사용자가 ID와 비밀번호, 이름을 입력하여 회원으로 가입할 수 있습니다.
- **로그인**: 사용자가 ID와 비밀번호로 로그인할 수 있으며, 로그인 성공 시 역할에 따라 접근 가능한 메뉴가 다릅니다.
- **권한 수정**: 관리자는 특정 회원의 권한(일반 회원 또는 관리자)을 수정할 수 있습니다.

### **2. 도서 관리**

- **도서 검색**: 사용자는 제목을 입력하여 도서를 검색할 수 있습니다.
- **도서 추가**: 관리자는 새로운 도서를 시스템에 추가할 수 있습니다.
- **도서 수정**: 관리자는 기존 도서의 정보를 수정할 수 있습니다.
- **도서 삭제**: 관리자는 도서를 삭제할 수 있습니다.
- **전체 도서 목록 조회**: 현재 보유 중인 모든 도서를 조회할 수 있습니다.
- **도서 대출/반납** : 사용자는 도서를 대출/반납할 수 있습니다.
- **대출/반납 현황 조회** : 사용자가 자신이 대출/반납한 모든 현황을 조회할 수 있습니다.

---

### **🔄 프로그램 흐름**

1. **시작**: 프로그램이 실행되면 사용자는 로그인, 회원가입, 비회원 도서 검색, 종료 중 하나를 선택합니다.
2. **로그인 전**:
    - 사용자가 로그인하지 않은 상태에서는 비회원 도서 검색 기능만 사용 가능합니다.
3. **로그인**:
    - 사용자가 로그인에 성공하면 역할에 따라 다른 메뉴에 접근할 수 있습니다.
    - `일반 회원`: 도서 검색 및 도서 대출/반납 기능을 사용할 수 있습니다.
    - `관리자`: 도서 관리 및 회원 관리 기능을 사용할 수 있습니다.
4. **메뉴 선택**:
    - 사용자는 메뉴를 선택하여 다양한 기능을 수행할 수 있습니다.
5. **종료**:
    - 사용자가 종료를 선택하면 프로그램이 종료되고, 데이터베이스 연결이 해제됩니다.

---

### **✨ 주요 코드**

업로드 예정

---

### **📄 DB 스크립트**

```sql
-- 관리자 스크립트 
-- 계정생성
CREATE user library IDENTIFIED by 1234;

GRANT connect, resource, dba to library;

GRANT unlimited tablespace to Library;

-- 도서관 스크립트
-- Members 테이블: 회원 정보를 저장
-- Books 테이블: 도서 정보를 저장
-- Borrow 테이블: 대출/반납 정보를 저장

-- 회원 관리 테이블
CREATE TABLE members (
    id VARCHAR(10) PRIMARY KEY,
    pwd VARCHAR(15) NOT NULL,
    name VARCHAR(50) NOT NULL
);

ALTER TABLE members ADD (role NUMBER(1) DEFAULT 1);

--  도서 관리 테이블
CREATE TABLE books (
    bno NUMBER PRIMARY KEY,   
    title VARCHAR(50) NOT NULL,               
    writer VARCHAR(255) NOT NULL,              
    publisher VARCHAR(255) default '-',                    
    isbn VARCHAR(20) default '-',                 
    price NUMBER,                      
    stock NUMBER NOT NULL,                 
    borrowedStock NUMBER default 0         
);

CREATE SEQUENCE seq_bno NOCACHE;

-- 대출 관리 테이블
CREATE TABLE borrow (
    bid NUMBER PRIMARY KEY,                
    member_id VARCHAR(10) NOT NULL,       -- 회원 ID (외래 키)
    borrow_date DATE DEFAULT SYSDATE,     -- 대출 일자
    return_date DATE,                     -- 반납 일자
    CONSTRAINT fk_member_id FOREIGN KEY (member_id) REFERENCES members(id)
);

CREATE SEQUENCE seq_bid NOCACHE;

ALTER TABLE borrow
ADD (book_title VARCHAR(50));

-- 책 1
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, '해리포터와 마법사의 돌', 'J.K. Rowling', '문학세계', '9780747532743', 15000, 20);

-- 책 2
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Scribner', '9780743273565', 18000, 15);

-- 책 3
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, '노르웨이의 숲', 'Haruki Murakami', 'Vintage', '9780375704024', 20000, 10);

-- 책 4
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, '1984', 'George Orwell', 'Secker & Warburg', '9780451524935', 17000, 12);

-- 책 5
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, '자바의 정석', '남궁성', '도우출판', '9788979140765', 25000, 25);

-- 책 6
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, 'Pride and Prejudice', 'Jane Austen', 'T. Egerton', '9781503290563', 16000, 18);

-- 책 7
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, 'The Catcher in the Rye', 'J.D. Salinger', 'Little, Brown and Company', '9780316769488', 19000, 20);

-- 책 8
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, '죽은 자의 집 청소', '가제트', '지식과감성', '9788901203102', 22000, 14);

-- 책 9
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, 'Brave New World', 'Aldous Huxley', 'Chatto & Windus', '9780060850524', 18000, 17);

-- 책 10
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, '명량', '김한민', '역사문화', '9788901065683', 27000, 8);

-- 책 11
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, 'To Kill a Mockingbird', 'Harper Lee', 'J.B. Lippincott & Co.', '9780060935467', 19000, 22);

-- 책 12
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, '1984', 'George Orwell', 'Signet Classics', '9780451524935', 17000, 16);

-- 책 13
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, 'The Alchemist', 'Paulo Coelho', 'HarperOne', '9780062315007', 16000, 19);

-- 책 14
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, '시간을 파는 상점', '김선영', '서울문학', '9788952780121', 23000, 11);

-- 책 15
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, 'The Hitchhiker\'s Guide to the Galaxy', 'Douglas Adams', 'Pan Books', '9780330508537', 17000, 13);

-- 책 16
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, '추리소설의 기술', '미야베 미유키', '파란', '9788957830237', 25000, 9);

-- 책 17
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, 'Moby Dick', 'Herman Melville', 'Harper & Brothers', '9781851244422', 20000, 7);

-- 책 18
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, '호밀밭의 파수꾼', 'J.D. Salinger', 'Little, Brown and Company', '9780316769488', 18000, 21);

-- 책 19
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, '가시리', '박완서', '문학과지성사', '9788937484671', 21000, 12);

-- 책 20
INSERT INTO books (bno, title, writer, publisher, isbn, price, stock) 
VALUES (seq_bno.NEXTVAL, 'The Road', 'Cormac McCarthy', 'Vintage', '9780307387899', 22000, 15);

-- 일반 회원
INSERT INTO members (id, pwd, name)
            VALUES ('abc123', '1234', '이망고');
            
INSERT INTO members (id, pwd, name)
            VALUES ('solmi26', '1234', '진솔미');

-- 관리자
INSERT INTO members (id, pwd, name, role)
    VALUES ('admin', '1234', 'admin', 0);

```

---

### **💡 향후 개선점**

- **UI 개선**: 현재 콘솔 기반의 UI를 GUI 기반으로 변경하기.
- **예외 처리 강화**: 다양한 예외 상황에 대한 처리를 강화하여 안정성을 높이기.
- **게시판 구현** : 현재 서비스 준비 중이라고 처리된 게시판 부분을 구현하기.
- **개인정보 관리, 로그아웃, 탈퇴** : 해당 기능 추가하기.
- **가독성 좋게 만들기** : 출력문을 사용자가 더 쉽게 확인할 수 있도록 출력문 수정하기.

---
