package io.waggeh.waggeh.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Document
@Getter
@Setter
@Builder
public class Image {
    @Id
    private String id;

    private String name;
    private String type;
    @NotNull
    private byte[] imageData;
}