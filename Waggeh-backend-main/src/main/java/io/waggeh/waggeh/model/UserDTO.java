package io.waggeh.waggeh.model;


import io.waggeh.waggeh.constant.BusinessType;
import io.waggeh.waggeh.constant.UserRole;
import io.waggeh.waggeh.domain.Field;
import io.waggeh.waggeh.model.responses.home.CategoryDTO;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @Size(max = 255)
    private String id;

    @NotNull
    @Size(max = 255)
    private String email;

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
    private String birthDate;

    @Size(max = 255)
    private String photoUrl;

    @Size(max = 255)
    private String company;

    @Size(max = 255)
    private String title;

    @Size(max = 255)
    private String bio;

    @Size(max = 255)
    private String linkedInUrl;

    private Integer experience;

    @NotNull
    private UserRole role;

    private CategoryDTO fieldId;

    private List<SessionDTO> sessions;
    private List<ScheduleSlotDTO> scheduleSlots;

    private BusinessType businessType;
}
