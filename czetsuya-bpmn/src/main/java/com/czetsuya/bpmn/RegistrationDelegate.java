package com.czetsuya.bpmn;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author Edward P. Legaspi
 * @since Dec 3, 2012
 */
public class RegistrationDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariable("name", "sakuya");
		execution.setVariable("authorized", true);
		
		execution.setVariable("mailRecipient", "czetsuya@gmail.com");
	}
}
