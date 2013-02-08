package com.czetsuya.shiro.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

import com.czetsuya.shiro.model.user.User;

@Stateless
@LocalBean
public class UserService {
	@PersistenceContext
	private EntityManager em;

	@Inject
	private Logger log;

	public User findByEmail(String email) {
		log.debug("[dropship-ejbs] searching for user with email={}", email);
		try {
			return (User) em.createQuery("FROM " + User.class.getName() + " u WHERE u.username=:email")
					.setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			log.debug("[dropship-ejbs] no result found");
			return null;
		}
	}

	public User findByEmail(String email, String activationCode) {
		log.debug("[dropship-ejbs] searching for user with email={}, activationCode={}", email, activationCode);
		try {
			return (User) em
					.createQuery(
							"FROM " + User.class.getName()
									+ " u WHERE u.username=:email AND u.activationCode=:activationCode")
					.setParameter("email", email).setParameter("activationCode", activationCode).getSingleResult();
		} catch (NoResultException e) {
			log.debug("[dropship-ejbs] no result found");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> findByRole(String role) {
		log.debug("[dropship-ejbs] searching for users with role={}", role);
		try {
			return (List<User>) em.createQuery(
					"SELECT u FROM " + User.class.getName() + " u JOIN u.roles r WHERE r.name='" + role + "'")
					.getResultList();
		} catch (NoResultException e) {
			log.debug("[dropship-ejbs] no result found");
			return null;
		}
	}
}
