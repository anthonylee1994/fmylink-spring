package com.spring.fmylinkspring.photos.controllers;

import com.spring.fmylinkspring.photos.responses.PhotoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author anthonylee
 */
@RequestMapping("/photos")
@RestController
public class PhotosController {
    PhotoService photoService;

    PhotosController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping()
    public ResponseEntity<PhotoResponse> create(@RequestParam("image") MultipartFile image) throws IOException {
        var response = new PhotoResponse();
        response.url = photoService.url(image);

        return ResponseEntity.ok(response);
    }
}
