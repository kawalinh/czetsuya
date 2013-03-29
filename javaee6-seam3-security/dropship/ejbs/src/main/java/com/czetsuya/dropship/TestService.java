package com.czetsuya.dropship;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

/**
 * @author Edward P. Legaspi
 * @since Mar 27, 2013
 */
@Stateless
@LocalBean
public class TestService {
	@Inject
	private Logger log;

	public TestService() {

	}

	public void test() {
		log.debug("run");
	}
}
