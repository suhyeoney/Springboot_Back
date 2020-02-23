package com.devops.demo.repository;

public class SQLquery {
	public static final String SELECT_BY_ID = "select * from team_board where id = ?";
	// list에서 특정 제목의 게시물을 클릭했을 때, 해당 id의 게시글 데이터 전체를 표출(article, id로 검색)
	
	public static final String SELECT_BOARD_BY_AUTHOR = "select * from team_board where author like ? order by id desc";
	//author 검색

	public static final String SELECT_BOARD_BY_TITLE = "select * from team_board where title like ? order by id desc";
	//title 검색

	public static final String SELECT_BOARD_BY_DATE = "select * from team_board where date = ? order by id desc";
	//date 검색
	
	public static final String SELECT_ALL = "select * from team_board order by id desc"; 
	// list에 게시글 전체를 id 내림차순으로 표출(list)
	
	public static final String INSERT_BOARD = "insert into team_board (author, title, content, password) values (?,?,?,?)";
	// 새 게시글 하나를 작성

	public static final String DELETE_DATA = "delete from team_board where id = ?"; 
	// 해당 id에 대한 게시글 삭제

	public static final String UPDATE_DATA = "update team_board set author = ?, title = ?, content = ? where id = ?"; 
	// 해당 id에 대한 게시글 수정
}
