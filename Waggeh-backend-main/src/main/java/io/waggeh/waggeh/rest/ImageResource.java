package io.waggeh.waggeh.rest;

import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageResource {
    private final ImageService imageService;

    public ImageResource(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponseWrapper<String>> uploadImage(@Valid @RequestBody MultipartFile imageFile) throws IOException {
        ApiResponseWrapper<String> response = imageService.uploadImageOnCloud(imageFile);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/download")
    public ResponseEntity<ApiResponseWrapper<byte[]>> uploadImage(@Valid @RequestParam("photoURL") String photoURL) {
        ApiResponseWrapper<byte[]> response = imageService.downloadImage(photoURL);
        return ResponseEntity.ok(response);

    }
}
