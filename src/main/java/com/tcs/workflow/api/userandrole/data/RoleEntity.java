package com.tcs.workflow.api.userandrole.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Role")
public class RoleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3996506499624429218L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 50, unique = true)
	private String name;

	@Column(nullable = false, length = 100)
	private String description;

	@Column(nullable = false, length = 3)
	private String isAdmin;

	@Column(nullable = false, length = 3)
	private String canViewReports;

	@Column(nullable = false, length = 3)
	private String deleteFlag;
	
	@ManyToMany(mappedBy = "roles")
	private java.util.Set<UserEntity> users;

	public java.util.Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(java.util.Set<UserEntity> users) {
		this.users = users;
	}

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
