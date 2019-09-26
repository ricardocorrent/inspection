package br.com.inspection.user;

import br.com.inspection.userinformation.UserInformationVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({"id", "userName", "fullName", "informations", "createdAt", "updatedAt"})
public class UserVO extends ResourceSupport implements Serializable {

    @Mapping("id")
    @JsonProperty("id")
    private UUID key;

    private String userName;

    private String fullName;

    private List<UserInformationVO> informations;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    public UUID getKey() {
        return key;
    }

    public void setKey(final UUID key) {
        this.key = key;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public List<UserInformationVO> getInformations() {
        return informations;
    }

    public void setInformations(final List<UserInformationVO> informations) {
        this.informations = informations;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(final OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
