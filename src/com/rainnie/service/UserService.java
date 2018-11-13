package com.rainnie.service;

import com.rainnie.po.User;

public interface UserService {
	public User findUser(String usercode, String password);
}
