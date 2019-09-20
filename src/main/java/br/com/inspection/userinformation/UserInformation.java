package br.com.inspection.userinformation;

import br.com.inspection.persistence.model.BaseModel;
import br.com.inspection.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_information")
public class UserInformation implements BaseModel {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
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

}
