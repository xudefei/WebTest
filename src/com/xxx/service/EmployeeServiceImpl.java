package com.xxx.service;

import java.io.Serializable;

import com.xxx.dao.Employee;
import com.xxx.dao.EmployeeDao;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao;
	
	

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public Serializable add(Employee employee) {
		return employeeDao.save(employee);
	}

	public Employee get(Serializable id) {
		return employeeDao.get(Employee.class, id);
	}

}
