package com.tcs.workflow.api.userandrole.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcs.workflow.api.userandrole.data.GroupEntity;
import com.tcs.workflow.api.userandrole.data.GroupRepository;
import com.tcs.workflow.api.userandrole.data.RoleEntity;
import com.tcs.workflow.api.userandrole.data.RoleRepository;
import com.tcs.workflow.api.userandrole.data.UserEntity;
import com.tcs.workflow.api.userandrole.data.UserRepository;
import com.tcs.workflow.api.userandrole.exceptions.ResourceNotFoundException;
import com.tcs.workflow.api.userandrole.exceptions.UserExistsException;
import com.tcs.workflow.api.userandrole.shared.UserDto;

@Service
public class UsersServiceImpl implements UsersService {

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private GroupRepository groupRepository;

	@Autowired
	public UsersServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, GroupRepository groupRepository,
			UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.groupRepository = groupRepository;
	}

	@Override
	public UserDto createUser(UserDto userDetails) throws UserExistsException, ResourceNotFoundException {
		userDetails.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity existingUser = userRepository.findByuserId(userDetails.getUserId());
		if (existingUser != null) {
			throw new UserExistsException("User already exists with this userId");
		}
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

		java.util.Set<RoleEntity> roles = new java.util.HashSet<RoleEntity>();
		for (int i = 0; i < userDetails.getRoleIds().length; i++) {
			roles.add(roleRepository.findById(userDetails.getRoleIds()[i])
					.orElseThrow(() -> new ResourceNotFoundException("Role doesnot exist with this roleId")));
		}
		if (roles != null)
			userEntity.setRoles(roles);

		java.util.Set<GroupEntity> groups = new java.util.HashSet<GroupEntity>();
		for (int i = 0; i < userDetails.getGroupIds().length; i++) {
			groups.add(groupRepository.findById(userDetails.getGroupIds()[i])
					.orElseThrow(() -> new ResourceNotFoundException("Group doesnot exist with this roleId")));
		}
		if (groups != null)
			userEntity.setGroups(groups);
		userRepository.save(userEntity);
		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
		return returnValue;
	}

	@Override
	public List<UserDto> findAllUsers() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<UserEntity> usersEntity = (List<UserEntity>) userRepository.findAll();
		List<UserDto> returnValue = usersEntity.stream().map(userEntity -> modelMapper.map(userEntity, UserDto.class))
				.collect(Collectors.toList());
		return returnValue;
	}

	@Override
	public UserDto updateUser(UserDto userDetails) throws ResourceNotFoundException, UserExistsException {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity = userRepository.findById(userDetails.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Role not found for this ID :: " + userDetails.getId()));
		userEntity.setName(userDetails.getName());
		userEntity.setEmail(userDetails.getEmail());
		userEntity.setUserId(userDetails.getUserId());
		userEntity.setAdminFlag(userDetails.getAdminFlag());
		java.util.Set<RoleEntity> roles = new java.util.HashSet<RoleEntity>();
		for (int i = 0; i < userDetails.getRoleIds().length; i++) {
			roles.add(roleRepository.findById(userDetails.getRoleIds()[i])
					.orElseThrow(() -> new ResourceNotFoundException("Role doesnot exist with this roleId")));
		}
		if (roles != null)
			userEntity.setRoles(roles);

		java.util.Set<GroupEntity> groups = new java.util.HashSet<GroupEntity>();
		for (int i = 0; i < userDetails.getGroupIds().length; i++) {
			groups.add(groupRepository.findById(userDetails.getGroupIds()[i])
					.orElseThrow(() -> new ResourceNotFoundException("Group doesnot exist with this roleId")));
		}
		if (groups != null)
			userEntity.setGroups(groups);
		UserEntity existingUser = userRepository.findByuserId(userEntity.getUserId());
		if (existingUser != null) {
			throw new UserExistsException("User already exists with this userId");
		}
		userRepository.save(userEntity);
		UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
		return returnValue;
	}

}
