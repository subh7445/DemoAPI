package com.demo.api.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.api.entity.FileData;
import com.demo.api.repository.FileDataRepository;

@Service
public class FileDataService {
	@Autowired
	private FileDataRepository fileDataRepository;

	public String uploadImageToFileSystem(MultipartFile file) throws Exception {
		byte[] fileBytes = file.getBytes();
		String projectRoot = System.getProperty("user.dir");

		// Define the base upload directory relative to the project directory
		String baseUploadDirectory = projectRoot + File.separator + "src/main/resources/static/Images";
		File baseUploadPath = new File(baseUploadDirectory);

		if (!baseUploadPath.exists()) {
			baseUploadPath.mkdirs(); // create directory if it doesn't exist
		}
		String fileName = UUID.randomUUID().toString() + "." + file.getContentType().split("/")[1];
		File serverFile = new File(baseUploadPath, fileName);

		try (FileOutputStream stream = new FileOutputStream(serverFile)) {
			stream.write(fileBytes);
		}

		FileData image = new FileData();
		image.setName(fileName);
		image.setFilePath(baseUploadPath.getAbsolutePath() + File.separator + fileName);
		image.setType(file.getContentType());

		fileDataRepository.save(image);
		return "Image is Uploaded";
	}

}
