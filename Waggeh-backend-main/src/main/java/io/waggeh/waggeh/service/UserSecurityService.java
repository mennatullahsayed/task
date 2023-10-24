package io.waggeh.waggeh.service;


import io.waggeh.waggeh.domain.Field;
import io.waggeh.waggeh.domain.User;
import io.waggeh.waggeh.model.UserDTO;
import io.waggeh.waggeh.model.requests.SignUpRequest;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.model.responses.UserDTOResponse;
import io.waggeh.waggeh.repos.FieldRepository;
import io.waggeh.waggeh.repos.SessionRepository;
import io.waggeh.waggeh.repos.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;


    private final FieldRepository fieldRepository;

    public UserSecurityService(UserRepository userRepository, SessionRepository sessionRepository, FieldRepository fieldRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.fieldRepository = fieldRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("email Not found" + email);
        }
        User user = optionalUser.get();
        return user;
    }

    public ApiResponseWrapper<UserDTOResponse> signUp(final SignUpRequest userDTO, PasswordEncoder passwordEncoder) {
        userDTO.setEmail(userDTO.getEmail().toLowerCase());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return new ApiResponseWrapper(false, "User Address already in use!");
        }
        Field field;
        if(Objects.isNull(userDTO.getField())){
            field = fieldRepository.findAll().get(0);
        }else {
            Optional<Field> fieldOptional = fieldRepository.findById(userDTO.getField());
            if(!fieldOptional.isPresent()) {
                return new ApiResponseWrapper(false, "Field not found!");
            }
            field = fieldOptional.get();
        }

        final User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setFieldId(field);
        user.setFullName(userDTO.getFirstName().concat(" ").concat(userDTO.getLastName()));
        userRepository.save(user);
        UserDTOResponse userDTOResponse = new UserDTOResponse();
        BeanUtils.copyProperties(user,userDTOResponse);
        userDTOResponse.setFieldId(user.getFieldId().getId());
        return new ApiResponseWrapper(true, "User registered successfully",userDTOResponse);
    }


    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
