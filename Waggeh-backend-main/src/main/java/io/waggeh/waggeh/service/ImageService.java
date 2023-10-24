package io.waggeh.waggeh.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.waggeh.waggeh.domain.Image;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.repos.StorageRepository;
import io.waggeh.waggeh.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final StorageRepository repository;
    private final Cloudinary cloudinary;



    public ApiResponseWrapper<byte[]> downloadImage(String fileId){
        Optional<Image> dbImageData = repository.findById(fileId);
        byte[] images =ImageUtils.decompressImage(dbImageData.get().getImageData());
        ApiResponseWrapper<byte[]> response = new ApiResponseWrapper<>(true,"success",images);
        return response;
    }
   public ApiResponseWrapper<String> uploadImage(MultipartFile imageFile) throws IOException {
       Image imageData = repository.save(Image.builder()
               .name(imageFile.getOriginalFilename())
               .type(imageFile.getContentType())
               .imageData(ImageUtils.compressImage(imageFile.getBytes())).build());
       if (Objects.nonNull(imageData)) {
           return new ApiResponseWrapper<>(true,"success",imageData.getId());
       }
       return new ApiResponseWrapper<>(false,"failure",null);
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
