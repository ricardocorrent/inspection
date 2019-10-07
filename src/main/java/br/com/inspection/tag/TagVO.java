package br.com.inspection.tag;

import br.com.inspection.server.model.BaseVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"id", "title", "createdAt", "updatedAt"})
public class TagVO extends ResourceSupport implements Serializable, BaseVO {

    @Mapping("id")
    @JsonProperty("id")
    private UUID key;

    @NotNull
    @NotBlank
    @NotEmpty
    private String title;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    public UUID getKey() {
        return key;
    }

    public void setKey(final UUID key) {
        this.key = key;
    }

}
