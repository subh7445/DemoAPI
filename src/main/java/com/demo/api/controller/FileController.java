package com.demo.api.controller;

import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonObject;
import com.demo.api.Service.FileDataService;
import com.demo.api.entity.FileData;
import com.demo.api.repository.FileDataRepository;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class FileController {
	@Autowired
	private Cluster couchBaseCluster;

	@Autowired
	private FileDataService fileDataService;

	@Autowired
	private FileDataRepository fileDataRepository;

	private final String bucketName = "CustomBucket";

	@PostMapping("/couch/files")
	public String addImage(@RequestParam("files") MultipartFile file) {
		// connect to bucket
		Bucket bucket = couchBaseCluster.bucket(bucketName);
		Collection collection = bucket.defaultCollection();

		try {
			byte[] fileContent = file.getBytes();

			String base64EncodedFile = Base64.getEncoder().encodeToString(fileContent);
			JsonObject content = JsonObject.create().put("file", base64EncodedFile);

			// Store file in Couchbase

			// ByteBuffer buf = ByteBuffer.wrap("file", fileContent);
			collection.upsert("2", content);

			return "File Uploaded Successfully";

		} catch (Exception e) {
			return "Error Uploading File" + e.getMessage();

		}
	}

	@GetMapping("/couch/files/{id}")
	public ResponseEntity<?> getFile(@PathVariable String id) {
		Bucket bucket = couchBaseCluster.bucket("CustomBucket");
		Collection collection = bucket.defaultCollection();

		try {
			// retrive the data through id
			Object obj = collection.get(id).contentAsObject().get("file");

			if (obj instanceof String) {
				String base64EncodedFile = (String) obj;
				byte[] fileContent = Base64.getDecoder().decode(base64EncodedFile);

				 HttpHeaders headers = new HttpHeaders();
		         headers.setContentType(MediaType.IMAGE_JPEG); // Adjust based on the file type

		            return ResponseEntity.ok()
		                    .headers(headers)
		                    .body(fileContent);
//				return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileId=" + id)
//						.body(fileContent);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving file content");
			}
		} catch (Exception e) {
			return ResponseEntity.notFound().build();

		}

	}
    //this is for second method store image in file and store its name/path in database
	
	@PostMapping("/uploadImage")
	public String handleImageUpload(@RequestParam("files") MultipartFile file) {

		try {
			fileDataService.uploadImageToFileSystem(file);
			return "Image is Successfully Uploaded";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error Occur here";
		}

	}

	@GetMapping("/getImage/{id}")
	public String getImage(@PathVariable int id, HttpServletRequest request) {

		Optional<FileData> file = fileDataRepository.findById(id);

		if (!file.isEmpty()) {
			// StringBuffer requestURL = request.getRequestURL();

			String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
//	       
			return baseUrl + "/" + file.get().getName();

		} else {

			return "error occur";

		}

	}

}
