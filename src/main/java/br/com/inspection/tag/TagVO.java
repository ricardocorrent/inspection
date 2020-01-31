package br.com.inspection.tag;

import br.com.inspection.rule.RuleVO;
import br.com.inspection.server.model.BaseVO;
import br.com.inspection.target.TargetVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"id", "title", "targets", "rules"})
public class TagVO extends ResourceSupport implements BaseVO {

    @Mapping("id")
    @JsonProperty("id")
    private UUID key;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 50)
    private String title;

    @JsonIgnoreProperties({"information", "tags", "createdAt", "updatedAt"})
    private List<TargetVO> targets;

    @JsonIgnoreProperties({"tag", "tags", "information", "createdAt", "updatedAt"})
    private List<RuleVO> rules;

    @JsonIgnore
    private OffsetDateTime createdAt;

    @JsonIgnore
    private OffsetDateTime updatedAt;


}
