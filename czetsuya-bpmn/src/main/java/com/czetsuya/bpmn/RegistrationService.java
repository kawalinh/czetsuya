package com.czetsuya.bpmn;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;

/**
 * @author Edward P. Legaspi
 * @since Nov 28, 2012
 */
@Named
@Stateless
public class RegistrationService implements Serializable {
	private static final long serialVersionUID = 8938158808570659391L;

	@Inject
	private Logger log;
	
	@Inject
	private RuntimeService runtimeService;

	public void start() {
		log.debug("start registration");
		
		runtimeService.startProcessInstanceByKey("registrationProcess");
	}
}
