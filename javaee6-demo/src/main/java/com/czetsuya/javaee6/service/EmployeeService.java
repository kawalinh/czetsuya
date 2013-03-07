package com.czetsuya.javaee6.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

import com.czetsuya.javaee6.model.Employee;

@Stateless
public class EmployeeService {
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private Logger log;
	
	public void create(Employee e) {
		log.debug("service.add");
		
		em.persist(e);
	}
}
