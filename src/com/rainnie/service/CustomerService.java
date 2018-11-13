package com.rainnie.service;

import com.rainnie.po.Customer;
import com.rainnie.utils.Page;

public interface CustomerService {
	// 查询客户列表

	public Page<Customer> findCustomerList(Integer page, Integer rows, String custName, String custSource,
			String custIndustry, String custLevel);

	// 创建客户
	public int createCustomer(Customer customer);

	/**
	 * 更新用户，更新用户那么先通过id查询客户
	 */
	// 根据id查询用户
	public Customer getCustomerById(Integer id);

	// 更新用户
	public int updateCustomer(Customer customer);

	// 删除客户
	public int deleteCustomer(Integer id);
}
