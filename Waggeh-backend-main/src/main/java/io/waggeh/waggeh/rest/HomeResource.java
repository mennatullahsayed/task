package io.waggeh.waggeh.rest;

import io.waggeh.waggeh.model.FieldDTO;
import io.waggeh.waggeh.model.UserDTO;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.model.responses.home.CategoryDTO;
import io.waggeh.waggeh.service.FieldService;
import io.waggeh.waggeh.service.HomeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/home", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HomeResource {
    private final HomeService homeService;

    public HomeResource(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/categories/get-all")
    public ResponseEntity<ApiResponseWrapper<List<CategoryDTO>>> getAllFields() {
        return ResponseEntity.ok(homeService.getAllCategories());
    }

    @GetMapping("/consultants/get-all")
    public ResponseEntity<ApiResponseWrapper<List<UserDTO>>> getAllConsultants(@RequestParam("categoryId") String categoryId) {
        ApiResponseWrapper<List<UserDTO>> responseWrapper = homeService.getAllConsultantsByCategory(categoryId);
        return ResponseEntity.ok(responseWrapper);
    }
    @GetMapping("/consultants/get-all/type")
    public ResponseEntity<ApiResponseWrapper<List<UserDTO>>> getAllConsultantsType(@RequestParam("categoryId") String categoryId,@RequestParam("type") Integer type) {
        ApiResponseWrapper<List<UserDTO>> responseWrapper = homeService.getAllConsultantsByCategoryID(categoryId,type);
        return ResponseEntity.ok(responseWrapper);
    }

    @GetMapping("/consultants/info")
    public ResponseEntity<ApiResponseWrapper<UserDTO>> getConsultantInfo(@RequestParam("consultantId") String consultantId) {
        ApiResponseWrapper<UserDTO> responseWrapper = homeService.getConsultantById(consultantId);
        return ResponseEntity.ok(responseWrapper);
    }
    @GetMapping("/consultants/search")
    public ResponseEntity<ApiResponseWrapper<List<UserDTO>>> consultantSearch(@RequestParam("name") String name) {
        ApiResponseWrapper<List<UserDTO>> responseWrapper = homeService.consultantSearch(name);
        return ResponseEntity.ok(responseWrapper);
    }

}
