package com.polus.service.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polus.service.app.entities.Attachment;

@Repository
public interface AttachmentRepo extends JpaRepository<Attachment, String>{
}
