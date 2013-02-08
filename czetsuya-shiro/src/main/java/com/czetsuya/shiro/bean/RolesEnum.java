package com.czetsuya.shiro.bean;

/**
 * @author Edward P. Legaspi
 * @since Dec 22, 2012
 */
public enum RolesEnum {
	ADMIN("admin"), MEMBER("member"), AFFILIATE("affiliate");

	private String role;

	private RolesEnum(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return role;
	}
}
