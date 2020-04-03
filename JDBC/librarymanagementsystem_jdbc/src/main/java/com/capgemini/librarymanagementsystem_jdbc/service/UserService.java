package com.capgemini.librarymanagementsystem_jdbc.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssue;
import com.capgemini.librarymanagementsystem_jdbc.dto.BooksBorrowed;
import com.capgemini.librarymanagementsystem_jdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.User;

public interface UserService {

	boolean registerUser(User user);
	User authUser(String email, String password);
	
	boolean request(int bookId, int id);
	BookDetails borrow(int bookId);
	
	
	boolean addBook(BookDetails bookDetail);
	boolean removeBook(int bookId);
	boolean updateBook(BookDetails book);
	boolean bookIssue(int bookId, int id);
	
	
	LinkedList<BookDetails> searchBookByTitle(String bookName);
	LinkedList<BookDetails> searchBookByAuthor(String authorName);
	List<BookDetails> searchBookBycategory (String bookCategory);
	LinkedList<BookDetails> getBooksInfo();
	
	boolean returnBook(int bookId, int id, String status);
	LinkedList<BookIssue> bookHistoryDetails(int uId);
	
	List<BooksBorrowed> borrowedBook(int id);
	LinkedList<RequestDetails> showRequests();
	LinkedList<BookIssue> showIssuedBooks();
	LinkedList<User> showUsers();
	boolean updatePassword(String email,String password,String newPassword,String role);
}
