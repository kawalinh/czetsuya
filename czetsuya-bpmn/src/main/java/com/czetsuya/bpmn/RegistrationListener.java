package com.czetsuya.bpmn;

import javax.inject.Inject;
import javax.inject.Named;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;

/**
 * @author Edward P. Legaspi
 * @since Dec 3, 2012
 */
@Named
public class RegistrationListener implements JavaDelegate {
	@Inject
	private Logger log;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		log.debug("registration listener");
	}

}
