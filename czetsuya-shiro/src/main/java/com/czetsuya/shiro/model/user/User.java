package com.czetsuya.shiro.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Proxy;

import com.czetsuya.shiro.model.BaseEntity;

/**
 * @author Edward P. Legaspi
 * @since Sep 30, 2012
 **/
@Entity
@Table(name = "CRM_USERS")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "CRM_USERS_SEQ")
@Inheritance(strategy = InheritanceType.JOINED)
@Proxy(lazy = false)
public class User extends BaseEntity {
	private static final long serialVersionUID = 5274477345750145639L;

	@Column(name = "USERNAME", length = 50, unique = true)
	private String username;

	@Column(name = "PASSWORD", length = 250)
	private String password;

	@Column(name = "SALT", length = 100)
	private String salt;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CRM_USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@ForeignKey(name = "TO_USER_FK", inverseName = "TO_ROLE_FK")
	private List<Role> roles = new ArrayList<Role>();

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
