package com.czetsuya.javaee6.mbeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.czetsuya.javaee6.model.Employee;
import com.czetsuya.javaee6.model.Name;
import com.czetsuya.javaee6.service.EmployeeService;

@Named
@RequestScoped
public class EmployeeBean {
	@Inject
	private EmployeeService employeeService;

	@Inject
	private Logger log;

	public String create() {
		log.debug("mbean.create");

		Employee e = new Employee();
		Name name = new Name();
		name.setFirstName("Edward");
		name.setLastName("Legaspi");
		e.setName(name);
		e.setAddress("My Address");
		employeeService.create(e);

		return "";
	}
}
