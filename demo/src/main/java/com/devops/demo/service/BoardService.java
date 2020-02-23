package com.devops.demo.service;

import java.util.List;

import com.devops.demo.model.Board;

public interface BoardService {
	Board showOneData(int id); // list에서 특정 제목의 게시물을 클릭했을 때, 해당 id의 게시글 데이터 전체를 표출 (article, id로 검색)

	List<Board> showListByAuthor(String author); // author로 검색
	
	List<Board> showListByTitle(String title); // title로 검색
	
	List<Board> showListByDate(String date); // date로  검색
	
	List<Board> showList(); // list에 게시글 전체를 id 내림차순으로 표출

	int register(Board board); // 새 게시글 하나를 작성

	int deleteBoard(int id); // 해당 id에 대한 게시글 하나 삭제

	int updateBoard(int id, Board board); // 해당 id에 대한 게시글 수정
}
