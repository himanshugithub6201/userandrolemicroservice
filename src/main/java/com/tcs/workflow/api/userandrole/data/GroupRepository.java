package com.tcs.workflow.api.userandrole.data;

import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<GroupEntity, Long> {

	GroupEntity findByName(String name);

}
