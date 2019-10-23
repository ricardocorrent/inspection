package br.com.inspection.rule;

import br.com.inspection.item.ItemVO;
import br.com.inspection.rule.information.RuleInformationVO;
import br.com.inspection.server.model.BaseVO;
import br.com.inspection.tag.TagVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonPropertyOrder({"id", "title", "description", "informations", "items", "createdAt", "updatedAt"})
public class RuleVO extends ResourceSupport implements BaseVO {

    @Mapping("id")
    @JsonProperty("id")
    private UUID key;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 100)
    private String title;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 150)
    private String description;

    @Valid
    private List<RuleInformationVO> informations;

    @Valid
    @JsonIgnoreProperties({"target", "targets"})
    private List<TagVO> tags;

    @Valid
    @JsonIgnoreProperties({"item", "items", "parent"})
    private List<ItemVO> items;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
