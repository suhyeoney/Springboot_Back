package com.devops.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.devops.demo.model.Board;

public class SelectionMapper implements RowMapper<Board> {
	// 쿼리문의 결과 데이터를 ResultSet 객체에 넣기 위해 정의한 클래스 (RowMapper 인터페이스를 끌어옴)

	private static final Logger logger = LoggerFactory.getLogger(SelectionMapper.class);

	@Override
	public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
		Board b = new Board();

		b.setId(rs.getInt("id"));
		b.setAuthor(rs.getString("author"));
		b.setTitle(rs.getString("title"));
		b.setContent(rs.getString("content"));
		b.setDate(rs.getDate("date"));
		b.setPassword(rs.getString("password"));

		return b;
	}
}
