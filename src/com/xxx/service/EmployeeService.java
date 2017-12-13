package com.xxx.service;

import java.io.Serializable;

import com.xxx.dao.Employee;

public interface EmployeeService {
	Serializable add(Employee employee);
	Employee get(Serializable id);
}
