package com.fusion.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.fusion.model.UserVo;

@Repository
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unused")
	private Session openSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public UserVo findByPassword(String username, String password) throws UserNotFound {
		// TODO Auto-generated method stub

		List<UserVo> users = new ArrayList<UserVo>();

		users = getSessionFactory().openSession().createQuery("from UserVo where username=? and password=?")
				.setParameter(0, username).setParameter(1, password).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			throw new UserNotFound("user not found in the table");
		}

	}
}
