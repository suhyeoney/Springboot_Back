package com.devops.demo.model;

import java.util.Date;

public class Board {
	private int id;
	private String author;
	private String title;
	private String content;
	private Date date;
	private String password;
	
	public Board() {}
	
	public Board(int id) {
		this.id = id;
	}

	public Board(String author, String title, String content) {
		this.author = author;
		this.title = title;
		this.content = content;
	}
	
	public Board(int id, String author, String title, String content) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.content = content;
	}
	
	public Board(String author, String title, String content, String password) {
		this.author = author;
		this.title = title;
		this.content = content;
		this.password = password;
	}

	public Board(int id, String author, String title, String content, Date date, String password) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.content = content;
		this.date = date;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
