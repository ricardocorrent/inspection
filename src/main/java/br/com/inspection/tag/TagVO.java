package br.com.inspection.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@JsonPropertyOrder({"id", "title", "createdAt", "updatedAt"})
public class TagVO extends ResourceSupport implements Serializable {

    @Mapping("id")
    @JsonProperty("id")
    private UUID key;

    @NotNull
    @NotBlank
    @NotEmpty
    private String title;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    public UUID getKey() {
        return key;
    }

    public void setKey(final UUID key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof TagVO)) return false;
        if (!super.equals(o)) return false;
        final TagVO tagVO = (TagVO) o;
        return getKey().equals(tagVO.getKey()) &&
                getTitle().equals(tagVO.getTitle()) &&
                getCreatedAt().equals(tagVO.getCreatedAt()) &&
                getUpdatedAt().equals(tagVO.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey(), getTitle(), getCreatedAt(), getUpdatedAt());
    }
}
