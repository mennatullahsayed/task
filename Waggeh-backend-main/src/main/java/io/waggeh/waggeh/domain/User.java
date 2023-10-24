package io.waggeh.waggeh.domain;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.waggeh.waggeh.constant.BusinessType;
import io.waggeh.waggeh.constant.UserRole;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    @Size(max = 255)
    private String fullName;


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

    @JsonManagedReference
    @DocumentReference(lazy = true, lookup = "{ 'userId' : ?#{#self._id} }")
    @ReadOnlyProperty
    private List<ScheduleSlot> scheduleSlots;

    @DocumentReference(lazy = true)
    private List<Session> sessions;


    @JsonProperty( value = "fieldId", access = JsonProperty.Access.WRITE_ONLY)
    @SerializedName("fieldId")
    @DocumentReference(lazy = true)
    @JsonManagedReference
    private Field fieldId;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

    private BusinessType businessType;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
