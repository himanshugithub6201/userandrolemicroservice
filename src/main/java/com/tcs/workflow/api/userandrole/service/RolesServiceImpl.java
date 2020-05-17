package com.tcs.workflow.api.userandrole.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.workflow.api.userandrole.data.RoleEntity;
import com.tcs.workflow.api.userandrole.data.RoleRepository;
import com.tcs.workflow.api.userandrole.exceptions.ExceptionNotFound;
import com.tcs.workflow.api.userandrole.exceptions.ResourceNotFoundException;
import com.tcs.workflow.api.userandrole.exceptions.RoleExistsException;
import com.tcs.workflow.api.userandrole.shared.RoleDto;

@Service
public class RolesServiceImpl implements RolesService {

	private RoleRepository roleRepository;

	@Autowired
	public RolesServiceImpl(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	@Override
	public RoleDto createRole(RoleDto roleDetails)throws RoleExistsException {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		RoleEntity existingRole=roleRepository.findByName(roleDetails.getName());
		if(existingRole !=null) {
			throw new RoleExistsException("User already exist with this name");
		}
		RoleEntity roleEntity = modelMapper.map(roleDetails, RoleEntity.class);
		roleRepository.save(roleEntity);
		RoleDto returnValue = modelMapper.map(roleEntity, RoleDto.class);
		return returnValue;
	}

	@Override
	public RoleDto updateRole(RoleDto roleDetails, Long roleId) throws ResourceNotFoundException {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		RoleEntity roleEntity = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role not found for this ID :: "+roleId));
		roleEntity.setName(roleDetails.getName());
		roleEntity.setDescription(roleDetails.getDescription());
		roleEntity.setIsAdmin(roleDetails.getIsAdmin());
		roleEntity.setCanViewReports(roleDetails.getCanViewReports());
		roleEntity.setDeleteFlag(roleDetails.getDeleteFlag());
		roleRepository.save(roleEntity);
		RoleDto returnValue = modelMapper.map(roleEntity, RoleDto.class);
		return returnValue;
	}

	@Override
	public boolean deleteRole(Long roleId) throws ResourceNotFoundException {
		roleRepository.findById(roleId).orElseThrow(()->new ResourceNotFoundException("Role not found for this ID :: "+roleId));
		roleRepository.deleteById(roleId);
		return true;
	}

	@Override
	public RoleDto findByRoleName(String name) throws ExceptionNotFound {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		RoleEntity roleEntity=roleRepository.findByName(name);
		if(roleEntity ==null) {
			throw new ExceptionNotFound("Role does not exist with this name");
		}
		RoleDto returnValue = modelMapper.map(roleEntity, RoleDto.class);
		return returnValue;
	}

	@Override
	public List<RoleDto> getAllRoles() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<RoleEntity> rolesEntity =(List<RoleEntity>)roleRepository.findAll();
		List<RoleDto> returnvalue=rolesEntity.stream().map(roleEntity->modelMapper.map(roleEntity, RoleDto.class)).collect(Collectors.toList());
		return returnvalue;
	}



}
