package com.capgemini.librarymanagementsystem_jdbc.dto;

import java.io.Serializable;
import java.util.Date;

public class BookIssue implements Serializable{

	
	private int id;
	private int bookId;
	private Date issueDate;
	private Date returnDate;
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
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	
	
	
}
