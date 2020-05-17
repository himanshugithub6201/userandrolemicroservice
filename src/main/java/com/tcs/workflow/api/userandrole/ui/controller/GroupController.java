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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tcs.workflow.api.userandrole.exceptions.GroupExistsException;
import com.tcs.workflow.api.userandrole.exceptions.ResourceNotFoundException;
import com.tcs.workflow.api.userandrole.service.GroupsService;
import com.tcs.workflow.api.userandrole.shared.GroupDto;
import com.tcs.workflow.api.userandrole.ui.model.CreateAndUpdateGroupRequestModel;
import com.tcs.workflow.api.userandrole.ui.model.CreateAndUpdateGroupResponseModel;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupController {

	private GroupsService groupsService;

	@Autowired
	public GroupController(GroupsService groupService) {
		super();
		this.groupsService = groupService;
	}

	@PostMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateAndUpdateGroupResponseModel> createGroup(
			@Valid @RequestBody CreateAndUpdateGroupRequestModel groupDetails) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		GroupDto groupDto = modelMapper.map(groupDetails, GroupDto.class);
		GroupDto createdGroup = null;
		try {
			createdGroup = groupsService.createGroup(groupDto);
		} catch (GroupExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		CreateAndUpdateGroupResponseModel returnValue = modelMapper.map(createdGroup,
				CreateAndUpdateGroupResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}

	
	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CreateAndUpdateGroupResponseModel> updateGroup(@PathVariable(value = "id")Long groupId,@Valid @RequestBody CreateAndUpdateGroupRequestModel groupDetails){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		GroupDto groupDto = modelMapper.map(groupDetails, GroupDto.class);
		GroupDto updatedGroup = null;
		try {
			updatedGroup=groupsService.updateGroup(groupDto,groupId);
		}catch(ResourceNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		CreateAndUpdateGroupResponseModel returnValue = modelMapper.map(updatedGroup,
				CreateAndUpdateGroupResponseModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	
	@DeleteMapping(value = "/{id}")
	public boolean deleteGroup(@PathVariable(value = "id") Long groupId) {
		try {
			return groupsService.deleteGroup(groupId);
		}catch(ResourceNotFoundException ex){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	@GetMapping
	public List<CreateAndUpdateGroupResponseModel> getAllGroups(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<GroupDto> groupsDto = groupsService.findAllGroups();
		List<CreateAndUpdateGroupResponseModel> returnValue=groupsDto.stream().map(groupDto->modelMapper.map(groupDto, CreateAndUpdateGroupResponseModel.class)).collect(Collectors.toList());
		return returnValue;
	}
}
