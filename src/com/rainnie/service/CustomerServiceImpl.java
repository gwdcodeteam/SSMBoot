package com.rainnie.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rainnie.dao.CustomerDao;
import com.rainnie.po.Customer;
import com.rainnie.utils.Page;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public Page<Customer> findCustomerList(Integer page, Integer rows, String custName, String custSource,
			String custIndustry, String custLevel) {

		Customer customer = new Customer();
		if (StringUtils.isNotBlank(custName)) {
			customer.setCust_name(custName);
		}

		if (StringUtils.isNotBlank(custSource)) {
			customer.setCust_source(custSource);
		}

		if (StringUtils.isNotBlank(custIndustry)) {
			customer.setCust_industry(custIndustry);
		}

		if (StringUtils.isNotBlank(custLevel)) {
			customer.setCust_level(custLevel);
		}

		// 当前页
		customer.setStart((page - 1) * rows);
		// 每页数
		customer.setRows(rows);
		// 查询客户列表
		List<Customer> customers = customerDao.selectCustomerList(customer);
		Integer count = customerDao.selectCustomerListCount(customer);
		// 创建Page返回对象
		Page<Customer> result = new Page<>();
		result.setPage(page);
		result.setRows(customers);
		result.setSize(rows);
		result.setTotal(count);

		return result;
	}

	/**
	 * 创建客户
	 */
	@Override
	public int createCustomer(Customer customer) {

		return customerDao.createCustomer(customer);
	}

	/**
	 * 根据id查询客户
	 */
	@Override
	public Customer getCustomerById(Integer id) {
		Customer customer = customerDao.getCustomerById(id);
		return customer;
	}

	/**
	 * 更新客户
	 */
	@Override
	public int updateCustomer(Customer customer) {

		return customerDao.updateCustomer(customer);
	}

	/**
	 * 删除客户
	 */
	@Override
	public int deleteCustomer(Integer id) {

		return customerDao.deleteCustomer(id);
	}

}
