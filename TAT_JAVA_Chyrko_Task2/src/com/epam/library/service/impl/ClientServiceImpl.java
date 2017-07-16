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
	User user = User.getInstance();

	@Override
	public void signIn(String login, String password)  throws ServiceException{
		
		if(login == null || login.isEmpty() || password == null || password.isEmpty()){
			logger.warn("Incorrect login or password");
			throw new ServiceException("Incorrect login or password");
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
			logger.info(user.toString());
			if(user.getLogin()==null && user.getLogin().isEmpty()){
				logger.warn("There is no user in the system!");
				throw new ServiceException("There is no user in the system!");
			}else if(!user.getLogin().equals(login)){
				logger.warn("Only the user can do SingOut!");
				throw new ServiceException("Only the user can do SingOut!");
			}
			if(!"IN".equals(user.getSignIn())){
				logger.warn("You must be SignIn!");
				throw new ServiceException("You must be SignIn!");
			}
		
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoObjectFactory.getUserDAO();
			userDAO.signOut(login);
		} catch (DAOException e) {
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}	catch (NullPointerException npe) {
			logger.warn("No one user in system!");
			throw new ServiceException("No one user in system!");
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
