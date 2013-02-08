package com.czetsuya.shiro;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Edward P. Legaspi
 * @since Oct 10, 2012
 * @description 
 *              http://balusc.blogspot.com/2013/01/apache-shiro-is-it-ready-for-java
 *              -ee-6.html#MakeShiroJSFAjaxAware
 */
@Secured
@Interceptor
public class SecurityInterceptor implements Serializable {
	private static final long serialVersionUID = -5431783969464350147L;
	private Logger log = LoggerFactory.getLogger(SecurityInterceptor.class);

	@AroundInvoke
	public Object interceptGet(InvocationContext context) throws Exception {
		log.debug("Securing {}.{}({})",
				new Object[] { context.getClass().getName(), context.getMethod(), context.getParameters() });

		Subject subject = SecurityUtils.getSubject();
		Class<?> c = context.getTarget().getClass();
		Method m = context.getMethod();

		if (!subject.isAuthenticated() && hasAnnotation(c, m, RequiresAuthentication.class)) {
			throw new UnauthenticatedException("Authentication required");
		}

		if (subject.getPrincipal() != null && hasAnnotation(c, m, RequiresGuest.class)) {
			throw new UnauthenticatedException("Guest required");
		}

		if (subject.getPrincipal() == null && hasAnnotation(c, m, RequiresUser.class)) {
			throw new UnauthenticatedException("User required");
		}

		RequiresRoles roles = getAnnotation(c, m, RequiresRoles.class);

		if (roles != null) {
			subject.checkRoles(Arrays.asList(roles.value()));
		}

		RequiresPermissions permissions = getAnnotation(c, m, RequiresPermissions.class);

		if (permissions != null) {
			subject.checkPermissions(permissions.value());
		}

		return context.proceed();
	}

	private static boolean hasAnnotation(Class<?> c, Method m, Class<? extends Annotation> a) {
		return m.isAnnotationPresent(a) || c.isAnnotationPresent(a) || c.getSuperclass().isAnnotationPresent(a);
	}

	private static <A extends Annotation> A getAnnotation(Class<?> c, Method m, Class<A> a) {
		return m.isAnnotationPresent(a) ? m.getAnnotation(a) : c.isAnnotationPresent(a) ? c.getAnnotation(a) : c
				.getSuperclass().getAnnotation(a);
	}
}
