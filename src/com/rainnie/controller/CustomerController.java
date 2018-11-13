package com.rainnie.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rainnie.po.BaseDict;
import com.rainnie.po.Customer;
import com.rainnie.po.User;
import com.rainnie.service.BaseDictService;
import com.rainnie.service.CustomerService;
import com.rainnie.utils.Page;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private BaseDictService baseDictService;

	/*
	 * 下面是加载并读取属性配置文件
	 */

	// 客户来源
	@Value("${customer.from.type}")
	private String FROM_TYPE;
	// 客户所属行业
	@Value("${customer.industry.type}")
	private String INDUSTRY_TYPE;
	// 客户级别
	@Value("${customer.level.type}")
	private String LEVEL_TYPE;

	@RequestMapping("customer/list.action")
	public String list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer rows,
			String custName, String custSource, String custIndustry, String custLevel, Model model) {
		Page<Customer> customers = customerService.findCustomerList(page, rows, custName, custSource, custIndustry,
				custLevel);

		model.addAttribute("page", customers);

		List<BaseDict> fromType = baseDictService.selectBaseDictByTypeCode(FROM_TYPE);
		List<BaseDict> industryType = baseDictService.selectBaseDictByTypeCode(INDUSTRY_TYPE);
		List<BaseDict> levelType = baseDictService.selectBaseDictByTypeCode(LEVEL_TYPE);

		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);
		model.addAttribute("custName", custName);
		model.addAttribute("custSource", custSource);
		model.addAttribute("custIndustry", custIndustry);
		model.addAttribute("custLevel", custLevel);

		return "customer";

	}

	/**
	 * 创建客户
	 */
	@RequestMapping("customer/create.action")
	@ResponseBody
	public String customerCreate(Customer customer, HttpSession session) {

		User user = (User) session.getAttribute("USER_SESSION");
		customer.setCust_create_id(user.getUser_id());

		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		customer.setCust_createtime(timeStamp);

		int rows = customerService.createCustomer(customer);
		if (rows > 0) {
			return "OK";
		} else {
			return "FAIL";
		}

	}

	/**
	 * 通过id获取客户
	 */
	@RequestMapping("customer/getCustomerById.action")
	@ResponseBody
	public Customer getCustomerById(Integer id) {
		Customer customer = customerService.getCustomerById(id);
		return customer;
	}

	/**
	 * 更新客户
	 */
	@RequestMapping("customer/update.action")
	@ResponseBody
	public String customerUpdate(Customer customer) {
		int rows = customerService.updateCustomer(customer);
		if (rows > 0) {
			return "OK";
		} else {
			return "FAIL";
		}
	}

	/**
	 * 删除客户
	 */
	@RequestMapping("customer/delete.action")
	@ResponseBody
	public String customerDelete(Integer id) {
		int rows = customerService.deleteCustomer(id);
		if (rows > 0) {
			return "OK";
		} else {
			return "FAIL";
		}
	}

}
