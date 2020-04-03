package com.capgemini.librarymanagementsystem_jdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssue;
import com.capgemini.librarymanagementsystem_jdbc.dto.BooksBorrowed;
import com.capgemini.librarymanagementsystem_jdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.User;
import com.capgemini.librarymanagementsystem_jdbc.exception.LMSException;
import com.mysql.jdbc.Statement;

public class UserDaoImplements implements UserDao{

	@Override
	public boolean registerUser(User user) {

		try 

			(FileInputStream info=new FileInputStream("db.properties");){
			Properties pro=new Properties();
			pro.load(info);

			//Class.forName(pro.getProperty("path"));
			Class.forName("com.mysql.jdbc.Driver");
			//String dburl = pro.getProperty("dburl");
			String dburl = "jdbc:mysql://localhost:3306/library_db";
			try(Connection conn = DriverManager.getConnection(dburl, "root", "root");){

			String query = "insert into user values(?,?,?,?,?,?,?)";
			//String query=pro.getProperty("insertQuery");
			try(PreparedStatement pstmt=conn.prepareStatement(query);){
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPassword());
			pstmt.setLong(6, user.getMobileNo());
			pstmt.setString(7, user.getRole());

			int count = pstmt.executeUpdate();
			//if(user.getEmail().isEmpty()) {
			if(user.getEmail().isEmpty() && count == 0) {
				return false;
			}else {

				return true;
			}
			
			}
		}
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}


	}



	@Override
	public User authUser(String email, String password) {

		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("select * from user where email=? and password=?");) {
				Class.forName(pro.getProperty("path"));
				pstmt.setString(1,email);
				pstmt.setString(2,password);
				ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					User bean = new User();
					bean.setId(rs.getInt("id"));
					bean.setFirstName(rs.getString("firstName"));
					bean.setLastName(rs.getString("lastName"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("password"));
					bean.setMobileNo(rs.getLong("mobileNo"));
					bean.setRole(rs.getString("role"));
					return bean;
				} else {
					return null;
				}
			}
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean request(int bookId, int id) {


		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("insert into request_details values(?,(select concat(firstname,'_',lastname) from users where id=?)"
							+ ",?,(select bookname from bookdetails where bookId=?))");) {
				Class.forName(pro.getProperty("path"));
				pstmt.setInt(1,id);
				pstmt.setInt(2,id);
				pstmt.setInt(3, bookId);
				pstmt.setInt(4, bookId);
				int count=pstmt.executeUpdate();
				if(count != 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
		
	@Override
	public BookDetails borrow(int bookId) {

		try 
			(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro=new Properties();
			pro.load(info);

			Class.forName(pro.getProperty("path"));
			String dburl = pro.getProperty("dburl");
			try(Connection conn = DriverManager.getConnection(dburl, pro);){
			String query = "select * from bookdetails where bookId = ?";
			try(PreparedStatement pstmt = conn.prepareStatement(query);){
			ResultSet rs=pstmt.executeQuery();
			pstmt.setInt(1,bookId);
			if(rs.next()) {
				BookDetails bean = new BookDetails();
				bean.setBookId(rs.getInt("bookId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthorName(rs.getString("authorName"));
				bean.setBookCategory(rs.getString("bookCategory"));
				bean.setPublisherName(rs.getString("publisherName"));
				bean.setCopies(rs.getInt("copies"));
				return bean;
			} else {
				return null;
			}
			}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}


	}

	@Override
	public boolean addBook(BookDetails bookDetail) {

		try 


			(FileInputStream info=new FileInputStream("db.properties");){
			Properties pro=new Properties();
			pro.load(info);

			Class.forName(pro.getProperty("path"));
			String dburl = pro.getProperty("dburl");
			try(Connection conn = DriverManager.getConnection(dburl, pro);){
			//String query = pro.getProperty("bookInsertQuery");
			String query = "insert into bookdetails values(?,?,?,?,?,?)";
			try(PreparedStatement pstmt = conn.prepareStatement(query);){
			pstmt.setInt(1, bookDetail.getBookId());
			pstmt.setString(2, bookDetail.getBookName());
			pstmt.setString(3, bookDetail.getAuthorName());
			pstmt.setString(4, bookDetail.getPublisherName());
			pstmt.setInt(5, bookDetail.getCopies());
			pstmt.setString(6, bookDetail.getBookCategory());

			int count = pstmt.executeUpdate();
			if(count!=0) {
				return true;
			} else {
				return false;
			}
			}
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}


	}

	@Override
	public boolean removeBook(int bookId) {


		try 


			(FileInputStream info=new FileInputStream("db.properties");){
			Properties pro=new Properties();
			pro.load(info);


			Class.forName(pro.getProperty("path"));
			String dburl = pro.getProperty("dburl");
			try(Connection conn = DriverManager.getConnection(dburl, pro);){
			String query = "delete from bookdetails where bookId = ?";
			try(PreparedStatement pstmt = conn.prepareStatement(query);){
			pstmt.setInt(1, bookId);
			int count= pstmt.executeUpdate();
			if(count != 0) {
				return true;
			}else {
				return false;
			}
		}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateBook(BookDetails book) {

		try 

			(FileInputStream info=new FileInputStream("db.properties");){
			Properties pro=new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			String dburl = pro.getProperty("dburl");
			try(Connection conn = DriverManager.getConnection(dburl,pro);){
			String query = "update bookdetails set bookName=? where bookId=?";
			try(PreparedStatement pstmt = conn.prepareStatement(query);){
			pstmt.setString(1,book.getBookName());
			pstmt.setInt(2, book.getBookId());
			int count = pstmt.executeUpdate();
			if(count != 0) {
				return true;
			}else {
				return false;
			}
			}
			}
		} catch (Exception e) {

			System.err.println(e.getMessage());
			return false;
		}

	}



	@Override
	public LinkedList<BookDetails> searchBookByTitle(String bookName) {

		try 
			(FileInputStream info=new FileInputStream("db.properties");){
			Properties pro=new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			String dburl = pro.getProperty("dburl");
			try(Connection conn = DriverManager.getConnection(dburl,pro);){

			String query = "select * from bookDetails where bookName = ?";
			try(PreparedStatement pstmt = conn.prepareStatement(query);){
			pstmt.setString(1, bookName);
			try(ResultSet rs = pstmt.executeQuery();){
			LinkedList<BookDetails> beans = new LinkedList<BookDetails>();
			while(rs.next()) {
				BookDetails bean = new BookDetails();
				bean.setBookId(rs.getInt("bookId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthorName(rs.getString("authorName"));
				bean.setPublisherName(rs.getString("publisherName"));
				bean.setCopies(rs.getInt("copies"));
				bean.setBookCategory(rs.getString("bookCategory"));
				beans.add(bean);
			}
			return beans;
		}
			}
	}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}



	@Override
	public LinkedList<BookDetails> searchBookByAuthor(String authorName) {

		try 
		(FileInputStream info=new FileInputStream("db.properties");){
			Properties pro=new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			String dburl = pro.getProperty("dburl");
			try(Connection conn = DriverManager.getConnection(dburl,pro);){
			String query = "select * from bookDetails where authorName = ?";
			try(PreparedStatement pstmt = conn.prepareStatement(query);){
			pstmt.setString(1, authorName);
			try(ResultSet rs = pstmt.executeQuery();){
			LinkedList<BookDetails> beans = new LinkedList<BookDetails>();
			while(rs.next()) {
				BookDetails bean = new BookDetails();
				bean.setBookId(rs.getInt("bookId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthorName(rs.getString("authorName"));
				bean.setPublisherName(rs.getString("publisherName"));
				bean.setCopies(rs.getInt("copies"));
				bean.setBookCategory(rs.getString("bookCategory"));
				beans.add(bean);
			}
			return beans;

			}
		}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

	}



	@Override
	public LinkedList<BookDetails> getBooksInfo() {


		try 
		(FileInputStream info=new FileInputStream("db.properties");){
			Properties pro=new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			String dburl = pro.getProperty("dburl");
			try (Connection conn = DriverManager.getConnection(dburl,pro);){

				String query = "select * from bookdetails";
				PreparedStatement pstmt = conn.prepareStatement(query);
				try(ResultSet rs = pstmt.executeQuery();){
				LinkedList<BookDetails> beans = new LinkedList<BookDetails>();


				while (rs.next()) {
					BookDetails book = new BookDetails();
					book.setBookId(rs.getInt("bookId"));
					book.setBookName(rs.getString("bookName"));
					book.setBookCategory(rs.getString("bookCategory"));
					book.setAuthorName(rs.getString("authorName"));
					book.setPublisherName(rs.getString("publisherName"));
					book.setCopies(rs.getInt("copies"));

					beans.add(book);
				}
				return beans;

			}
		}
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}


	}


	
	@Override
	public boolean returnBook(int bookId, int id, String status) {

		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pst = conn.prepareStatement("select * from bookdetails where bid=?");) {
				pst.setInt(1, bookId);
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					try(PreparedStatement pstmt = conn.prepareStatement("select * from book_issue where bid=? and uid=?");){
						pstmt.setInt(1, bookId);
						pstmt.setInt(2, id);
						rs = pstmt.executeQuery();
						if(rs.next()) {
							Date issueDate = rs.getDate("issueDate");
							Date returnDate = rs.getDate("returnDate");
							long difference = issueDate.getTime() - returnDate.getTime();
							float daysBetween = (difference / (1000*60*60*24));
							if(daysBetween>7) {
								float fine = daysBetween*5;
								System.out.println("The user has to pay the fine of the respective book of Rs:"+fine);
								if(status=="yes") {
									try(PreparedStatement pstmt1 = conn.prepareStatement("delete from book_issue where bookid=? and id=?");) {
										pstmt1.setInt(1,bookId);
										pstmt1.setInt(2,id);
										int count =  pstmt1.executeUpdate();
										if(count != 0) {
											try(PreparedStatement pstmt2 = conn.prepareStatement("delete from borrowed_books where bookid=? and id=?");) {
												pstmt2.setInt(1, bookId);
												pstmt2.setInt(2, id);
												int isReturned = pstmt2.executeUpdate();
												if(isReturned != 0 ) {
													try(PreparedStatement pstmt3 = conn.prepareStatement("delete from request_details where bookid=? and id=?");){
														pstmt3.setInt(1, bookId);
														pstmt3.setInt(2, id);
														int isRequestDeleted = pstmt3.executeUpdate();
														if(isRequestDeleted != 0) {
															return true;
														}else {
															return false;
														}
													}
												}else {
													return false;
												}
											}
										} else {
											return false;
										}
									}
								} else {
									throw new LMSException("The User has to pay fine for delaying book return");
								}
							}else {
								try(PreparedStatement pstmt1 = conn.prepareStatement("delete from book_issue where bookid=? and id=?");) {
									pstmt1.setInt(1,bookId);
									pstmt1.setInt(2,id);
									int count =  pstmt1.executeUpdate();
									if(count != 0) {
										try(PreparedStatement pstmt2 = conn.prepareStatement("delete from borrowed_books where bookid=? and id=?");) {
											pstmt2.setInt(1, bookId);
											pstmt2.setInt(2, id);
											int isReturned = pstmt2.executeUpdate();
											if(isReturned != 0 ) {
												try(PreparedStatement pstmt3 = conn.prepareStatement("delete from request_details where bookid=? and id=?");){
													pstmt3.setInt(1, bookId);
													pstmt3.setInt(2, id);
													int isRequestDeleted = pstmt3.executeUpdate();
													if(isRequestDeleted != 0) {
														return true;
													}else {
														return false;
													}
												}
											}else {
												return false;
											}
										}
									} else {
										return false;
									}
								}
							}
						}else {
							throw new LMSException("This respective user hasn't borrowed any book");
						}
					}

				}else {
					throw new LMSException("No book exist with bookId"+bookId);
				}

			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public List<BookDetails> searchBookBycategory(String bookCategory) {


		try 
		(FileInputStream info=new FileInputStream("db.properties");){
			Properties pro=new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			String dburl = pro.getProperty("dburl");
			try(Connection conn = DriverManager.getConnection(dburl,pro);){
			String query = "select * from bookDetails where bookcategory = ?";
			try(PreparedStatement pstmt = conn.prepareStatement(query);){
			pstmt.setString(1, bookCategory);
			try(ResultSet rs = pstmt.executeQuery();){
			LinkedList<BookDetails> beans = new LinkedList<BookDetails>();
			while(rs.next()) {
				BookDetails bean = new BookDetails();
				bean.setBookId(rs.getInt("bookId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthorName(rs.getString("authorName"));
				bean.setPublisherName(rs.getString("publisherName"));
				bean.setCopies(rs.getInt("copies"));
				bean.setBookCategory(rs.getString("bookCategory"));
				beans.add(bean);
			}
			return beans;

			}
		}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		

	}





	@Override
	public boolean bookIssue(int bookId, int id) {


		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pst = conn.prepareStatement("select * from bookdetails where bid=?");){
				pst.setInt(1, bookId);
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					try(PreparedStatement pstmt = conn.prepareStatement("select * from request_details where uid=? and bid=? and email=(select email from users where uid=?)")) {
						pstmt.setInt(1, id);
						pstmt.setInt(2, bookId);
						pstmt.setInt(3, id);
						rs = pstmt.executeQuery();
						if(rs.next()) {
							try(PreparedStatement pstmt1 = conn.prepareStatement("insert into book_issue values(?,?,?,?)");){
								pstmt1.setInt(1, bookId);
								pstmt1.setInt(2, id);
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
								Calendar cal = Calendar.getInstance();
								String issueDate = sdf.format(cal.getTime());
								pstmt1.setDate(3, java.sql.Date.valueOf(issueDate));
								cal.add(Calendar.DAY_OF_MONTH, 7);
								String returnDate = sdf.format(cal.getTime());
								pstmt.setDate(4, java.sql.Date.valueOf(returnDate));
								int count=pstmt1.executeUpdate();
								if(count != 0) {	
									try(PreparedStatement pstmt2 = conn.prepareStatement("Insert into borrowed_books values(?,?,(select * from borrowed_books where id=?))")){
										pstmt2.setInt(1, id);
										pstmt2.setInt(2, bookId);
										pstmt2.setInt(3, id);
										int isBorrowed = pstmt2.executeUpdate();
										if(isBorrowed != 0) {
											return true;
										}else {
											return false;
										}
									}
								} else {
									throw new LMSException("Book Not issued");
								}					
							}
						} else {
							throw new LMSException("The respective user have not placed any request");
						}			
					}
				}else{
					throw new LMSException("There is no book exist with bookId"+bookId);
				}
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}



	@Override
	public LinkedList<BookIssue> bookHistoryDetails(int uId) {
	
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("select count(*) as id from book_issue where id=?");) {
				Class.forName(pro.getProperty("path"));
				pstmt.setInt(1, uId);
				 ResultSet rs=pstmt.executeQuery();
				LinkedList<BookIssue> beans = new LinkedList<BookIssue>();
				while(rs.next()) {
					BookIssue issueDetails = new BookIssue();
					issueDetails.setId(rs.getInt("id"));
					beans.add(issueDetails);
				} 
				return beans;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}



	@Override
	public List<BooksBorrowed> borrowedBook(int id) {
		
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pstmt = conn.prepareStatement("select * from borrowed_books where uid=?");) {
				pstmt.setInt(1, id);
				 ResultSet rs=pstmt.executeQuery();
				LinkedList<BooksBorrowed> beans = new LinkedList<BooksBorrowed>();
				while(rs.next()) {
					BooksBorrowed listOfbooksBorrowed = new BooksBorrowed();
					listOfbooksBorrowed.setId(rs.getInt("id"));
					listOfbooksBorrowed.setBookId(rs.getInt("bookId"));
					listOfbooksBorrowed.setEmail(rs.getString("email"));
					beans.add(listOfbooksBorrowed);
				} 
				return beans;
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
		
	}



	@Override
	public LinkedList<RequestDetails> showRequests() {
		
		
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					Statement stmt = (Statement)conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from request_details");) {
				LinkedList<RequestDetails> beans = new LinkedList<RequestDetails>();
				while(rs.next()) {
					RequestDetails bean = new RequestDetails();
					bean.setId(rs.getInt("id"));
					bean.setFullName(rs.getString("fullName"));
					bean.setBookId(rs.getInt("bookId"));
					bean.setBookName(rs.getString("bookName"));
					beans.add(bean);
				}
				return beans;
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}



	@Override
	public LinkedList<BookIssue> showIssuedBooks() {
		
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					Statement stmt = (Statement)conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from book_issue");) {
				LinkedList<BookIssue> beans = new LinkedList<BookIssue>();
				while(rs.next()) {
					BookIssue bean = new BookIssue();
					bean.setBookId(rs.getInt("bookId"));
					bean.setId(rs.getInt("id"));
					bean.setIssueDate(rs.getDate("issueDate"));
					bean.setReturnDate(rs.getDate("returnDate"));
					beans.add(bean);
				}
				return beans;
			}
		} catch(Exception e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}
		
	}



	@Override
	public LinkedList<User> showUsers() {
		
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					Statement stmt = (Statement)conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from user");) {
				LinkedList<User> beans = new LinkedList<User>();
				while(rs.next()) {
					User bean = new User();
					bean.setId(rs.getInt("id"));
					bean.setFirstName(rs.getString("firstName"));
					bean.setLastName(rs.getString("lastName"));
					bean.setEmail(rs.getString("email"));
					bean.setPassword(rs.getString("password"));
					bean.setMobileNo(rs.getLong("mobileNo"));
					bean.setRole(rs.getString("role"));
					beans.add(bean);
				}
				return beans;
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
		
	}



	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {
	
		
		try(FileInputStream info = new FileInputStream("db.properties");){
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			try(Connection conn = DriverManager.getConnection(pro.getProperty("dburl"),pro);
					PreparedStatement pst = conn.prepareStatement("select * from user where email=? and role=?")){
				pst.setString(1, email);
				pst.setString(2, role);
				 ResultSet  rs=pst.executeQuery();
				if(rs.next()) {
					try(PreparedStatement pstmt = conn.prepareStatement("update user set password=? where email=? and password=?");) {
						pstmt.setString(1, newPassword);
						pstmt.setString(2, email);
						pstmt.setString(3,password);
						int count=pstmt.executeUpdate();
						if(count!=0) {
							return true;
						} else {
							return false;
						}
					}
				}else {
					throw new LMSException("User doesnt exist");
				}
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}
	}
		
		

