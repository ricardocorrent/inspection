package br.com.inspection.targetinformation;

import br.com.inspection.persistence.model.BaseModel;
import br.com.inspection.target.Target;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "target_information")
public class TargetInformation implements BaseModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

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

}
