package com.czetsuya.bpmn;

import javax.inject.Inject;
import javax.inject.Named;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;

/**
 * @author Edward P. Legaspi
 * @since Dec 3, 2012
 */
@Named
public class RegistrationListener implements ExecutionListener {
	@Inject
	private Logger log;

	@Override
	public void notify(DelegateExecution delegateExecution) throws Exception {
		delegateExecution.setVariable("listenerSet", RegistrationListener.class.getName());		
	}

}
