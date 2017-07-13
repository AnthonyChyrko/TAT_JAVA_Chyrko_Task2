package com.epam.library.controller.command.impl;

import org.apache.log4j.Logger;

import com.epam.library.bean.User;
import com.epam.library.controller.command.Command;
import com.epam.library.controller.utils.UserParam;
import com.epam.library.controller.utils.UtilController;
import com.epam.library.service.ClientService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class Register implements Command {
	private final static Logger logger = Logger.getLogger(Register.class);
	User user;
	UtilController uc = new UtilController();
	@Override
	public String execute(String request) {
		
		String[] param = request.split("&");		
		String login = uc.recognizeParam(UserParam.LOGIN, param);
		String password = uc.recognizeParam(UserParam.PASSWORD, param);
		String response = null;
		user = new User(login,password);
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ClientService clientService = serviceFactory.getClientService();
		try {
			clientService.registration(login, password);//тут вернулся текущий юзер
			response = "New USER added!";
			logger.info(response);
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
				
		return response;
	}

}
