package com.iktpreobuka.dnevnik.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dnevnik.entities.AdminEntity;
import com.iktpreobuka.dnevnik.entities.ParentEntity;
import com.iktpreobuka.dnevnik.entities.StudentEntity;
import com.iktpreobuka.dnevnik.entities.TeacherEntity;
import com.iktpreobuka.dnevnik.entities.UserEntity;
import com.iktpreobuka.dnevnik.entities.UserValidator;
import com.iktpreobuka.dnevnik.entities.dto.UserSecondDTO;
import com.iktpreobuka.dnevnik.repositories.AdminRepository;
import com.iktpreobuka.dnevnik.repositories.ParentRepository;
import com.iktpreobuka.dnevnik.repositories.RoleRepository;
import com.iktpreobuka.dnevnik.repositories.StudentRepository;
import com.iktpreobuka.dnevnik.repositories.TeacherRepository;
import com.iktpreobuka.dnevnik.repositories.UserRepository;
import com.iktpreobuka.dnevnik.utils.RESTError;


@RestController
@RequestMapping("/api/v1/users")
public class UserSecondController {
//
//	
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	UserValidator userValidator;

	// ============= ADD USER ================

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, value = "/newuser")
	public ResponseEntity<?> newUser(@Valid @RequestBody UserSecondDTO userSecondDTO, @RequestParam Integer roleId,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		} else {
			userValidator.validate(userSecondDTO, result);
		}
		UserEntity u = new UserEntity();
		u.setUsername(userSecondDTO.getUsername());

		switch (roleId) {
		case 1:
			u.setRole(roleRepository.findById(1).get());
			AdminEntity admin = new AdminEntity();
			admin.setUsername(userSecondDTO.getUsername());
			admin.setPassword(userSecondDTO.getPassword());
			admin.setConfirmPassword(userSecondDTO.getConfirmPassword());
			admin.setName(userSecondDTO.getName());
			admin.setLastname(userSecondDTO.getLastname());
			admin.setEmail(userSecondDTO.getEmail());
			admin.setJmbg(userSecondDTO.getJmbg());
			admin.setRole(roleRepository.findById(1).get());
			userRepository.save(admin);
			break;
		case 2:
			u.setRole(roleRepository.findById(2).get());
			TeacherEntity teacher = new TeacherEntity();
			teacher.setUsername(userSecondDTO.getUsername());
			teacher.setPassword(userSecondDTO.getPassword());
			teacher.setConfirmPassword(userSecondDTO.getConfirmPassword());
			teacher.setName(userSecondDTO.getName());
			teacher.setLastname(userSecondDTO.getLastname());
			teacher.setEmail(userSecondDTO.getEmail());
			teacher.setJmbg(userSecondDTO.getJmbg());
			teacher.setRole(roleRepository.findById(2).get());
			userRepository.save(teacher);

			break;
		case 3:
			u.setRole(roleRepository.findById(3).get());
			ParentEntity parent = new ParentEntity();
			parent.setUsername(userSecondDTO.getUsername());
			parent.setPassword(userSecondDTO.getPassword());
			parent.setConfirmPassword(userSecondDTO.getConfirmPassword());
			parent.setName(userSecondDTO.getName());
			parent.setLastname(userSecondDTO.getLastname());
			parent.setEmail(userSecondDTO.getEmail());
			parent.setJmbg(userSecondDTO.getJmbg());
			parent.setRole(roleRepository.findById(3).get());
			userRepository.save(parent);
			break;
		case 4:
			u.setRole(roleRepository.findById(4).get());
			StudentEntity student = new StudentEntity();
			student.setUsername(userSecondDTO.getUsername());
			student.setPassword(userSecondDTO.getPassword());
			student.setConfirmPassword(userSecondDTO.getConfirmPassword());
			student.setName(userSecondDTO.getName());
			student.setLastname(userSecondDTO.getLastname());
			student.setEmail(userSecondDTO.getEmail());
			student.setJmbg(userSecondDTO.getJmbg());
			student.setRole(roleRepository.findById(4).get());
			userRepository.save(student);
			break;

		}
		logger.info("New user added");
		return new ResponseEntity<>(u, HttpStatus.OK);
	}

	// =========== ADMINS =================

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, value = "/admins")
	public ResponseEntity<?> getAllAdmins() {

		List<AdminEntity> admin = new ArrayList<>();
		admin = (List<AdminEntity>) adminRepository.findAll();
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	// FIND ADMIN BY ID ------ ISPRAVKA**** BY NAME
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, value = "/admins/{adminId}")
	public ResponseEntity<?> findAdminById(@PathVariable Integer adminId) {
		if (adminRepository.findById(adminId).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(1, "doesn't exist"), HttpStatus.NOT_FOUND);
		}
		AdminEntity admin = adminRepository.findById(adminId).get();
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	// UPDATE ADMIN BY ID
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, value = "/admins/{adminId}")
	public ResponseEntity<?> updateAdmin(@Valid @RequestBody UserSecondDTO admin, @PathVariable Integer adminId,
			BindingResult result) {
		if (adminRepository.findById(adminId).isPresent() == false) {
			if (result.hasErrors()) {
				return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
			}
			AdminEntity updateAdmin = adminRepository.findById(adminId).get();
			updateAdmin.setName(admin.getName());
			updateAdmin.setLastname(admin.getLastname());
			updateAdmin.setUsername(admin.getUsername());
			updateAdmin.setPassword(admin.getPassword());
			updateAdmin.setEmail(admin.getEmail());
			updateAdmin.setJmbg(admin.getJmbg());
			return new ResponseEntity<AdminEntity>(adminRepository.save(updateAdmin), HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError(1, "Admin not found."), HttpStatus.NOT_FOUND);
	}

	// DELETE BY ID
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, value = "/admins/{adminId}")
	public ResponseEntity<?> deleteAdmin(@PathVariable Integer adminId) {
		if (adminRepository.findById(adminId).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "admin with entered id doesn't exist"),
					HttpStatus.NOT_FOUND);

		}
		adminRepository.deleteById(adminId);
		return new ResponseEntity<AdminEntity>(HttpStatus.OK);
	}

	// =========== TEACHERS =================

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, value = "/teachers")
	public ResponseEntity<?> getAllTeachers() {

		List<TeacherEntity> teacher = new ArrayList<>();
		teacher = (List<TeacherEntity>) teacherRepository.findAll();
		return new ResponseEntity<>(teacher, HttpStatus.OK);
	}

	// FIND TEACHER BY ID
	@Secured({ "ROLE_ADMIN", "ROLE_TEACHER" })
	@RequestMapping(method = RequestMethod.GET, value = "/teachers/{teacherId}")
	public ResponseEntity<?> findTeacherById(@PathVariable Integer teacherId) {
		if (teacherRepository.findById(teacherId).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(1, "doesn't exist"), HttpStatus.NOT_FOUND);
		}
		TeacherEntity teacher = teacherRepository.findById(teacherId).get();
		return new ResponseEntity<>(teacher, HttpStatus.OK);
	}

	// UPDATE TEACHER BY ID
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, value = "/teachers/{teacherId}")
	public ResponseEntity<?> updateTeacher(@Valid @RequestBody UserSecondDTO teacher, @PathVariable Integer teacherId,
			BindingResult result) {
		if (teacherRepository.findById(teacherId).isPresent() == false) {
			if (result.hasErrors()) {
				return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
			}
			TeacherEntity updateTeacher = teacherRepository.findById(teacherId).get();
			updateTeacher.setName(teacher.getName());
			updateTeacher.setLastname(teacher.getLastname());
			updateTeacher.setUsername(teacher.getUsername());
			updateTeacher.setPassword(teacher.getPassword());
			updateTeacher.setEmail(teacher.getEmail());
			updateTeacher.setJmbg(teacher.getJmbg());
			return new ResponseEntity<TeacherEntity>(teacherRepository.save(updateTeacher), HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError(2, "teacher with entered id doesn't exist"),
				HttpStatus.NOT_FOUND);
	}

	// DELETE BY ID
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, value = "/teachers/{teacherId}")
	public ResponseEntity<?> deleteTeacher(@PathVariable Integer teacherId) {
		if (teacherRepository.findById(teacherId).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "teacher with entered id doesn't exist"),
					HttpStatus.NOT_FOUND);

		}
		teacherRepository.deleteById(teacherId);
		return new ResponseEntity<TeacherEntity>(HttpStatus.OK);
	}

	// ================== STUDENTS =================

	@Secured({ "ROLE_ADMIN", "ROLE_TEACHER" })
	@RequestMapping(method = RequestMethod.GET, value = "/students")
	public ResponseEntity<?> getAllStudents() {

		List<StudentEntity> student = new ArrayList<>();
		student = (List<StudentEntity>) studentRepository.findAll();
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	// FIND STUDENT BY ID
	
	@Secured({ "ROLE_ADMIN", "ROLE_TEACHER" })
	@RequestMapping(method = RequestMethod.GET, value = "/students/{studentId}")
	public ResponseEntity<?> findStudentById(@PathVariable Integer studentId) {
		if (studentRepository.findById(studentId).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(1, "doesn't exist"), HttpStatus.NOT_FOUND);
		}
		StudentEntity student = studentRepository.findById(studentId).get();
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	// UPDATE STUDENT BY ID
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, value = "/students/{studentId}")
	public ResponseEntity<?> updateStudent(@Valid @RequestBody UserSecondDTO student, @PathVariable Integer studentId,
			BindingResult result) {
		if (studentRepository.findById(studentId).isPresent() == false) {
			if (result.hasErrors()) {
				return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
			}

			StudentEntity updateStudent = studentRepository.findById(studentId).get();
			updateStudent.setName(student.getName());
			updateStudent.setLastname(student.getLastname());
			updateStudent.setUsername(student.getUsername());
			updateStudent.setPassword(student.getPassword());
			updateStudent.setEmail(student.getEmail());
			updateStudent.setJmbg(student.getJmbg());
			return new ResponseEntity<StudentEntity>(studentRepository.save(updateStudent), HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError(1, "Student not found."), HttpStatus.NOT_FOUND);
	}

	// DELETE BY ID

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, value = "/students/{studentId}")
	public ResponseEntity<?> deleteStudent(@PathVariable Integer studentId) {
		if (studentRepository.findById(studentId).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "student with entered id doesn't exist"),
					HttpStatus.NOT_FOUND);
		}
		studentRepository.deleteById(studentId);
		return new ResponseEntity<TeacherEntity>(HttpStatus.OK);
	}

	// ================ PARENTS =================

	@RequestMapping(method = RequestMethod.GET, value = "/parents")
	public ResponseEntity<?> getAllParents() {

		List<ParentEntity> parent = new ArrayList<>();
		parent = (List<ParentEntity>) parentRepository.findAll();
		return new ResponseEntity<>(parent, HttpStatus.OK);
	}

	// FIND PARENT BY ID

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, value = "/parents/{parentId}")
	public ResponseEntity<?> findParentById(@PathVariable Integer parentId) {
		if (parentRepository.findById(parentId).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(1, "doesn't exist"), HttpStatus.NOT_FOUND);
		}
		ParentEntity parent = parentRepository.findById(parentId).get();
		return new ResponseEntity<>(parent, HttpStatus.OK);
	}

	// UPDATE PARENT BY ID

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, value = "/parents/{parentId}")
	public ResponseEntity<?> updateParent(@RequestBody UserSecondDTO parent, @PathVariable Integer parentId,
			BindingResult result) {
		if (parentRepository.findById(parentId).isPresent() == false) {
			if (result.hasErrors()) {
				return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
			}
			ParentEntity updateParent = parentRepository.findById(parentId).get();
			updateParent.setName(parent.getName());
			updateParent.setLastname(parent.getLastname());
			updateParent.setUsername(parent.getUsername());
			updateParent.setPassword(parent.getPassword());
			updateParent.setEmail(parent.getEmail());
			updateParent.setJmbg(parent.getJmbg());
			return new ResponseEntity<ParentEntity>(parentRepository.save(updateParent), HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError(1, "Parent not found."), HttpStatus.NOT_FOUND);
	}

	
	
	// DELETE BY ID

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.DELETE, value = "/parents/{parentId}")
	public ResponseEntity<?> deleteParent(@PathVariable Integer parentId) {
		if (parentRepository.findById(parentId).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "parent with entered id doesn't exist"),
					HttpStatus.NOT_FOUND);

		}
		parentRepository.deleteById(parentId);
		return new ResponseEntity<ParentEntity>(HttpStatus.OK);
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
}
