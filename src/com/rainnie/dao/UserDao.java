package com.rainnie.dao;

import org.apache.ibatis.annotations.Param;

import com.rainnie.po.User;

public interface UserDao {

	public User findUser(@Param("usercode") String usercode, @Param("password") String password);
}
