package io.waggeh.waggeh.rest;

import io.waggeh.waggeh.domain.Field;
import io.waggeh.waggeh.model.FieldDTO;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.service.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/fields", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FieldResource {

    private final FieldService fieldService;

    public FieldResource(final FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseWrapper<List<FieldDTO>>> getAllFields() {
        return ResponseEntity.ok(fieldService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseWrapper<Field>> getField(@PathVariable(name = "id") final String id) {
        return ResponseEntity.ok(fieldService.get(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponseWrapper<Field>> createField(@RequestBody @Valid final FieldDTO fieldDTO) {
        final ApiResponseWrapper<Field> fieldApiResponseWrapper = fieldService.create(fieldDTO);
        return new ResponseEntity<>(fieldApiResponseWrapper, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable(name = "id") final String id) {
        fieldService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
