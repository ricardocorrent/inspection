package br.com.inspection.tag;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"id", "title"})
public class TagVO implements Serializable {
    private UUID id;

    private String title;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;
}
