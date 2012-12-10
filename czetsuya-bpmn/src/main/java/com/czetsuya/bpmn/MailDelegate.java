package com.czetsuya.bpmn;

import javax.inject.Inject;
import javax.inject.Named;

import org.activiti.cdi.annotation.ProcessVariable;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;

/**
 * @author Edward P. Legaspi
 * @since Dec 3, 2012
 */
@Named
public class MailDelegate implements JavaDelegate {
	@Inject
	private Logger log;

	@Inject
	@ProcessVariable
	private Object name;

	@Inject
	@ProcessVariable
	private Object mailRecipient;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		log.debug(
				"Username {} has been authorized. And an email has been sent to {}.",
				name, mailRecipient);
	}
}
