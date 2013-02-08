package com.czetsuya.shiro.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.czetsuya.shiro.model.BaseEntity;

/**
 * @author Edward P. Legaspi
 * @since Oct 15, 2012
 */
@Entity
@Table(name = "CRM_PERMISSIONS")
@SequenceGenerator(name = "ID_GENERATOR", sequenceName = "CRM_PERMISSIONS_SEQ")
public class Permission extends BaseEntity {
	private static final long serialVersionUID = -2844386098501951453L;

	@Column(name = "ROLE", nullable = false)
	private String role;

	@Column(name = "ACTION", nullable = false, length = 1500)
	private String action;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
