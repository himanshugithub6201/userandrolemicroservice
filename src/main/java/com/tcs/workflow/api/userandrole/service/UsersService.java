package com.tcs.workflow.api.userandrole.service;

import java.util.List;

import com.tcs.workflow.api.userandrole.exceptions.ResourceNotFoundException;
import com.tcs.workflow.api.userandrole.exceptions.UserExistsException;
import com.tcs.workflow.api.userandrole.shared.UserDto;

public interface UsersService {

	UserDto createUser(UserDto userDto) throws UserExistsException, ResourceNotFoundException;

	List<UserDto> findAllUsers();

	UserDto updateUser(UserDto userDto) throws ResourceNotFoundException, UserExistsException;

}
