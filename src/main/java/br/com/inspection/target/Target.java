package br.com.inspection.target;

import br.com.inspection.server.model.PhysicalBaseEntity;
import br.com.inspection.targetinformation.TargetInformation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "target")
public class Target extends PhysicalBaseEntity {

    @NotNull
    @Column(name = "name")
    @Size(max = 50)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "target_id", nullable = false)
    private List<TargetInformation> informations;

}
