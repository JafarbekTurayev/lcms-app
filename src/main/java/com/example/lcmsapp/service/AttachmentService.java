package com.example.lcmsapp.service;

import com.example.lcmsapp.dto.ApiResponse;
import com.example.lcmsapp.entity.Attachment;
import com.example.lcmsapp.entity.AttachmentContent;
import com.example.lcmsapp.exception.ResourceNotFoundException;
import com.example.lcmsapp.repository.AttachmentContentRepository;
import com.example.lcmsapp.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;
    private final Path root = Paths.get("C:\\PDP Lesson\\Backend G7\\lcms-app\\src\\main\\resources\\upload");

    @SneakyThrows
    public ApiResponse uploadFileSystem(MultipartHttpServletRequest request) {
//        List<MultipartFile> ketmon = request.getFiles("file");
//        MultipartFile file = request.getFile("file");

        Iterator<String> fileNames = request.getFileNames();
        Attachment save = null;
        while (fileNames.hasNext()) {
            Attachment attachment = new Attachment();
            MultipartFile file = request.getFile(fileNames.next());
            attachment.setFileName(file.getOriginalFilename());
            attachment.setContentType(file.getContentType());
            attachment.setSize(file.getSize());

            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));

            attachment.setUrl(this.root + file.getOriginalFilename());
            save = attachmentRepository.save(attachment);
        }
        return ApiResponse.builder().success(true).message(save.getFileName() + " nomli fayl saqlandi").build();
    }

    @SneakyThrows
    public ApiResponse uploadDB(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        while (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            if (!file.isEmpty() || file != null) {
                //info
                Attachment attachment = new Attachment();
                attachment.setSize(file.getSize());
                attachment.setContentType(file.getContentType());
                attachment.setFileName(file.getOriginalFilename());
                Attachment save = attachmentRepository.save(attachment);


                //bu o'zi
                AttachmentContent content = new AttachmentContent();
                content.setAttachment(save);
                content.setBytes(file.getBytes());
                attachmentContentRepository.save(content);
            }
        }

        return ApiResponse.builder().success(true).message("Chotki").build();
    }

    public ResponseEntity<?> downloadDB(UUID id) {
        //info
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("file", "id", id));

        AttachmentContent byAttachment = attachmentContentRepository.findByAttachment(attachment);
        AttachmentContent byAttachment_id = attachmentContentRepository.findByAttachment_Id(id);


        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(byAttachment.getBytes());
    }
}
