package com.example.lcmsapp.service;

import com.example.lcmsapp.dto.ApiResponse;
import com.example.lcmsapp.entity.Attachment;
import com.example.lcmsapp.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
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
}
