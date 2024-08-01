package com.polus.service.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDto {

	private String fileName;
	private String downloadURL;
	private String fileType;
	private long fileSize;
}
