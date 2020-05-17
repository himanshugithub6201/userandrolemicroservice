package com.tcs.workflow.api.userandrole.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7501235801040685069L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 50, unique = true)
	private String userId;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 100)
	private String encryptedPassword;

	@Column(nullable = false, length = 100)
	private String email;

	@ManyToMany
	@JoinTable(name = "user2role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private java.util.Set<RoleEntity> roles;

	@ManyToMany
	@JoinTable(name = "user2group", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "groups_id"))
	private java.util.Set<GroupEntity> groups;

	@Column(nullable = false, length = 3)
	private String adminFlag;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}

}
