package com.devops.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.devops.demo.model.Board;
import com.devops.demo.repository.BoardRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

	private static final Logger logger = LoggerFactory.getLogger(BoardRepository.class);

	@Autowired
	private BoardRepository dao;

	@Test
	public void getBoardListTest() throws Exception {
		for(int i = 0; i < dao.getBoard().size(); i++ ) {
			Board b = dao.getBoard().get(i);
			logger.info("{ id : {}, author : {}, title : {}, content : {}, date : {}, password : {} }", b.getId(), b.getAuthor(), b.getTitle(), b.getContent(), b.getDate(), b.getPassword());
		}
	}
	
	@Test
	public void insertBoardTest() throws Exception {
		Board newb = new Board("author66", "title111", "content1111", "pw56789");
		int i = dao.insertBoard(newb);
		logger.info("insert - i = {}", i);
	}

	@Test
	public void updateBoardTest() throws Exception {
		Board b = new Board("a", "t", "c");
		int i = dao.updateBoard(5, b);
		logger.info("update - i = {}", i);
	}
	
	@Test
	public void deleteBoardTest() throws Exception {
		int first = dao.getBoard().get(0).getId();
		logger.info("first : {}", first);
		int j = dao.deleteBoard(first);
		logger.info("delete - j : {}", j);
	}
}
