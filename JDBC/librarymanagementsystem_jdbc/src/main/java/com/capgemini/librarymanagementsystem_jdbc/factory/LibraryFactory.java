package com.capgemini.librarymanagementsystem_jdbc.factory;

import com.capgemini.librarymanagementsystem_jdbc.dao.UserDao;
import com.capgemini.librarymanagementsystem_jdbc.dao.UserDaoImplements;
import com.capgemini.librarymanagementsystem_jdbc.service.UserService;
import com.capgemini.librarymanagementsystem_jdbc.service.UserServiceImplements;

public class LibraryFactory {

	
	
	
	public static UserDao getUserDao() {
		return new UserDaoImplements();
	}

	public static UserService getUserService() {
		return new UserServiceImplements();
	}
}

