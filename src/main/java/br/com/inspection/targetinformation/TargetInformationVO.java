package br.com.inspection.targetinformation;

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
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"id", "title", "description", "createdAt", "updatedAt"})
public class TargetInformationVO extends ResourceSupport implements BaseVO {

    @Mapping("id")
    @JsonProperty("id")
    private UUID key;

    @NotNull
    @NotBlank
    @NotEmpty
    private String title;

    @NotNull
    @NotBlank
    @NotEmpty
    private String description;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
