package com.czetsuya.bpmn;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Edward P. Legaspi
 * @since Dec 3, 2012
 */
@RequestScoped
@Named
public class RegistrationBean {
	@Inject
	private RegistrationService registrationService; 
	
	public void start() {
		registrationService.start();
	}
}
