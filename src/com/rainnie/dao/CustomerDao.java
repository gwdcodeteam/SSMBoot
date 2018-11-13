package com.rainnie.dao;

import java.util.List;

import com.rainnie.po.Customer;

public interface CustomerDao {

	// 客户列表
	public List<Customer> selectCustomerList(Customer customer);

	// 客户数
	public Integer selectCustomerListCount(Customer customer);

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
