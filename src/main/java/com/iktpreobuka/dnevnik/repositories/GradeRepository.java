package com.iktpreobuka.dnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dnevnik.entities.GradeEntity;
import com.iktpreobuka.dnevnik.entities.StudentEntity;
import com.iktpreobuka.dnevnik.entities.Subject;
import com.iktpreobuka.dnevnik.entities.TeacherEntity;
//import com.iktpreobuka.dnevnik.entities.TeacherSubject;

public interface GradeRepository extends CrudRepository<GradeEntity, Integer> {
	boolean existsById(Integer id);
//	public GradeEntity findByTeacherAndSubject(TeacherEntity teacher, Subject subject);
//	public GradeEntity findByStudentTeacherSubject
}
