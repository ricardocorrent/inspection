package br.com.inspection.item.vo;

import br.com.inspection.item.information.ItemInformationVO;
import br.com.inspection.server.model.BaseVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"id", "code", "description", "information"})
public class ItemsFromRuleIdResponseVO extends ResourceSupport implements BaseVO {

    @Mapping("id")
    @JsonProperty("id")
    private UUID key;

    private String code;

    private String description;

    private List<ItemInformationVO> information;

}
