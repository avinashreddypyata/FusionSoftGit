package com.fusion.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fusion.model.User;
import com.fusion.service.UserNotFoundService;
import com.fusion.service.UserService;
import com.fusion.service.UserServiceImpl;

/**
 * Handles requests for the login purpose.
 */

/*
 * @Configuration
 * 
 * @ComponentScan
 * 
 * @RestController
 * 
 * @EnableWebMvc
 */
/*This is for Controller*/
@Controller
public class FusionMainController {

	@Resource
	private UserServiceImpl userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	private static final Logger logger = LoggerFactory.getLogger(FusionMainController.class);

	

	@RequestMapping(value = "/fus/validateLogin/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> getCheckLogin(@RequestBody User request, @PathVariable("id") int id) {

		logger.info("Start getCheckLogin");

		System.out.println(request.getName());
		String username = request.getName();
		String password = request.getPassword();

		User user = null;

		try {
			user = userService.findByPassword(username, password);
			if (user.getName().equals(username) && user.getPassword().equals(password)) {

				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		} catch (UserNotFoundService e) {

			UserError errorUser = new UserError();
			errorUser.setErrorCode("401");
			errorUser.setErrorMessage(e.getMessage());
			return new ResponseEntity<UserError>(errorUser, HttpStatus.NOT_FOUND);
		}
		return null;

	}
}
