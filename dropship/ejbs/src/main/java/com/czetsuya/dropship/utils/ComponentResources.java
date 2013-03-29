package com.czetsuya.dropship.utils;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.czetsuya.commons.utils.ParamBean;

/**
 * @author Edward P. Legaspi
 * @since Mar 27, 2013
 */
public class ComponentResources implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	public ResourceBundle getResourceBundle() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", locale);
		return resourceBundle;
	}

	@Produces
	@ApplicationScoped
	public ParamBean getParamBean() {
		return ParamBean.getInstance("dropship");
	}

	@Produces
	Logger createLogger(InjectionPoint injectionPoint) {
		return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}
}
