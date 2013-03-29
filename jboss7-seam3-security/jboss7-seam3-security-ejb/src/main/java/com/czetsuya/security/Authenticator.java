package com.czetsuya.security;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.picketlink.idm.impl.api.PasswordCredential;
import org.picketlink.idm.impl.api.model.SimpleUser;

/**
 * @author Edward P. Legaspi
 * @since Mar 29, 2013
 */
@Model
public class Authenticator extends BaseAuthenticator {
	@Inject
	Credentials credentials;

	public Authenticator() {

	}

	@Override
	public void authenticate() {
		System.out.println("logging in: " + credentials.getUsername());

		if ("demo".equals(credentials.getUsername())
				&& credentials.getCredential() instanceof PasswordCredential
				&& "demo".equals(((PasswordCredential) credentials.getCredential()).getValue())) {

			setStatus(AuthenticationStatus.SUCCESS);
			setUser(new SimpleUser("demo"));

		}

	}

}
