package br.com.inspection.target;

import br.com.inspection.server.model.BaseVO;
import br.com.inspection.target.information.TargetInformationVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"id", "name", "informations", "createdAt", "updatedAt"})
public class TargetVO extends ResourceSupport implements BaseVO {

    @Mapping("id")
    @JsonProperty("id")
    private UUID key;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 50)
    private String name;

    @Valid
    private List<TargetInformationVO> informations;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
