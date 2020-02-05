package br.com.inspection.user;

import br.com.inspection.userinformation.UserInformationVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonPropertyOrder({"id", "userName", "fullName", "information", "createdAt", "updatedAt"})
public class UserVO extends ResourceSupport implements Serializable {

    @Mapping("id")
    @JsonProperty("id")
    private UUID key;

    private String userName;

    private String fullName;

    private List<UserInformationVO> information;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

}
