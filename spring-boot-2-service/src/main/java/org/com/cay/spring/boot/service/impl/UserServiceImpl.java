package org.com.cay.spring.boot.service.impl;

import org.com.cay.spring.boot.entity.User;
import org.com.cay.spring.boot.repository.IUserRepository;
import org.com.cay.spring.boot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Cay on 2018/5/18.
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public boolean save(User user) {
		return userRepository.save(user);
	}
}
