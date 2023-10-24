package io.waggeh.waggeh.mappers;

import io.waggeh.waggeh.domain.Field;;
import io.waggeh.waggeh.model.responses.home.CategoryDTO;
import org.springframework.beans.BeanUtils;

public class FieldMapper {
    public static Field toEntity(CategoryDTO fieldDTO) {
        final Field field = new Field();
        BeanUtils.copyProperties(fieldDTO,field);
        return field;
    }

    public static CategoryDTO toDTO(Field field) {
        final CategoryDTO fieldDTO = new CategoryDTO();
        BeanUtils.copyProperties(field,fieldDTO);
        return fieldDTO;
    }
}
