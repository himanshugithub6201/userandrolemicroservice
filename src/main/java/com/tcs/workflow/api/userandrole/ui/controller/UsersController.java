package com.tcs.workflow.api.userandrole.ui.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.workflow.api.userandrole.exceptions.ResourceNotFoundException;
import com.tcs.workflow.api.userandrole.exceptions.UserExistsException;
import com.tcs.workflow.api.userandrole.service.UsersService;
import com.tcs.workflow.api.userandrole.shared.UserDto;
import com.tcs.workflow.api.userandrole.ui.model.CreateAndUpdateGroupResponseModel;
import com.tcs.workflow.api.userandrole.ui.model.CreateRoleResponseModel;
import com.tcs.workflow.api.userandrole.ui.model.CreateUserRequestModel;
import com.tcs.workflow.api.userandrole.ui.model.CreateUserResponseModel;
import com.tcs.workflow.api.userandrole.ui.model.UpdateUserRequestModel;
import com.tcs.workflow.api.userandrole.ui.model.UpdateUserResponseModel;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {
	private UsersService usersService;
	
	@Autowired
	public UsersController(UsersService usersService) {
		super();
		this.usersService = usersService;
	}
	
	@PostMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) throws UserExistsException, ResourceNotFoundException{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto =modelMapper.map(userDetails, UserDto.class);
		UserDto createdUser=null;
		try {
			createdUser=usersService.createUser(userDto);
		}catch(UserExistsException ex) {
			throw ex;
		}
		Set<CreateRoleResponseModel> rolesReturn=createdUser.getRoles().stream().map(roleEntity->modelMapper.map(roleEntity, CreateRoleResponseModel.class)).collect(Collectors.toSet());
		Set<CreateAndUpdateGroupResponseModel> groupsReturn=createdUser.getGroups().stream().map(groupEnt->modelMapper.map(groupEnt, CreateAndUpdateGroupResponseModel.class)).collect(Collectors.toSet());
		CreateUserResponseModel returnValue =modelMapper.map(createdUser,CreateUserResponseModel.class );
		returnValue.setRoles(rolesReturn);
		returnValue.setGroups(groupsReturn);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
		
	}

	@GetMapping
	public List<CreateUserResponseModel> getAllUsers(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<UserDto> usersDto=usersService.findAllUsers();
		List<CreateUserResponseModel> returnValue = usersDto.stream().map(userDto->
			modelMapper.map(userDto, CreateUserResponseModel.class)
		).collect(Collectors.toList());
		return returnValue;	
	}
	
	@PutMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UpdateUserResponseModel> updateUser(@Valid @RequestBody UpdateUserRequestModel userDetails) throws ResourceNotFoundException, UserExistsException{
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto =modelMapper.map(userDetails,UserDto.class);
		UserDto updatedUser=null;
		try {
			updatedUser=usersService.updateUser(userDto);
		}catch(UserExistsException ex) {
			throw ex;
		}
		Set<CreateRoleResponseModel> rolesReturn=updatedUser.getRoles().stream().map(roleEntity->modelMapper.map(roleEntity, CreateRoleResponseModel.class)).collect(Collectors.toSet());
		Set<CreateAndUpdateGroupResponseModel> groupsReturn=updatedUser.getGroups().stream().map(groupEnt->modelMapper.map(groupEnt, CreateAndUpdateGroupResponseModel.class)).collect(Collectors.toSet());
		UpdateUserResponseModel returnValue =modelMapper.map(updatedUser,UpdateUserResponseModel.class );
		returnValue.setRoles(rolesReturn);
		returnValue.setGroups(groupsReturn);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
}
