package com.tcs.workflow.api.userandrole.data;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByuserId(String userId);

}
