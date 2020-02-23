package com.devops.demo.repository;

import java.util.List;

import com.devops.demo.model.Board;

public interface BoardRepository {
	Board getBoardById(int id); // list에서 특정 제목의 게시물을 클릭했을 때, 해당 id의 게시글 데이터 전체를 표출 (article)

	List<Board> getBoardByAuthor(String author); // author로 검색
	
	List<Board> getBoardByTitle(String title); // title로 검색
	
	List<Board> getBoardByDate(String date); // date로 검색
	
	List<Board> getBoard();	// list에 게시글 전체를 id 내림차순으로 표출

	int insertBoard(Board board); // 새 게시글 하나를 작성

	int deleteBoard(int id); // 해당 id에 대한 게시글 삭제
	
	int updateBoard(int id, Board board); // 해당 id에 대한 게시글 수정
}
