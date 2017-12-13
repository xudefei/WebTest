package com.xxx.action;


import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.xxx.dao.Employee;
import com.xxx.service.EmployeeService;

public class EmployeeAction extends ActionSupport {
	@Autowired
	private EmployeeService employeeService;
	private String firstName, lastName;
	private Employee employee;
	

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8442995807620521709L;

	@Override
	public String execute() throws Exception {
		Employee employee = new Employee();
//		System.out.println(firstName+lastName);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employeeService.add(employee);
//		System.out.println(id);
		return super.execute();
	}
}
