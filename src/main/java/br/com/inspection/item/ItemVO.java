package br.com.inspection.item;

import br.com.inspection.server.model.BaseVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"id", "code", "description", "parent", "children", "createdAt", "updatedAt"})
public class ItemVO extends ResourceSupport implements BaseVO {

    @Mapping("id")
    @JsonProperty("id")
    private UUID key;

    @NotNull
    @Column(name = "code")
    @Size(max = 50)
    private String code;

    @NotNull
    @Column(name = "description")
    @Size(max = 150)
    private String description;

    @Valid
    private ItemVO parent;

    @Valid
    //@JsonIgnoreProperties({"parent"})
    private List<ItemVO> children;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;
}
