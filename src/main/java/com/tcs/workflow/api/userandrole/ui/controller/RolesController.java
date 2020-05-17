package com.tcs.workflow.api.userandrole.ui.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tcs.workflow.api.userandrole.exceptions.ExceptionNotFound;
import com.tcs.workflow.api.userandrole.exceptions.ResourceNotFoundException;
import com.tcs.workflow.api.userandrole.exceptions.RoleExistsException;
import com.tcs.workflow.api.userandrole.service.RolesService;
import com.tcs.workflow.api.userandrole.shared.RoleDto;
import com.tcs.workflow.api.userandrole.ui.model.CreateRoleRequestModel;
import com.tcs.workflow.api.userandrole.ui.model.CreateRoleResponseModel;
import com.tcs.workflow.api.userandrole.ui.model.UpdateRoleRequestModel;
import com.tcs.workflow.api.userandrole.ui.model.UpdateRoleResponseModel;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RolesController {

	private RolesService rolesService;

	@Autowired
	public RolesController(RolesService rolesService) {
		super();
		this.rolesService = rolesService;
	}

	@PostMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateRoleResponseModel> createRole(@Valid @RequestBody CreateRoleRequestModel roleDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		RoleDto roleDto = modelMapper.map(roleDetails, RoleDto.class);
		RoleDto createdRole = null;
		try {
			createdRole = rolesService.createRole(roleDto);
		} catch (RoleExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}

		CreateRoleResponseModel returnValue = modelMapper.map(createdRole, CreateRoleResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}

	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UpdateRoleResponseModel> updateRole(@PathVariable(value = "id") Long roleId,
			@Valid @RequestBody UpdateRoleRequestModel roleDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		RoleDto roleDto = modelMapper.map(roleDetails, RoleDto.class);
		RoleDto updatedRole = null;
		try {
			updatedRole = rolesService.updateRole(roleDto, roleId);
		} catch (ResourceNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		UpdateRoleResponseModel returnvalue = modelMapper.map(updatedRole, UpdateRoleResponseModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(returnvalue);

	}

	@DeleteMapping(value = "/{id}")
	public boolean deleteRole(@PathVariable(value = "id") Long roleId){
		try {
			return rolesService.deleteRole(roleId);
		} catch (ResourceNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<CreateRoleResponseModel> searchByName(@RequestParam(name="name",required=true)String name) throws ExceptionNotFound{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		RoleDto roleDto=null;
		try {
			roleDto=rolesService.findByRoleName(name);
		}catch(ExceptionNotFound ex) {
			throw ex;
		}
		CreateRoleResponseModel returnValue = modelMapper.map(roleDto, CreateRoleResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	

	@GetMapping
	public List<CreateRoleResponseModel> getAllRole() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<RoleDto> rolesDto= rolesService.getAllRoles();
		List<CreateRoleResponseModel> returnvalue=rolesDto.stream().map(roleDto->modelMapper.map(roleDto, CreateRoleResponseModel.class)).collect(Collectors.toList());
        return returnvalue;
    } 
	
	
//	@PutMapping
//	public ResponseEntity<CreateRoleResponseModel> updateRoleByName(@RequestBody UpdateRoleRequestModel roleDetails){
//		ModelMapper modelMapper = new ModelMapper();
//		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		RoleDto roleDto = modelMapper.map(roleDetails, RoleDto.class);
//		RoleDto updatedRole = null;
//		try {
//			updatedRole = rolesService.updateRole(roleDto);
//		} catch (ResourceNotFoundException ex) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
//		}
//		
//		
//	}
}
