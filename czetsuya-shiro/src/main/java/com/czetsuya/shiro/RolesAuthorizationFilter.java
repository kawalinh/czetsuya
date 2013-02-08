package com.czetsuya.shiro;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * @author Edward P. Legaspi
 * @since Dec 22, 2012
 */
public class RolesAuthorizationFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object obj)
			throws IOException {

		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) obj;

		if (rolesArray == null || rolesArray.length == 0) {
			return true;
		}

		Set<String> roles = CollectionUtils.asSet(rolesArray);
		for (String role : roles) {
			if (subject.hasRole(role))
				return true;
		}

		return false;
	}

}
