package com.czetsuya.shiro.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;

import com.czetsuya.shiro.Secured;

/**
 * @author Edward P. Legaspi
 * @since Feb 8, 2013
 */
@RequestScoped
@Named
@Secured
public class AffiliateBean {
	@Inject
	private Logger log;

	@RequiresRoles("affiliate")
	public String getName() {
		log.debug("affiliate");
		return "affiliate";
	}
}
