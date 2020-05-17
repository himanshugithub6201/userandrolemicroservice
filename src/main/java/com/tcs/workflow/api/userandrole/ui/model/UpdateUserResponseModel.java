package com.tcs.workflow.api.userandrole.ui.model;

public class UpdateUserResponseModel {
	private Long id;
	private String name;
	private String userId;
	private String email;

    private java.util.Set<CreateRoleResponseModel> roles;
	
	private java.util.Set<CreateAndUpdateGroupResponseModel> groups;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public java.util.Set<CreateRoleResponseModel> getRoles() {
		return roles;
	}

	public void setRoles(java.util.Set<CreateRoleResponseModel> roles) {
		this.roles = roles;
	}

	public java.util.Set<CreateAndUpdateGroupResponseModel> getGroups() {
		return groups;
	}

	public void setGroups(java.util.Set<CreateAndUpdateGroupResponseModel> groups) {
		this.groups = groups;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
