package com.czetsuya.bpmn;

import javax.inject.Inject;
import javax.inject.Named;

import org.activiti.cdi.annotation.ProcessVariable;
import org.slf4j.Logger;

/**
 * @author Edward P. Legaspi
 * @since Dec 3, 2012
 */
@Named
public class RegistrationEvent {
	@Inject
	private Logger log;

	@Inject
	@ProcessVariable
	private Object name;

	public void authorized() {
		log.debug("authorized " + name);
	}

	public void notAuthorized() {
		log.debug("notAuthorized " + name);
	}
}
