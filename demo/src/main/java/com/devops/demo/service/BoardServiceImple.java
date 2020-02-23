package com.devops.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devops.demo.model.Board;
import com.devops.demo.repository.BoardRepository;

@Service
public class BoardServiceImple implements BoardService {

	@Autowired
	private BoardRepository dao;

	@Override
	public Board showOneData(int id) {
		Board board;
		try {
			board = dao.getBoardById(id);
		} catch (Exception e) {
			board = new Board();
		}
		return board;
	}

	@Override
	public List<Board> showListByAuthor(String author) {
		List<Board> board;
		try {
			board = dao.getBoardByAuthor(author);
		} catch (Exception e) {
			board = new ArrayList<Board>();
		}
		return board;
	}

	@Override
	public List<Board> showListByTitle(String title) {
		List<Board> board;
		try {
			board = dao.getBoardByTitle(title);
		} catch (Exception e) {
			board = new ArrayList<Board>();
		}
		return board;
	}

	@Override
	public List<Board> showListByDate(String date) {
		List<Board> board;
		try {
			board = dao.getBoardByDate(date);
		} catch (Exception e) {
			board = new ArrayList<Board>();
		}
		return board;
	}
	
	@Override
	public List<Board> showList() {
		List<Board> board;
		try {
			board = dao.getBoard();
		} catch (Exception e) {
			board = new ArrayList<Board>();
		}
		return board;
	}

	@Override
	public int register(Board board) {
		int result = dao.insertBoard(board);
		if(result == 1)
			return 1;
		else
			return 0;
	}
	
	@Override
	public int updateBoard(int id, Board board) {
		int result = dao.updateBoard(id,board);
		if(result == 1)
			return 1;
		else
			return 0;
	}
	
	@Override
	public int deleteBoard(int id) {
		int i;
		try {
			 i = dao.deleteBoard(id);
		} catch(Exception e) {
			i = 0;
		}
		return i;
	}

}
