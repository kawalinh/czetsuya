package com.czetsuya.bpmn;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;

/**
 * @author Edward P. Legaspi
 * @since Dec 3, 2012
 */
@Startup
@Singleton
public class StartupListener {
	@Inject
	private Logger log;

	@PostConstruct
	private void init() {
		log.debug("startup");
	}
}
