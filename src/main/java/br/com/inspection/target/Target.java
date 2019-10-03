package br.com.inspection.target;


import br.com.inspection.server.model.BaseModel;
import br.com.inspection.targetinformation.TargetInformation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "target")
public class Target implements BaseModel {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @NotNull
    @Column(name = "name")
    @Size(max = 50)
    private String name;

    @Column(name = "createdAt")
    private OffsetDateTime createdAt;

    @Column(name = "updatedAt")
    private OffsetDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "target_id", nullable = false)
    private List<TargetInformation> informations;

}
