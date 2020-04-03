package com.capgemini.librarymanagementsystem_jdbc.service;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dao.UserDao;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssue;
import com.capgemini.librarymanagementsystem_jdbc.dto.BooksBorrowed;
import com.capgemini.librarymanagementsystem_jdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.User;
import com.capgemini.librarymanagementsystem_jdbc.factory.LibraryFactory;

public class UserServiceImplements implements UserService{

	private UserDao dao = LibraryFactory.getUserDao();

	@Override
	public boolean registerUser(User user) {
		// TODO Auto-generated method stub
		return dao.registerUser(user);
	}

	@Override
	public User authUser(String email, String password) {
		// TODO Auto-generated method stub
		return dao.authUser(email, password);
	}

	@Override
	public boolean request( int bookId, int id) {
		// TODO Auto-generated method stub
		return dao.request(bookId, id);
	}

	@Override
	public BookDetails borrow(int bookId) {
		// TODO Auto-generated method stub
		return dao.borrow(bookId);
	}

	/*@Override
	 *
	 * public BookDetails searchBook(int bookId) { // TODO Auto-generated method
	 * stub return dao.searchBook(bookId); }
	 */

	@Override
	public boolean addBook(BookDetails bookDetail) {
		// TODO Auto-generated method stub
		return dao.addBook(bookDetail);
	}

	@Override
	public boolean removeBook(int bookId) {
		// TODO Auto-generated method stub
		return dao.removeBook(bookId);
	}

	@Override
	public boolean updateBook(BookDetails book) {
		// TODO Auto-generated method stub
		return dao.updateBook(book);
	}

	@Override
	public boolean bookIssue(int bookId, int id) {
		// TODO Auto-generated method stub
		return dao.bookIssue(bookId, id);
	}


	@Override
	public LinkedList<BookDetails> searchBookByTitle(String bookName) {
		// TODO Auto-generated method stub
		return dao.searchBookByTitle(bookName);
	}

	@Override
	public LinkedList<BookDetails> searchBookByAuthor(String authorName) {
		// TODO Auto-generated method stub
		return dao.searchBookByAuthor(authorName);
	}

	@Override
	public LinkedList<BookDetails> getBooksInfo() {
		// TODO Auto-generated method stub
		return dao.getBooksInfo();
	}

	@Override
	public boolean returnBook(int bookId, int id,String status) {
		// TODO Auto-generated method stub
		return dao.returnBook(bookId, id, status);
	}

	@Override
	public List<BookDetails> searchBookBycategory(String bookCategory) {
		// TODO Auto-generated method stub
		return dao.searchBookBycategory(bookCategory);
	}

	@Override
	public LinkedList<BookIssue> bookHistoryDetails(int uId) {
		// TODO Auto-generated method stub
		return dao.bookHistoryDetails(uId);
	}

	@Override
	public List<BooksBorrowed> borrowedBook(int id) {
		// TODO Auto-generated method stub
		return dao.borrowedBook(id);
	}

	@Override
	public LinkedList<RequestDetails> showRequests() {
		// TODO Auto-generated method stub
		return dao.showRequests();
	}

	@Override
	public LinkedList<BookIssue> showIssuedBooks() {
		// TODO Auto-generated method stub
		return dao.showIssuedBooks();
	}

	@Override
	public LinkedList<User> showUsers() {
		// TODO Auto-generated method stub
		return dao.showUsers();
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
		// TODO Auto-generated method stub
		return dao.updatePassword(email, password, newPassword, role);
	}

}
