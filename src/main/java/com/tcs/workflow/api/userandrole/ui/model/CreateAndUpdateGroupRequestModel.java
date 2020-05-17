package com.tcs.workflow.api.userandrole.ui.model;

import javax.validation.constraints.NotNull;

public class CreateAndUpdateGroupRequestModel {

	@NotNull(message = "First Name can not be null")
	private String name;

	@NotNull(message = "Description Name can not be null")
	private String description;

	@NotNull
	private String isAdmin;

	@NotNull
	private String canViewReports;

	@NotNull
	private String deleteFlag;

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
