package com.tcs.workflow.api.userandrole.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class CreateUserRequestModel {

	@NotNull(message = "UserId cannot be Null")
	private String userId;
	
	@NotNull(message = "UserId cannot be Null")
	private String password;
	
	@Email(message = "Email Format is not Correct")
	private String email;
	
	@NotNull(message = "roleId cannot be Null")
	private java.lang.Long[] roleIds;
	
	private java.lang.Long[] groupIds;
	
	@NotNull(message = "name cannot be Null")
	private String name;
	
	@NotNull(message = "name cannot be Null")
	private String adminFlag;

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

}
