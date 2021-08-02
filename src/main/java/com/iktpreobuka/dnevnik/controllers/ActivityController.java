package com.iktpreobuka.dnevnik.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dnevnik.entities.ClassEntity;
import com.iktpreobuka.dnevnik.entities.EmailObject;
import com.iktpreobuka.dnevnik.entities.GradeEntity;
import com.iktpreobuka.dnevnik.entities.Semester;
import com.iktpreobuka.dnevnik.entities.StudentEntity;
//import com.iktpreobuka.dnevnik.entities.StudentTeacherSubject;
import com.iktpreobuka.dnevnik.entities.Subject;
import com.iktpreobuka.dnevnik.entities.TeacherEntity;
//import com.iktpreobuka.dnevnik.entities.TeacherSubject;
import com.iktpreobuka.dnevnik.entities.dto.ClassDTO;
import com.iktpreobuka.dnevnik.entities.dto.GradeDTO;
import com.iktpreobuka.dnevnik.entities.dto.SemesterDTO;
import com.iktpreobuka.dnevnik.entities.dto.SubjectDTO;
import com.iktpreobuka.dnevnik.repositories.ClassRepository;
import com.iktpreobuka.dnevnik.repositories.GradeRepository;
import com.iktpreobuka.dnevnik.repositories.ParentRepository;
import com.iktpreobuka.dnevnik.repositories.SemesterRepository;
import com.iktpreobuka.dnevnik.repositories.StudentRepository;
import com.iktpreobuka.dnevnik.repositories.SubjectRepository;
import com.iktpreobuka.dnevnik.repositories.TeacherRepository;
//import com.iktpreobuka.dnevnik.repositories.TeacherSubjectRepository;
//import com.iktpreobuka.dnevnik.repositories.TeacherSubjectRepository;
import com.iktpreobuka.dnevnik.services.EmailService;
import com.iktpreobuka.dnevnik.services.EmailServiceImpl;
import com.iktpreobuka.dnevnik.services.FileHandler;
//import com.iktpreobuka.dnevnik.repositories.TeacherSubjectRepository;
import com.iktpreobuka.dnevnik.utils.RESTError;

@RestController
@RequestMapping("/api/v1/activities")
public class ActivityController {

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	private EmailService emailService = new EmailServiceImpl();

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private SemesterRepository semesterRepository;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private FileHandler fileHandler;

//	@Autowired
//	private TeacherSubjectRepository teacherSubjectRepository;

