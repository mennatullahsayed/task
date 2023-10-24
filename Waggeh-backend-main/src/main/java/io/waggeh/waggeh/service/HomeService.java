package io.waggeh.waggeh.service;

import io.waggeh.waggeh.constant.UserRole;
import io.waggeh.waggeh.domain.Field;
import io.waggeh.waggeh.domain.User;
import io.waggeh.waggeh.mappers.UserMapper;
import io.waggeh.waggeh.model.UserDTO;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.model.responses.home.CategoryDTO;
import io.waggeh.waggeh.repos.FieldRepository;
import io.waggeh.waggeh.repos.UserRepository;
import io.waggeh.waggeh.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class HomeService {

    private final FieldRepository fieldRepository;
    private final UserRepository consultantRepository;

    public HomeService(final FieldRepository fieldRepository, UserRepository consultantRepository) {
        this.fieldRepository = fieldRepository;
        this.consultantRepository = consultantRepository;
    }

    public ApiResponseWrapper<List<CategoryDTO>> getAllCategories() {
        final List<Field> fields = fieldRepository.findAll(Sort.by("id"));
        final List<CategoryDTO> categoryDTOS = new ArrayList<>();
        fields.forEach(field -> {
            final CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(field.getId());
            categoryDTO.setName(field.getName());
            categoryDTO.setDisplayName(field.getDisplayName());
            categoryDTO.setPhotoURL(field.getPhotoURL());
            categoryDTOS.add(categoryDTO);
        });
        return new ApiResponseWrapper<>(true, "Categories found", categoryDTOS);
    }

    public ApiResponseWrapper<Field> getById(final String id) {
          final Field field = fieldRepository.findById(id)
                 .orElseThrow(NotFoundException::new);
          return new ApiResponseWrapper<>(true, "Field found", field);
    }


    public ApiResponseWrapper<List<UserDTO>> getAllConsultantsByCategory(final String id) {
        final List<UserDTO> userDTOS = new ArrayList<>();
        List<Field> allFields = new ArrayList<>();
        if(id.isBlank()) {
            allFields = fieldRepository.findAll();
        }else {
            final Field field = fieldRepository.findById(id)
                    .orElseThrow(NotFoundException::new);
            allFields.add(field);
        }
        allFields.forEach(field -> {
            field.getUsers().forEach(user -> {
                if(user.getRole().equals(UserRole.ROLE_CONSULTANT)){
                    final UserDTO userDTO = new UserDTO();
                    userDTO.setId(user.getId());
                    userDTO.setFirstName(user.getFirstName());
                    userDTO.setLastName(user.getLastName());
                    userDTO.setPhotoUrl(user.getPhotoUrl());
                    userDTO.setBio(user.getBio());
                    userDTO.setTitle(user.getTitle());
                    userDTOS.add(userDTO);
                }
            });
        });
        return new ApiResponseWrapper<>(true, "Users found", userDTOS);
    }

    public ApiResponseWrapper<List<UserDTO>> getAllConsultantsByCategoryID(String categoryID,int type){
        Optional<Field> optionalField = fieldRepository.findById(categoryID);
        if(!optionalField.isPresent()){
            return new ApiResponseWrapper<>(false,"Category not exist!",null);
        }
        Field field = optionalField.get();
        List<User> consultants = new ArrayList<>();
        if(type!=0){
            consultants = consultantRepository.findFirst5ByRoleAndFieldId(UserRole.ROLE_CONSULTANT,field);
        }else {
            consultants = consultantRepository.findAllByRoleAndFieldId(UserRole.ROLE_CONSULTANT,field);
        }
        List<UserDTO> consultantDTOs = consultants.stream().map(user -> {
            return UserMapper.toDTO(user);
        }).toList();
        return new ApiResponseWrapper<>(true,"Successfully",consultantDTOs);
    }

    public ApiResponseWrapper<UserDTO> getConsultantById(String consultantId) {
        Optional<User> optionalUser = consultantRepository.findById(consultantId);
        if(!optionalUser.isPresent()){
            return new ApiResponseWrapper<>(false,"Consultant not exist!",null);
        }
        User user = optionalUser.get();
        UserDTO userDTO = UserMapper.toDTO(user);
        return new ApiResponseWrapper<>(true,"Successfully",userDTO);
    }

    public ApiResponseWrapper<List<UserDTO>> consultantSearch(String name) {
        List<User> allUsers = consultantRepository.findAllByFullNameContainingIgnoreCaseAndRole(name,UserRole.ROLE_CONSULTANT);

        List<UserDTO> allUsersDtos = allUsers.stream().map(UserMapper::toDTO).toList();
        return new ApiResponseWrapper<>(true,"Successfully",allUsersDtos);
    }
}
