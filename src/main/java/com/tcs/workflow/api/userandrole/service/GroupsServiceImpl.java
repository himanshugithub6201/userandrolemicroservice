package com.tcs.workflow.api.userandrole.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.workflow.api.userandrole.data.GroupEntity;
import com.tcs.workflow.api.userandrole.data.GroupRepository;
import com.tcs.workflow.api.userandrole.exceptions.GroupExistsException;
import com.tcs.workflow.api.userandrole.exceptions.ResourceNotFoundException;
import com.tcs.workflow.api.userandrole.shared.GroupDto;


@Service
public class GroupsServiceImpl implements GroupsService {

	private GroupRepository groupRepository;
	
	@Autowired
	public GroupsServiceImpl(GroupRepository groupRepository) {
		super();
		this.groupRepository = groupRepository;
	}

	@Override
	public GroupDto createGroup(GroupDto groupDetails) throws GroupExistsException {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		GroupEntity existingGroup=groupRepository.findByName(groupDetails.getName());
		if(existingGroup !=null) {
			throw new GroupExistsException("Group already exists with this name");
		}
		GroupEntity groupEntity=modelMapper.map(groupDetails, GroupEntity.class);
		groupRepository.save(groupEntity);
		GroupDto returnValue=modelMapper.map(groupEntity, GroupDto.class);
		return returnValue; 
	}

	@Override
	public GroupDto updateGroup(GroupDto groupDetails, Long groupId) throws ResourceNotFoundException {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		GroupEntity groupEntity=groupRepository.findById(groupId).orElseThrow(()->new ResourceNotFoundException("Group not found with this id ::" +groupId));
		groupEntity.setName(groupDetails.getName());
		groupEntity.setDescription(groupDetails.getDescription());
		groupEntity.setIsAdmin(groupDetails.getIsAdmin());
		groupEntity.setCanViewReports(groupDetails.getCanViewReports());
		groupEntity.setDeleteFlag(groupDetails.getDeleteFlag());
		groupRepository.save(groupEntity);
		GroupDto returnValue=modelMapper.map(groupEntity, GroupDto.class);
		return returnValue; 
	}

	@Override
	public boolean deleteGroup(Long groupId) throws ResourceNotFoundException {
		groupRepository.findById(groupId).orElseThrow(()->new ResourceNotFoundException("Group not found with this id ::" +groupId));
		groupRepository.deleteById(groupId);
		return true;
	}

	@Override
	public List<GroupDto> findAllGroups() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<GroupEntity> groupsEntity =(List<GroupEntity>)groupRepository.findAll();
		List<GroupDto> returnValue=groupsEntity.stream().map(groupEntity->modelMapper.map(groupEntity, GroupDto.class)).collect(Collectors.toList());
		return returnValue;
	}

}
