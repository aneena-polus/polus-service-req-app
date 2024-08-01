package com.polus.service.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.polus.service.app.entities.Attachment;
import com.polus.service.app.repository.AttachmentRepo;

@Service
public class AttachmentService {

	@Autowired
	AttachmentRepo repo;

	public Attachment saveAttachment(MultipartFile file) throws Exception {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new Exception("Filename contains invalid path" + fileName);
			}
			Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());
			return repo.save(attachment);
		} catch (Exception e) {
			throw new Exception("Could not save file: " + fileName);
		}
	}

	public Attachment getAttachment(String fileId) throws Exception {

		return repo.findById(fileId)
				.orElseThrow(() -> new Exception("File not found: " + fileId));
	}

}
