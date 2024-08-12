package com.example.uploadingfiles;

import java.io.IOException;

import com.example.uploadingfiles.storage.StorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FileUploadController {
    private final StorageService storageService;

    @Autowired
    public FileUploadController (StorageService storageService) {
        this.storageService = storageService;
    }
}
