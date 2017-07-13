package com.epam.library.service.impl;

import org.apache.log4j.Logger;

import com.epam.library.bean.User;
import com.epam.library.dao.UserDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.service.ClientService;
import com.epam.library.service.exception.ServiceException;

public class ClientServiceImpl implements ClientService {
	private final static Logger logger = Logger.getLogger(ClientServiceImpl.class);
	User user;

	@Override
	public void signIn(String login, String password)  throws ServiceException{
		
		if(login == null || login.isEmpty()){
			logger.warn("Incorrect login");
			throw new ServiceException("Incorrect login");
		}		
		
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.signIn(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}		

	}

	@Override
	public void signOut(String login) throws ServiceException{	
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.signOut(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}	
	}

	@Override
	public void registration(String login, String password) throws ServiceException {
		if(login == null || password== null || login.isEmpty() || password.isEmpty()){	
			logger.warn("Wrong login or password");
			throw new ServiceException("Wrong login or password");
		}				
		
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.registration(login, password);			
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}		

	}	

	@Override
	public void editLogin(String login) throws ServiceException {
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.editLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void editPassword(String password) throws ServiceException {
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.editPassword(password);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void editAccess(String targetLogin, String newAccess) throws ServiceException {
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.editAccess(targetLogin, newAccess);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void banUser(String targetlogin, String signIn) throws ServiceException {
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.banUser(targetlogin, signIn);
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

}
