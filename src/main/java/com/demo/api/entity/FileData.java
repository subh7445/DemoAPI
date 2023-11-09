package com.demo.api.entity;

import jakarta.persistence.*;
import lombok.Builder;


@Entity
@Table(name="fileData")
@Builder
public class FileData {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String type;
	@Column(nullable = false)
	private String filePath;
	public FileData(int id, String name, String type, String filePath) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.filePath = filePath;
	}
	public FileData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	

}
