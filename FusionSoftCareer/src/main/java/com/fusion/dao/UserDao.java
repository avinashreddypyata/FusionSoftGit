package com.fusion.dao;

import com.fusion.model.UserVo;

public interface UserDao {

	public UserVo findByPassword(String username, String password) throws UserNotFound;

}
