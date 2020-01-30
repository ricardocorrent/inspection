package br.com.inspection.item;

import br.com.inspection.rule.RuleVO;
import br.com.inspection.server.model.BaseVO;
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
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"id", "code", "description"})
public class ItemVO extends ResourceSupport implements BaseVO {

    @Mapping("id")
    @JsonProperty("id")
    private UUID key;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 100)
    private String code;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(max = 250)
    private String description;

    @NotNull
    @JsonIgnoreProperties({"title", "description", "information", "tags", "createdAt", "updatedAt"})
    private RuleVO rule;
}
