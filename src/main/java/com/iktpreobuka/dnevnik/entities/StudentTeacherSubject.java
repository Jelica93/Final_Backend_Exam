package com.iktpreobuka.dnevnik.entities;

//import java.util.List;
//
//import javax.persistence.CascadeType;
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
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//@Entity
//@Table(name = "studentTeacherSubject")
//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//public class StudentTeacherSubject {
//	
//	@Id
//	@GeneratedValue
//	private Integer id;
//	
//	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@JoinColumn(name = "student")
//	private StudentEntity student;
//	
//	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@JoinColumn(name = "teacherSubject")
//	private TeacherSubject teacherSubject;
//
//	
//	@OneToMany(mappedBy = "studentTeacherSubject", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@JsonBackReference
//	private List<GradeEntity> grades;
//
//
//	public StudentTeacherSubject() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//
//	public Integer getId() {
//		return id;
//	}
//
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//
//	public StudentEntity getStudent() {
//		return student;
//	}
//
//
//	public void setStudent(StudentEntity student) {
//		this.student = student;
//	}
//
//
//	public TeacherSubject getTeacherSubject() {
//		return teacherSubject;
//	}
//
//
//	public void setTeacherSubject(TeacherSubject teacherSubject) {
//		this.teacherSubject = teacherSubject;
//	}
//
//
//	public List<GradeEntity> getGrades() {
//		return grades;
//	}
//
//
//	public void setGrades(List<GradeEntity> grades) {
//		this.grades = grades;
//	}
//
//	
//}
