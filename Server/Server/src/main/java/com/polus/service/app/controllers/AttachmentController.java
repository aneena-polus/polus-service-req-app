package com.polus.service.app.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.polus.service.app.entities.Attachment;
import com.polus.service.app.services.AttachmentService;

@RestController
public class AttachmentController {

	Logger logger = org.apache.logging.log4j.LogManager.getLogger(AttachmentController.class);

	@Autowired
	AttachmentService service;

	@PostMapping("/upload")
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
		Attachment attachment = null;
		String downloadURL = "";
		try {
			attachment = service.saveAttachment(file);
		} catch (Exception e) {
			logger.error("Error in uploading file: " + e.getMessage());
		}
		downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(attachment.getId())
				.toUriString();
		Map<String, Object> response = new HashMap<>();
		response.put("fileName", attachment.getFileName());
		response.put("downloadURL", downloadURL);
		response.put("fileType", file.getContentType());
		response.put("size", file.getSize());
		return ResponseEntity.ok(response);
	}

	@GetMapping("/download/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId) {

		Attachment attachment = null;
		try {
			attachment = service.getAttachment(fileId);
		} catch (Exception e) {
			logger.error("Error in downloading file: " + e.getMessage());
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(attachment.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
				.body(new ByteArrayResource(attachment.getData()));
	}
}
