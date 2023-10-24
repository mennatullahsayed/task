package io.waggeh.waggeh.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.waggeh.waggeh.domain.Field;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.model.responses.home.CategoryDTO;
import io.waggeh.waggeh.repos.FieldRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Service
public class DashboardService {

    private final FieldRepository fieldRepository;
    private final Cloudinary cloudinary;

    public DashboardService(final FieldRepository fieldRepository, Cloudinary cloudinary) {
        this.fieldRepository = fieldRepository;
        this.cloudinary = cloudinary;
    }

    public ApiResponseWrapper<Field> createCategory(final CategoryDTO categoryDTO) {
        if (fieldRepository.existsByNameIgnoreCase(categoryDTO.getName())) {
            return new ApiResponseWrapper(false, "This Category already exist!");
        }
        final Field field = new Field();
        BeanUtils.copyProperties(categoryDTO,field);

        return new ApiResponseWrapper(true, "Category registered successfully",fieldRepository.save(field));
    }


    public ApiResponseWrapper<String> uploadImageOnCloud(MultipartFile imageFile) throws IOException {
        String imageURL = cloudinary.uploader()
                .upload(imageFile.getBytes(), ObjectUtils.asMap("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();
        if (!imageURL.isBlank()) {
            return new ApiResponseWrapper<>(true,"success",imageURL);
        }
        return new ApiResponseWrapper<>(false,"failure",null);
    }

}
