package com.fusion.service;
import org.springframework.stereotype.Component;
import com.fusion.dao.UserDao;
import com.fusion.dao.UserDaoImpl;
import com.fusion.dao.UserNotFound;
import com.fusion.model.User;
import com.fusion.model.UserVo;

@Component
public class UserServiceImpl implements UserService {

	User usr = new User();

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	private UserDaoImpl userDao;

	@Override
	public User findByPassword(String username, String password) throws UserNotFoundService {
		try {
			UserVo pwd = userDao.findByPassword(username, password);
			usr.setPassword(pwd.getPassword());
			usr.setName(pwd.getName());
			usr.setId(pwd.getId());
		} catch (UserNotFound e) {
			throw new UserNotFoundService("User not available");
		}

		return usr;
	}

}
