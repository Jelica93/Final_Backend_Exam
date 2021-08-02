package com.iktpreobuka.dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.ClassEntity;

public interface ClassRepository extends CrudRepository<ClassEntity, Integer> {
	boolean existsById (Integer id);

}
