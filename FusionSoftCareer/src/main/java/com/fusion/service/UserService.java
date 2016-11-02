package com.fusion.service;

import com.fusion.dao.UserNotFound;
import com.fusion.model.User;

public interface UserService {

	public User findByPassword(String username, String password) throws UserNotFound, UserNotFoundService;

}
