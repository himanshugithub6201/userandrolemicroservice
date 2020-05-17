package com.tcs.workflow.api.userandrole.service;

import java.util.List;

import com.tcs.workflow.api.userandrole.exceptions.GroupExistsException;
import com.tcs.workflow.api.userandrole.exceptions.ResourceNotFoundException;
import com.tcs.workflow.api.userandrole.shared.GroupDto;

public interface GroupsService {

	GroupDto createGroup(GroupDto groupDto) throws GroupExistsException;

	GroupDto updateGroup(GroupDto groupDto, Long groupId) throws ResourceNotFoundException;

	boolean deleteGroup(Long groupId) throws ResourceNotFoundException;

	List<GroupDto> findAllGroups();

}
