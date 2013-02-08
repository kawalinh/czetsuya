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
@RequiresRoles({ "member" })
public class MemberBean {
	@Inject
	private Logger log;

	public String getName() {
		log.debug("member");
		return "member";
	}
}
