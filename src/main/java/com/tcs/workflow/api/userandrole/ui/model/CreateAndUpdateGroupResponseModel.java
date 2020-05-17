package com.tcs.workflow.api.userandrole.ui.model;

public class CreateAndUpdateGroupResponseModel {
	private Long id;
	private String name;

	private String description;

	private String isAdmin;

	private String canViewReports;

	private String deleteFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getCanViewReports() {
		return canViewReports;
	}

	public void setCanViewReports(String canViewReports) {
		this.canViewReports = canViewReports;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
