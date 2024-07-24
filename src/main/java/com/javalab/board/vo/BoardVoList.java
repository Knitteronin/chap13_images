package com.javalab.board.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * BoardVo를 여러개 한꺼번 바인딩해주는 Data Transfer Object(DTO)
 * DTO & Vo 차이점
 * - Vo 주로 데이터베이서의 테이블과 거의 비슷한 불변의 구조
 * - Dto : 주로 화면의 데이터를 바인딩하고 그걸 다른 레이어로 전달해주는
 *   순순한 데이터 전달자의 역할.
 * @Data
 * - 기본생성자
 * - 세터/게터 메소드
 * - ToString
 * - equals & hashcode 메소드
 *
 */
@Data
public class BoardVoList {
	// 속성, 멤버 변수
	private List<BoardVo> list; // 게시물 목록을 담는 List<BoardVo>
	
	public BoardVoList() {
		this.list = new ArrayList<BoardVo>();
	}
}
