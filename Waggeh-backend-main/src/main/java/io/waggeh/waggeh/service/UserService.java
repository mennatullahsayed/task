package io.waggeh.waggeh.service;

import io.waggeh.waggeh.constant.UserRole;
import io.waggeh.waggeh.domain.Field;
import io.waggeh.waggeh.domain.User;
import io.waggeh.waggeh.mappers.FieldMapper;
import io.waggeh.waggeh.mappers.UserMapper;
import io.waggeh.waggeh.model.UserDTO;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.model.responses.home.CategoryDTO;
import io.waggeh.waggeh.repos.FieldRepository;
import io.waggeh.waggeh.repos.SessionRepository;
import io.waggeh.waggeh.repos.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final FieldRepository fieldRepository;

    public UserService(final UserRepository userRepository,
                       final SessionRepository sessionRepository, FieldRepository fieldRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.fieldRepository = fieldRepository;
    }

    public ApiResponseWrapper<List<UserDTO>> findAll() {
       try {
           final List<User> users = userRepository.findAll();
           final List<UserDTO> userDTOS = users.stream().map(UserMapper::toDTO).collect(Collectors.toList());
           return new ApiResponseWrapper<>(true, "Users found", userDTOS);
       }catch (Exception e) {
           return new ApiResponseWrapper<>(false, e.getMessage(), null);
       }

    }

    public ApiResponseWrapper<UserDTO> findCurrentUserInfo() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(!authentication.isAuthenticated()){
                return new ApiResponseWrapper<UserDTO>(false, "Token is not valid!", null);
            }
            String userEmail = authentication.getName();
            final Optional<User> optionalUser = userRepository.findUserByEmail(userEmail);
            if(!optionalUser.isPresent()) {
                return new ApiResponseWrapper<UserDTO>(false, "User not found", null);
            }else {
                User user = optionalUser.get();
                Optional<Field> optionalField = fieldRepository.findById(user.getFieldId().getId());
                if(optionalField.isPresent()){
                    user.setFieldId(optionalField.get());
                }
                final UserDTO userDTO = UserMapper.toDTO(user);
                return new ApiResponseWrapper<>(true, "User found successfully", userDTO);
            }
        } catch (Exception e) {
            return new ApiResponseWrapper<>(false, e.getMessage(), null);
        }

    }

    public ApiResponseWrapper<UserDTO> updateUserInfo(UserDTO userDTO) {
        try {

            UserDTO returnUserDTO = new UserDTO();

            final Optional<User> optionalUser = userRepository.findById(userDTO.getId());

            if (!optionalUser.isPresent()) {
                return new ApiResponseWrapper<>(false, "User not found", null);
            } else {
                User currentUser = optionalUser.get();
                currentUser.setFirstName(userDTO.getFirstName());
                currentUser.setLastName(userDTO.getLastName());
                currentUser.setCompany(userDTO.getCompany());
                currentUser.setPhone(userDTO.getPhone());
                currentUser.setGender(userDTO.getGender());
                currentUser.setBusinessType(userDTO.getBusinessType());
                currentUser.setNationality(userDTO.getNationality());
                currentUser.setLinkedInUrl(userDTO.getLinkedInUrl());
                currentUser.setBio(userDTO.getBio());
                currentUser.setPhotoUrl(userDTO.getPhotoUrl());
                currentUser.setBusinessType(userDTO.getBusinessType());
//                currentUser.setFieldId(userDTO.getFieldId());
                if (userDTO.getRole().equals(UserRole.ROLE_CONSULTANT)){
                    currentUser.setTitle(userDTO.getTitle());
                    currentUser.setExperience(userDTO.getExperience());
                }


                User updatedUser = userRepository.save(currentUser);
                BeanUtils.copyProperties(updatedUser, returnUserDTO);

                return new ApiResponseWrapper<>(true, "User Updated", returnUserDTO);
            }
        } catch (Exception e) {
            return new ApiResponseWrapper<>(false, e.getMessage(), null);
        }
    }




}
