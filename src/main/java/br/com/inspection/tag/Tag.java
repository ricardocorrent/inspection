package br.com.inspection.tag;

import br.com.inspection.persistence.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "tag")
public class Tag implements BaseModel {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @NotNull
    private String title;

    @JsonIgnore
    private OffsetDateTime createdAt;

    @JsonIgnore
    private OffsetDateTime updatedAt;
}
