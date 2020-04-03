package com.capgemini.librarymanagementsystem_jdbc.dto;

import java.io.Serializable;
import java.util.Date;

public class BookDetails implements Serializable{

	private int bookId;
	private String bookName;
	private String authorName;
	private String publisherName;
	private int copies;
	private String bookCategory;
	
	private Date issueDate;
	private Date returnDate;
	
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
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	//private Date dateAdded;
	public int getBookId() {
		return bookId;
	}
	public void setId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public int getCopies() {
		return copies;
	}
	public void setCopies(int copies) {
		this.copies = copies;
	}
	/*
	 * public Date getDateAdded() { return dateAdded; } public void
	 * setDateAdded(Date dateAdded) { this.dateAdded = dateAdded; }
	 */
	
	
}