	// ============ ADD PARENT TO A STUDENT ================

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/student/{studentId}/parent/{parentId}")
	public ResponseEntity<?> addStudentParent(@PathVariable Integer studentId, @PathVariable Integer parentId) {
		if (studentRepository.existsById(studentId)) {
			if (parentRepository.existsById(parentId)) {
				StudentEntity student = studentRepository.findById(studentId).get();
				student.setParents(parentRepository.findById(parentId).get());
				studentRepository.save(student);
				return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);

			}
			return new ResponseEntity<RESTError>(new RESTError(4, "Student not found."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RESTError>(new RESTError(5, "Parent not found."), HttpStatus.NOT_FOUND);
	}

	// ==================== ADD STUDENT TO A CLASS =====================

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/student/{studentId}/classes/{classId}")
	public ResponseEntity<?> addStudentClass(@PathVariable Integer studentId, @PathVariable Integer classId) {
		if (studentRepository.existsById(studentId)) {
			if (classRepository.existsById(classId)) {
				StudentEntity student = studentRepository.findById(studentId).get();
				student.setClassEntity(classRepository.findById(classId).get());
				studentRepository.save(student);
				return new ResponseEntity<StudentEntity>(student, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(4, "Student not found."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RESTError>(new RESTError(5, "Class not found."), HttpStatus.NOT_FOUND);
	}

	// ================== ADD NEW GRADE TO A STUDENT BY TEACHER AND SUBJECT
	// =====================

	@Secured({ "ROLE_ADMIN", "ROLE_TEACHER" })
	@RequestMapping(method = RequestMethod.POST, value = "/semester/{semesterId}/subject/{subjectId}/student/{studentId}/teacher/{teacherId}/grades")
	public GradeEntity newGrade(@PathVariable("semesterId") Integer semesterId,
			@PathVariable("subjectId") Integer subjectId, @PathVariable("studentId") Integer studentId,
			@PathVariable("teacherId") Integer teacherId, @Valid @RequestBody GradeDTO gradeDTO) {

		Semester semester = semesterRepository.findById(semesterId).get();
		Subject subject = subjectRepository.findById(subjectId).get();
		StudentEntity student = studentRepository.findById(studentId).get();
		TeacherEntity teacher = teacherRepository.findById(teacherId).get();
		GradeEntity gradeEntity = new GradeEntity();

		gradeEntity.setSemester(semester);
		gradeEntity.setSubject(subject);
		gradeEntity.setStudent(student);
		gradeEntity.setTeacher(teacher);
		gradeEntity.setGradeType(gradeDTO.getGradeType());
		gradeEntity.setValue(gradeDTO.getValue());

		EmailObject object = new EmailObject();
		object.setTo(student.getParents().getEmail());
		object.setSubject(String.format("%s %s je dobio/la ocenu %s", student.getLastname(), student.getName(),
				gradeEntity.getValue()));
		object.setText(String.format("Vaše dete %s %s je dobilo ocenu %s kod profesora %s %s.", student.getName(),
				student.getLastname(), gradeEntity.getValue(), teacher.getName(), teacher.getLastname()));

		emailService.sendSimpleMessage(object);

		return gradeRepository.save(gradeEntity);

	}

// ====================== ADD SUBJECT TO TEACHER  ===================

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "teacherSubject/subject/{subjectId}/teacher/{teacherId}")
	public ResponseEntity<?> addSubjectTeacher(@PathVariable Integer subjectId, @PathVariable Integer teacherId) {
		if (subjectRepository.existsById(subjectId)) {
			if (teacherRepository.existsById(teacherId)) {
				Subject subject = subjectRepository.findById(subjectId).get();
				subject.setTeachers(teacherRepository.findById(teacherId).get());
				subjectRepository.save(subject);

				return new ResponseEntity<Subject>(subject, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError(4, "Subject not found."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RESTError>(new RESTError(5, "Teacher not found."), HttpStatus.NOT_FOUND);
	}

// ===================== ADD CLASSES TO TEACHER ========================

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/teacher/{teacherId}/class/{classId}/teacherClass")
	public ResponseEntity<?> addTeacherClass(@PathVariable Integer teacherId, @PathVariable Integer classId) {

		if (teacherRepository.existsById(teacherId)) {
			if (classRepository.existsById(classId)) {

				ClassEntity classes = classRepository.findById(classId).get();
				classes.setTeachers(teacherRepository.findById(teacherId).get());

				classRepository.save(classes);
				return new ResponseEntity<ClassEntity>(classes, HttpStatus.OK);

			}
			return new ResponseEntity<RESTError>(new RESTError(5, "Teacher not found."), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RESTError>(new RESTError(6, "Class not found."), HttpStatus.NOT_FOUND);
	}

	// ================ SEMESTER ====================

	@Secured({ "ROLE_ADMIN", "ROLE_TEACHER" })
	@RequestMapping(method = RequestMethod.GET, value = "/semester")
	public ResponseEntity<?> getAllSemesters() {
//		if(semesterRepository.findAll().equals(null)) {
		List<Semester> semester = new ArrayList<>();
		semester = (List<Semester>) semesterRepository.findAll();
		return new ResponseEntity<>(semester, HttpStatus.OK);
	}

///		return new ResponseEntity<RESTError>(new RESTError(1, "semesters don't exist"),
//			HttpStatus.NOT_FOUND);
//}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/semester")
	public Semester newSemester(@Valid @RequestBody SemesterDTO semesterDTO) {
		Semester semester = new Semester();
		semester.setName(semesterDTO.getName());
//		log.info("Semester added.");
		return semesterRepository.save(semester);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, value = "/semester/{semesterId}")
	public Semester updateSemester(@Valid @RequestBody SemesterDTO semesterDTO) {
		Semester semester = new Semester();
		semester.setName(semesterDTO.getName());

//		log.info("Semester updated.");
		return semesterRepository.save(semester);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, value = "/semester/{semesterId}")
	public void deleteSemester(@PathVariable("semesterId") Integer semesterId) {
//	log.info("Semester deleted.");
		semesterRepository.deleteById(semesterId);
	}

// ===================== SUBJECT===================

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, value = "/subjects")
	public ResponseEntity<?> getAllSubjects() {
//		if(semesterRepository.findAll().equals(null)) {
		List<Subject> subject = new ArrayList<>();
		subject = (List<Subject>) subjectRepository.findAll();
		return new ResponseEntity<>(subject, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, value = "/subjects/{subjectId}")
	public Subject updateSubject(@RequestBody SubjectDTO subjectDTO) {
		Subject subject = new Subject();
		subject.setWeeklyFond(subjectDTO.getWeeklyFond());
		subject.setName(subjectDTO.getName());

		logger.info("Semester updated.");
		return subjectRepository.save(subject);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, value = "/subjects/{subjectId}")
	public void deleteSubject(@PathVariable("subjectId") Integer subjectId) {
//log.info("Semester deleted.");
		subjectRepository.deleteById(subjectId);
	}

// ===================== GRADES =================

	// GET BY ID
	@Secured({ "ROLE_ADMIN", "ROLE_TEACHER" })
	@RequestMapping(method = RequestMethod.GET, value = "/grade/{gradeId}")
	public ResponseEntity<?> getById(@PathVariable Integer gradeId) {
		if (gradeRepository.existsById(gradeId)) {
			return new ResponseEntity<GradeEntity>(gradeRepository.findById(gradeId).get(), HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError(1, "Grade not found."), HttpStatus.NOT_FOUND);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_TEACHER" })
	@RequestMapping(method = RequestMethod.PUT, value = "/grade/{gradeId}")
	public GradeEntity updateGrade(@PathVariable Integer gradeId, @RequestBody GradeDTO newGrade) {
		GradeEntity grade = gradeRepository.findById(gradeId).get();

		grade.setValue(newGrade.getValue());

		logger.info("Grade updated.");
		return gradeRepository.save(grade);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_TEACHER" })
	@RequestMapping(method = RequestMethod.DELETE, value = "/grade/{gradeId}")
	public void deleteGrade(@PathVariable Integer gradeId) {

		gradeRepository.deleteById(gradeId);
		logger.info(gradeId + " : deleted.");
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/download")
	public ResponseEntity<Resource> downloadLogs() {
		try {
			File file = fileHandler.getLogs();

			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("content-disposition", "attachment; filename=" + "logs.txt");

			return ResponseEntity.ok().headers(responseHeaders).contentLength(file.length())
					.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
		} catch (IOException e) {
			e.getStackTrace();
		}
		return null;
	}

	public String createErrorMessage(BindingResult result) {
		// return
		// result.getAllErrors().stream().map(ObjectError::toString).collect(Collectors.joining(","));
		String errors = "";
		for (ObjectError error : result.getAllErrors()) {
			errors += error.getDefaultMessage();
			errors += "\n";
		}
		return errors;
	}

	
	// ============== CLASSES =====================
	
	@PostMapping("/classes")
	public ClassEntity createClass(@RequestBody ClassDTO classDTO) {
	//	Semester semester = semesterRepository.findById(semesterId).get();
		ClassEntity classEntity = new ClassEntity();
		classEntity.setClassNumber(classDTO.getClassNumber());
		return classRepository.save(classEntity);
	}
	
	
	@DeleteMapping("/studentClass/{studentClassId}")
	public void deleteStudentClass(@PathVariable("studentClassId") Integer studentClassId) {
		classRepository.deleteById(studentClassId);
	}

}
//	
//	
//@Secured({"ROLE_ADMIN", "ROLE_PARENT", "ROLE_STUDENT"})
//@GetMapping(value = "/student/{studentId}/teacherSubject/{teacherSubjectId}")
//public ResponseEntity<?> getGradeForStudentAndTeacherCourse(@PathVariable Integer studentId, @PathVariable Integer teacherSubjectId, 
//		HttpServletRequest request) {
//	if (studentRepository.existsById(studentId)) {
//		if (teacherSubjectRepository.existsById(teacherSubjectId)) {
//			
//			StudentEntity student = studentRepository.findById(studentId).get();
//			TeacherSubject teacherSubject = teacherSubjectRepository.findById(teacherSubjectId).get();
//			StudentTeacherSubject sts = studentTeacherSubjectRepository.findByStudentAndTeacherSubject(student, teacherSubject);
//			if (sts != null) {
//				List<GradeEntity> grades = ((List<GradeEntity>) gradeRepository.findByStudentTeacherSubject(sts)).stream()
//						.collect(Collectors.toList());
//				return new ResponseEntity<List<GradeEntity>>(grades, HttpStatus.OK);
//			}
//			return new ResponseEntity<RESTError>(new RESTError(15, "Student does not attend that course."), HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<RESTError>(new RESTError(11, "Teacher subject not found."), HttpStatus.NOT_FOUND);
//	}
//	return new ResponseEntity<RESTError>(new RESTError(5, "Student not found."), HttpStatus.NOT_FOUND);
//}

//// ================== GET ALL GRADES FOR ONE STUDENT ==================
//@Secured({ "ROLE_ADMIN", "ROLE_TEACHER" })
//@RequestMapping(method = RequestMethod.GET, value = "/semester/grades/{studentId}")
//public ResponseEntity<?> getGradesForStudent(@PathVariable Integer StudentId) {
//
//	GradeEntity grade = gradeRepository.findById(studentId).get();
//	List<GradeEntity> grades = new ArrayList<>();
//	grades = (List<GradeEntity>) gradeRepository.findGradesByStudentId();
//	return new ResponseEntity<>(semester, HttpStatus.OK);
//}
//}

//// ============= ADD SUBJECT TO TEACHER ================
//
//
//	@Secured("ROLE_ADMIN")
//	@RequestMapping(method = RequestMethod.POST, value = "/{teacherId}/subjects/{subjectId}")
//	public TeacherSubject addSubjectForTeacher(@PathVariable Integer teacherId, @PathVariable Integer subjectId) {
//				TeacherEntity teachers = teacherRepository.findById(teacherId).get();
//				Subject subject = subjectRepository.findById(subjectId).get();			
//					TeacherSubject teacherSubject = new TeacherSubject();					
//					teacherSubject.setTeachers(teacherRepository.findById(teacherId).get());
//					teacherSubject.setSubject(subjectRepository.findById(subjectId).get());
//					
//			return	teacherSubjectRepository.save(teacherSubject);
//	}
//}