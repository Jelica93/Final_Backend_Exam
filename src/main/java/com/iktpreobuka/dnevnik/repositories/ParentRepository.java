package com.iktpreobuka.dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.ParentEntity;

public interface ParentRepository extends CrudRepository<ParentEntity, Integer> {
	boolean existsById(Integer id);
}
