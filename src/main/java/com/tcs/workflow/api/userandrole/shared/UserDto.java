package com.tcs.workflow.api.userandrole.shared;

import java.io.Serializable;

import com.tcs.workflow.api.userandrole.data.GroupEntity;
import com.tcs.workflow.api.userandrole.data.RoleEntity;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5188061649604950284L;
	private Long id;

	private String userId;

	private String password;

	private String email;

	private java.lang.Long[] roleIds;

	private java.lang.Long[] groupIds;
	
	private java.util.Set<RoleEntity> roles;
	
	private java.util.Set<GroupEntity> groups;

	private String name;

	private String adminFlag;
	
	private String encryptedPassword;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public java.lang.Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(java.lang.Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public java.lang.Long[] getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(java.lang.Long[] groupIds) {
		this.groupIds = groupIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public java.util.Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(java.util.Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public java.util.Set<GroupEntity> getGroups() {
		return groups;
	}

	public void setGroups(java.util.Set<GroupEntity> groups) {
		this.groups = groups;
	}
	
	
	
	

}
