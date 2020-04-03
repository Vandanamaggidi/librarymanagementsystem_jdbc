package com.capgemini.librarymanagementsystem_jdbc.dto;

import java.io.Serializable;

public class BooksBorrowed implements Serializable{

	private int id;
	private int bookId;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
