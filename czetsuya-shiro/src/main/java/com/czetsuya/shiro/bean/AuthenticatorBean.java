package com.czetsuya.shiro.bean;

import java.text.MessageFormat;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;

import com.czetsuya.shiro.model.user.User;
import com.czetsuya.shiro.service.UserService;

/**
 * @author Edward P. Legaspi
 * @since Feb 5, 2013
 */
@Stateless
@Named
public class AuthenticatorBean {
	private String username;
	private String password;

	@Inject
	private UserService userService;

	@Inject
	private Logger log;

	public String login() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			log.debug("[dropship-ejbs] active subject={}, user={}", subject, subject.getPrincipal());

			String role = getRole(subject);

			String landingPage = MessageFormat.format("/{0}/index.xhtml?faces-redirect=true", role);
			log.debug("redirecting to {} for role={}", landingPage, getRole(subject));
			return landingPage;
		} else {
			log.debug("[dropship-ejbs] login to the system with user={}, password={}", getUsername(), getPassword());
			AuthenticationToken token = new UsernamePasswordToken(getUsername(), getPassword());
			try {
				subject.login(token);
				User currentUser = userService.findByEmail(subject.getPrincipal().toString());

				subject.getSession().setAttribute("currentUser", currentUser);

				String role = getRole(subject);
				if (role.equals(RolesEnum.MEMBER.toString())) {
					subject.logout();
					return "/errors/authorization.xhtml";
				}

				String landingPage = MessageFormat.format("/{0}/index.xhtml?faces-redirect=true", getRole(subject));
				log.debug("redirecting to {} for role={}", landingPage, role);
				return landingPage;
			} catch (Exception e) {
				log.error("[dropship-ejbs] error login {}", e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "invalid account", "invalid account"));
				return "";
			}
		}
	}

	private String getRole(Subject subject) {
		if (!isUserLogged())
			return "";
		for (RolesEnum r : RolesEnum.values()) {
			String role = r.toString();
			if (subject != null && subject.hasRole(role)) {
				return role;
			}
		}
		return null;
	}

	public boolean isUserLogged() {
		Subject subject = SecurityUtils.getSubject();
		return subject != null && !isBlank(subject.getPrincipal());
	}

	public static boolean isBlank(Object value) {
		return ((value == null) || ((value instanceof String) && ((String) value).trim().length() == 0) || ((value instanceof String) && ((String) value)
				.equals("")));
	}

	public String getRole() {
		return getRole(SecurityUtils.getSubject());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
