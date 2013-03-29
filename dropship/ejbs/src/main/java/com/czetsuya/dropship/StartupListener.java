package com.czetsuya.dropship;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.slf4j.Logger;

/**
 * @author Edward P. Legaspi
 * @since Mar 27, 2013
 */
@Singleton
@Startup
public class StartupListener {
	@Inject
	private Logger log;

	@Inject
	private TestService testService;

	public StartupListener() {

	}

	@PostConstruct
	private void init() {
		testService.test();
		log.debug("startup");
	}
}
