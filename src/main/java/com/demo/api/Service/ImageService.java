package com.demo.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.api.entity.Image;
import com.demo.api.repository.ImageRepository;

@Service
public class ImageService {
	@Autowired
	private ImageRepository imageRepository;
	
	public Image createImage(Image image) {
		return imageRepository.save(image);
	}
	


}
