package com.tcs.workflow.api.userandrole.service;

import java.util.List;

import com.tcs.workflow.api.userandrole.exceptions.ExceptionNotFound;
import com.tcs.workflow.api.userandrole.exceptions.ResourceNotFoundException;
import com.tcs.workflow.api.userandrole.exceptions.RoleExistsException;
import com.tcs.workflow.api.userandrole.shared.RoleDto;


public interface RolesService {

	RoleDto createRole(RoleDto roleDetails) throws RoleExistsException;

	RoleDto updateRole(RoleDto roleDto, Long roleId) throws ResourceNotFoundException;

	boolean deleteRole(Long roleId) throws ResourceNotFoundException;

	RoleDto findByRoleName(String name) throws ExceptionNotFound;

	List<RoleDto> getAllRoles();

	
}
