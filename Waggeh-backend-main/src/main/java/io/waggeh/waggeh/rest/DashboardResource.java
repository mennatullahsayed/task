package io.waggeh.waggeh.rest;

import io.waggeh.waggeh.domain.Field;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.model.responses.home.CategoryDTO;
import io.waggeh.waggeh.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/dash-board", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DashboardResource {
    private final DashboardService dashboardService;

    public DashboardResource(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @PostMapping("/category/add")
    public ResponseEntity<ApiResponseWrapper<Field>> createField(@RequestBody @Valid final CategoryDTO categoryDTO) {
        final ApiResponseWrapper<Field> fieldApiResponseWrapper = dashboardService.createCategory(categoryDTO);
        return new ResponseEntity<>(fieldApiResponseWrapper, HttpStatus.CREATED);
    }

    @PostMapping("/image/upload")
    public ResponseEntity<ApiResponseWrapper<String>> uploadImage(@Valid @RequestBody MultipartFile imageFile) throws IOException {
        ApiResponseWrapper<String> response = dashboardService.uploadImageOnCloud(imageFile);
        return ResponseEntity.ok(response);

    }

}
