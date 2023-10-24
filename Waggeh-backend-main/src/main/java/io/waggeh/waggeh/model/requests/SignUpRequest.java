package io.waggeh.waggeh.model.requests;

import io.waggeh.waggeh.constant.BusinessType;
import io.waggeh.waggeh.constant.UserRole;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @Size(max = 255)
    private String id;

    @NotNull
    @Size(max = 255)
    private String email;
    @NotNull
    @Size(max = 255)
    private String username;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    @Size(max = 255)
    private String gender;

    @Size(max = 255)
    private String password;

    @Size(max = 255)
    private String phone;

    @Size(max = 255)
    private String nationality;

    @Size(max = 255)
    private String brithDate;

    @Size(max = 255)
    private String photoUrl;

    @Size(max = 255)
    private String company;

    @Size(max = 255)
    private String title;

    @Size(max = 1080)
    private String bio;

    @Size(max = 255)
    private String linkedInUrl;

    private Integer experience;

    @NotNull
    private UserRole role;

    private String field;

    private BusinessType businessType;

}
