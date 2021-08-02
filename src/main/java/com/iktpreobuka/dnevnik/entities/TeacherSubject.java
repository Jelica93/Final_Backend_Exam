package com.iktpreobuka.dnevnik.entities;

//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//@Entity
//@Table(name = "teacherSubject")
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"
//})
//public class TeacherSubject {
//
//@Id
//@GeneratedValue
//private Integer id;
//
//@JsonIgnore
//@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//private Semester semester;
//
//@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//private StudentEntity student;
//
//@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//private TeacherEntity teacher;
//
//@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//private Subject subject;
//
//@OneToMany(mappedBy = "teacherSubject", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//@JsonBackReference
//private List<StudentTeacherSubject> studentTeacherSubject;
//
//public TeacherSubject() {
//	super();
//	// TODO Auto-generated constructor stub
//}
//
//public Integer getId() {
//	return id;
//}
//
//public void setId(Integer id) {
//	this.id = id;
//}
//
//public Semester getSemester() {
//	return semester;
//}
//
//public void setSemester(Semester semester) {
//	this.semester = semester;
//}
//
//public StudentEntity getStudent() {
//	return student;
//}
//
//public void setStudent(StudentEntity student) {
//	this.student = student;
//}
//
//public TeacherEntity getTeacher() {
//	return teacher;
//}
//
//public void setTeacher(TeacherEntity teacher) {
//	this.teacher = teacher;
//}
//
//public Subject getSubject() {
//	return subject;
//}
//
//public void setSubject(Subject subject) {
//	this.subject = subject;
//}
//
//public List<StudentTeacherSubject> getStudentTeacherSubject() {
//	return studentTeacherSubject;
//}
//
//public void setStudentTeacherSubject(List<StudentTeacherSubject> studentTeacherSubject) {
//	this.studentTeacherSubject = studentTeacherSubject;
//}

//	@JsonIgnore
//	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@JoinColumn(name = "teacherId")
//	private TeacherEntity teachers;
//	
//	@JsonIgnore
//	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@JoinColumn(name = "subjectId")
//	private Subject subject;
//	
//	@OneToMany(mappedBy = "teacherSubject")
//	private List<ClassEntity> classes = new ArrayList<>();
//
//	public TeacherSubject() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public TeacherEntity getTeachers() {
//		return teachers;
//	}
//
//	public void setTeachers(TeacherEntity teachers) {
//		this.teachers = teachers;
//	}
//
//	
//	public List<ClassEntity> getClasses() {
//		return classes;
//	}
//
//	public void setClasses(List<ClassEntity> classes) {
//		this.classes = classes;
//	}
//
//	public Subject getSubject() {
//		return subject;
//	}
//
//	public void setSubject(Subject subject) {
//		this.subject = subject;
//	}
//	
	
//
