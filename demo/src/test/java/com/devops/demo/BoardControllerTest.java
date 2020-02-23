package com.devops.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devops.demo.model.Board;
import com.devops.demo.service.BoardService;
import com.devops.demo.web.BoardController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class BoardControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Mock
	private BoardService boardService; // test 목적으로 생성한 일종의 가짜 service 객체

	@InjectMocks
	private BoardController boardController;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
	}

	@Test
	public void getDetailTest_Success() throws Exception {
		// 게시글 하나에 대한 상세정보 Read test (결과 성공을 예상)
		Board board = new Board(2, "lee", "title", "content", new Date(), "pw1234");
		when(boardService.showOneData(board.getId())).thenReturn(board);

		mockMvc.perform(get("/v0.0.1/lists/{id}", board.getId()).characterEncoding("utf-8"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(board.getId()))).andExpect(jsonPath("$.author", is(board.getAuthor())))
				.andDo(print());
	}

	@Test
	public void getDetailTest_Fail() throws Exception {
		// 게시글 하나에 대한 상세정보 Read test (결과 실패를 예상)
		Board board = new Board(5, "kim", "title5", "content5", new Date(), "pw12355");
		when(boardService.showOneData(board.getId())).thenReturn(board);

		mockMvc.perform(get("/v0.0.1/lists/{id}", board.getId()).characterEncoding("utf-8"))
				.andExpect(jsonPath("$.author", is("lee"))).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void getSearchAuthorTest_Success() throws Exception {
		// author를 검색한 게시글 Read test (결과 성공을 예상)

		List<Board> list = Arrays.asList(new Board(1, "kim", "title1", "content1", new Date(), "pw1234"),
				new Board(4, "kim", "title4", "content4", new Date(), "pw1238"));

		when(boardService.showListByAuthor("kim")).thenReturn(list);

		mockMvc.perform(get("/v0.0.1/lists/author/{author}", "kim").characterEncoding("utf-8"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[*].id").exists()).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void getEntireBoardTest_Success() throws Exception {
		// 게시글 전체 리스트 Read test (결과 성공을 예상)
		List<Board> list = Arrays.asList(new Board(1, "kim", "title1", "content1", new Date(), "pw1234"),
				new Board(2, "choi", "title2", "content2", new Date(), "pw1236"),
				new Board(3, "park", "title3", "content3", new Date(), "pw1237"),
				new Board(4, "oh", "title4", "content4", new Date(), "pw1238"));
		when(boardService.showList()).thenReturn(list);

		mockMvc.perform(get("/v0.0.1/lists").characterEncoding("utf-8")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].author", is("kim")))
				.andExpect(jsonPath("$[0].title", is("title1"))).andExpect(jsonPath("$[0].password", is("pw1234")))
				.andExpect(jsonPath("$[*].id").exists()).andDo(print());
	}

	@Test
	public void getEntireBoardTest_Fail() throws Exception {
		// 게시글 전체 리스트 Read test (결과 실패를 예상)
		List<Board> list = Arrays.asList(new Board(1, "kim", "title1", "content1", new Date(), "pw1234"),
				new Board(2, "choi", "title2", "content2", new Date(), "pw1236"),
				new Board(3, "park", "title3", "content3", new Date(), "pw1237"),
				new Board(4, "oh", "title4", "content4", new Date(), "pw1238"));
		when(boardService.showList()).thenReturn(list);

		mockMvc.perform(get("/v0.0.1/lists").characterEncoding("utf-8")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[1].author", is("kim")))
				.andExpect(jsonPath("$[2].title", is("title1"))).andExpect(jsonPath("$[3].password", is("pw1234")))
				.andDo(print());
	}

	@Test
	public void createDataTest_Success() throws Exception {
		// 새 게시글 객체 한개를 test용으로 생성 후 post방식으로 요청 (결과 성공을 예상)
		when(boardService.register(any(Board.class))).thenReturn(1);

		mockMvc.perform(post("/v0.0.1/lists").contentType(MediaType.APPLICATION_JSON).content(
				"{\"author\":\"namae\",\r\n\"title\":\"1234\",\r\n\"content\":\"content2\",\r\n\"password\":\"psdf0\"\r\n}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.title", is("1234"))).andDo(print());
	}

	@Test
	public void deleteDataTest_Success() throws Exception {
		// 해당 id의 글을 삭제 요청 (결과 성공을 예상)
		when(boardService.deleteBoard(16)).thenReturn(1);
		logger.info("delete - i : {}", boardService.deleteBoard(16));
		mockMvc.perform(delete("/v0.0.1/lists/{id}", 16)).andExpect(status().isOk());
	}

	@Test
	public void updateDataTest_Success() throws Exception {
		when(boardService.updateBoard(16, new Board("author16", "title16", "content16"))).thenReturn(1);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/v0.0.1/lists/{id}", 16).contentType(MediaType.APPLICATION_JSON)
				.content("{	\r\n" + 
						"	\"author\": \"na33222\",\r\n" + 
						"	\"title\": \"satua\",\r\n" + 
						"	\"content\": \"hoya\"\r\n" + 
						"}")
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());

	}
}
