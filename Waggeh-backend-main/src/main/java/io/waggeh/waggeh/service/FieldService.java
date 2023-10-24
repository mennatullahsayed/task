package io.waggeh.waggeh.service;

import io.waggeh.waggeh.domain.Field;
import io.waggeh.waggeh.model.FieldDTO;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.repos.FieldRepository;
import io.waggeh.waggeh.util.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FieldService {

    private final FieldRepository fieldRepository;

    public FieldService(final FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public ApiResponseWrapper<List<FieldDTO>> findAll() {
        final List<Field> fields = fieldRepository.findAll(Sort.by("id"));
        final List<FieldDTO> fieldDTOS =
            fields.stream()
                .map(field -> mapToDTO(field, new FieldDTO()))
                .toList();
        return new ApiResponseWrapper<>(true, "Fields found", fieldDTOS);
    }

    public ApiResponseWrapper<Field> get(final String id) {
          final Field field = fieldRepository.findById(id)
                 .orElseThrow(NotFoundException::new);
          return new ApiResponseWrapper<>(true, "Field found", field);
    }

    public ApiResponseWrapper<Field> create(final FieldDTO fieldDTO) {
        if (fieldRepository.existsByNameIgnoreCase(fieldDTO.getName())) {
            return new ApiResponseWrapper(false, "This Field already exist!");
        }
        final Field field = new Field();
        BeanUtils.copyProperties(fieldDTO,field);

        return new ApiResponseWrapper(true, "User registered successfully",fieldRepository.save(field));
    }

    public void update(final String id, final FieldDTO fieldDTO) {
        final Field field = fieldRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(fieldDTO, field);
        fieldRepository.save(field);
    }

    public void delete(final String id) {
        fieldRepository.deleteById(id);
    }

    private FieldDTO mapToDTO(final Field field, final FieldDTO fieldDTO) {
        fieldDTO.setId(field.getId());
        fieldDTO.setName(field.getName());
        fieldDTO.setDisplayName(field.getDisplayName());
        return fieldDTO;
    }

    private Field mapToEntity(final FieldDTO fieldDTO, final Field field) {
        field.setName(fieldDTO.getName());
        field.setDisplayName(fieldDTO.getDisplayName());
        return field;
    }

    public boolean idExists(final String id) {
        return fieldRepository.existsByIdIgnoreCase(id);
    }

}
