[애플리케이션 개요]
- 상품 등록시 여러 이미지 등록
- 상품 테이블과 이미지 테이블이 1:N 관계로 매핑됨.


[데이터베이스]
	--create user product identified by 1234;
	--grant all privileges to product;
	--grant unlimited tablespace to product;
	
	
	-- Product Table
	-- drop table product;
	CREATE TABLE product (
	    product_id NUMBER ,
	    name VARCHAR2(100) NOT NULL,
	    description VARCHAR2(1000),
	    unit_price NUMBER(10) default 0,
	    reg_date DATE DEFAULT SYSDATE,
	    constraint pk_product primary key(product_id)
	);
	
	-- Product Image Table
	CREATE TABLE prod_img (
	    img_id NUMBER,
	    product_id NUMBER not null,
	    img_path VARCHAR2(500),
	    file_name VARCHAR2(500),
	    is_main NUMBER(1) DEFAULT 0,
	    constraint pk_prod_img primary key(img_id),
	    constraint fk_product_id FOREIGN KEY (product_id) REFERENCES product (product_id)
	);
	
	-- Sequence for product_id
	CREATE SEQUENCE seq_product
	START WITH 1
	INCREMENT BY 1
	NOCACHE
	NOCYCLE;
	
	-- Sequence for image_id
	CREATE SEQUENCE seq_img
	START WITH 1
	INCREMENT BY 1
	NOCACHE
	NOCYCLE;

[프로그램]

1. webapp/resources/upload 폴더 생성
 - 이미지 업로드 폴더
 - 실제로 다음과 같은 배포 경로에 폴더가 생성된다.
   C:\javaworks\workspace\springlegacy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\해당프로젝트\resources\upload\년\월\일
   
2.    
   