package com.demo.api.entity;



import java.sql.Blob;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Image")
public class Image
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Lob
    private Blob image;
	
	private Date date;
    
	
	public Image(int id, Blob image, Date date) {
		super();
		this.id = id;
		this.image = image;
		this.date = new Date();
	}
	
	public Image() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = new Date();
	}
	
	
	


}
