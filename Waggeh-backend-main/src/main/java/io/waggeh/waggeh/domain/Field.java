package io.waggeh.waggeh.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.Set;


@Document
@Getter
@Setter
public class Field {
    
    @Id
    private String id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String displayName;

    private String photoURL;

    @DocumentReference(lazy = true, lookup = "{ 'fieldId' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<Session> sessions;

    @DocumentReference(lazy = true, lookup = "{ 'fieldId' : ?#{#self._id} }")
    @ReadOnlyProperty
    @DBRef(lazy = true)
    @JsonBackReference
    private Set<User> users;


    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
