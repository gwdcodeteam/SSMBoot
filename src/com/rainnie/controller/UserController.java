package com.rainnie.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rainnie.po.User;
import com.rainnie.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("login.action")
	public String login(String usercode, String password, Model model, HttpSession session) {

		User user = (User) userService.findUser(usercode, password);
		if (user != null) {
			session.setAttribute("USER_SESSION", user);
			/* return "customer"; */
			return "redirect:customer/list.action";
		}
		model.addAttribute("msg", "账号或者密码错误");

		return "login";

	}

	@RequestMapping("logout.action")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:tologin.action";

	}

	@RequestMapping("tologin.action")
	public String toLogin() {
		return "login";

	}

	/**
	 * 此处是模拟没有拦截器登录的情况，没有实质作用
	 * 
	 * @return
	 */
	@RequestMapping("toCustomer.action")
	public String toCustomer() {
		return "customer";

	}
}
