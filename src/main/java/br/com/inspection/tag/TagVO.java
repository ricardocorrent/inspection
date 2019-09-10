package br.com.inspection.tag;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"key", "title"})
public class TagVO extends ResourceSupport implements Serializable {

    @Mapping("id")
    private UUID key;

    private String title;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;
}
