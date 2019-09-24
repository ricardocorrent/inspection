package br.com.inspection.userinformation;

import br.com.inspection.persistence.model.BaseModel;
import br.com.inspection.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user_information")
public class UserInformation implements BaseModel {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "title")
    private String title;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "description")
    private String description;

    @Column(name = "createdAt")
    private OffsetDateTime createdAt;

    @Column(name = "updatedAt")
    private OffsetDateTime updatedAt;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(final UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(final OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setUpdatedAt(final OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInformation)) return false;
        final UserInformation that = (UserInformation) o;
        return getId().equals(that.getId()) &&
                getTitle().equals(that.getTitle()) &&
                getDescription().equals(that.getDescription()) &&
                getCreatedAt().equals(that.getCreatedAt()) &&
                getUpdatedAt().equals(that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getCreatedAt(), getUpdatedAt());
    }
}
