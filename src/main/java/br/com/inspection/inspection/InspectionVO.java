package br.com.inspection.inspection;

import br.com.inspection.inspection.information.InspectionInformationVO;
import br.com.inspection.server.model.BaseVO;
import br.com.inspection.target.Target;
import br.com.inspection.target.TargetVO;
import br.com.inspection.user.User;
import br.com.inspection.user.UserVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"id", "title", "description", "draft", "syncQuantities", "note", "information", "user", "target"})
public class InspectionVO extends ResourceSupport implements BaseVO {

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

    private Boolean draft;

    private Integer syncQuantities;

    @Size(max = 4000)
    private String note;

    @Valid
    private List<InspectionInformationVO> information;

    @JsonIgnoreProperties({"createdAt", "updatedAt"})
    private UserVO user;

    @JsonIgnoreProperties({"tags", "createdAt", "updatedAt"})
    private TargetVO target;

}
