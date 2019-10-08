package br.com.inspection.tag;

import br.com.inspection.rule.RuleVO;
import br.com.inspection.server.model.BaseVO;
import br.com.inspection.target.TargetVO;
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
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"id", "title", "targets", "createdAt", "updatedAt"})
public class TagVO extends ResourceSupport implements Serializable, BaseVO {

    @Mapping("id")
    @JsonProperty("id")
    private UUID key;

    @NotNull
    @NotBlank
    @NotEmpty
    private String title;

    @JsonIgnoreProperties({"tag", "tags", "informations", "createdAt", "updatedAt"})
    private List<TargetVO> targets;

    @JsonIgnoreProperties({"tag", "tags", "informations", "createdAt", "updatedAt"})
    private List<RuleVO> rules;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    public UUID getKey() {
        return key;
    }

    public void setKey(final UUID key) {
        this.key = key;
    }

}
