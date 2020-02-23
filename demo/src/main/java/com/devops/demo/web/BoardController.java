package com.devops.demo.web;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.devops.demo.model.Board;
import com.devops.demo.service.BoardService;

@RestController
@RequestMapping("/v0.0.1/lists")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	BoardService boardService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Board> getBoardById(@PathVariable("id") Integer id) {
		logger.info("getBoard() controller called");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		Board board = boardService.showOneData(id);
		if (board == null) {
			return new ResponseEntity<Board>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Board>(board, headers,HttpStatus.OK);
	}

	@RequestMapping(value = "/author/{author}", method = RequestMethod.GET)
	public ResponseEntity<List<Board>> getListByAuthor(@PathVariable("author") String author) {
		logger.info("getListByAuthor() controller called");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		List<Board> board = boardService.showListByAuthor(author);
		if (board == null) {
			return new ResponseEntity<List<Board>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Board>>(board, headers,HttpStatus.OK);
	}

	@RequestMapping(value = "/title/{title}", method = RequestMethod.GET)
	public ResponseEntity<List<Board>> getListByTitle(@PathVariable("title") String title) {
		logger.info("getListByTitle() controller called");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		List<Board> board = boardService.showListByTitle(title);
		if (board == null) {
			return new ResponseEntity<List<Board>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Board>>(board, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/date/{date}", method = RequestMethod.GET)
	public ResponseEntity<List<Board>> getListByDate(@PathVariable("date") String date) {
		logger.info("getListByDate() controller called");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		List<Board> board = boardService.showListByDate(date);
		if (board == null) {
			return new ResponseEntity<List<Board>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Board>>(board, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Board>> getList() {
		logger.info("getList() controller called");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
				List<Board> board = boardService.showList();
		if (board == null) {
			return new ResponseEntity<List<Board>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Board>>(board,headers ,HttpStatus.OK);
	}

	@CrossOrigin(origins="*")
	@RequestMapping(value = "", method = RequestMethod.POST,produces = "application/json")
	public ResponseEntity<Board> createPost(@RequestBody Board board) { // Get a Board Object through RequestBody annotation
		logger.info("createPost() controller called");
		HttpHeaders headers = new HttpHeaders();
		if (board == null) {
			return new ResponseEntity<Board>(HttpStatus.BAD_REQUEST);
		}
		int i = boardService.register(board);
		headers.add("A post Created - ", String.valueOf(board.getTitle()));
		headers.add("A post Created - " , String.valueOf(board.getTitle()));
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		if (i == 0) {
			return new ResponseEntity<Board>(HttpStatus.BAD_REQUEST); 
		}  
		return new ResponseEntity<Board>(board, headers, HttpStatus.CREATED); 
	}

	@CrossOrigin(origins="*")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Board> updateBoard(@PathVariable("id") Integer id, @RequestBody Board board) { 	
		// Get a Board Object through RequestBody annotation
		// ResponseEntity 타입을 Integer -> Board로 변경																						
		logger.info("udpateBoard() controller called");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		boardService.updateBoard(id, board);
		return new ResponseEntity<Board>(board, HttpStatus.CREATED);
	}

	@CrossOrigin(origins="*")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deleteBoard(@PathVariable("id") Integer id) {
		logger.info("deleteBoard() controller called");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		int i = boardService.deleteBoard(id);
		if (i == 0) {
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Integer>(i, headers,HttpStatus.OK);
	}
	@CrossOrigin(origins="*")
	@RequestMapping(value = "", method = RequestMethod.OPTIONS)
	public ResponseEntity<Integer> optionBoard() {
		logger.info("optionBoard() controller called");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		return new ResponseEntity<Integer>(headers,HttpStatus.OK);
	}
}