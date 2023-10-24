package io.waggeh.waggeh.model.requests;

import io.waggeh.waggeh.constant.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
    private String firstName;
    private String photoUrl;
    private UserRole role;


}
