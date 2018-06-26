package org.com.cay.spring.boot.controller;

import org.com.cay.spring.boot.entity.User;
import org.com.cay.spring.boot.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cay on 2018/5/9.
 */
@RestController
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@PostMapping("/user")
	public User save(@RequestParam String name) {
		LOGGER.info("保存用户...");
		User user = new User();
		user.setName(name);

		if (userService.save(user)) {
			LOGGER.info("保存成功...");
		}
		return user;

	}
}
