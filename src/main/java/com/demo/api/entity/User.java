package com.demo.api.entity;

import jakarta.persistence.*;




@Entity
@Table(name="student")
public class User {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String Name;
	@Column(nullable=false)
	private Long Age;
	@Column(nullable=false)
	private String Department;
	@Column(nullable=false, unique=true)
	private String Email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Long getAge() {
		return Age;
	}
	public void setAge(Long age) {
		Age = age;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public User(Long id, String name, Long age, String department, String email) {
		super();
		this.id = id;
		Name = name;
		Age = age;
		Department = department;
		Email = email;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
