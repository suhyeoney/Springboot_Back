package com.devops.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.devops.demo.model.Board;
import com.devops.demo.security.EncDec;

@Repository
public class BoardRepositoryImple implements BoardRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Board getBoardById(int id) {
		return this.jdbcTemplate.queryForObject(SQLquery.SELECT_BY_ID, new SelectionMapper(), id);
	}
	
	@Override
	public List<Board> getBoardByAuthor(String author) {
		Object[] params = {"%"+author+"%"};
		return this.jdbcTemplate.query(SQLquery.SELECT_BOARD_BY_AUTHOR, new SelectionMapper(), params);
	}

	@Override
	public List<Board> getBoardByTitle(String title) {
		Object[] params = {"%"+title+"%"};
		return this.jdbcTemplate.query(SQLquery.SELECT_BOARD_BY_TITLE, new SelectionMapper(), params);
	}

	@Override
	public List<Board> getBoardByDate(String date) {
		return this.jdbcTemplate.query(SQLquery.SELECT_BOARD_BY_DATE, new SelectionMapper(), date);
	}

	@Override
	public List<Board> getBoard() {
		return this.jdbcTemplate.query(SQLquery.SELECT_ALL, new SelectionMapper());
	}
	
	@Override
	public int insertBoard(Board board) {
		EncDec enc = new EncDec();
		String encryptedText = enc.encryptText(board.getPassword());
	
		return this.jdbcTemplate.update(SQLquery.INSERT_BOARD, board.getAuthor(), board.getTitle(),
				board.getContent(), encryptedText);
	}
	
	@Override
	public int deleteBoard(int id) {
		return this.jdbcTemplate.update(SQLquery.DELETE_DATA, id);
	}
	
	@Override
	public int updateBoard(int id, Board board) {
		return this.jdbcTemplate.update(SQLquery.UPDATE_DATA, board.getAuthor(), board.getTitle(),
				board.getContent(), id);
	}
}
