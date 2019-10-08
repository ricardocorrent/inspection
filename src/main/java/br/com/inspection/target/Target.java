package br.com.inspection.target;

import br.com.inspection.server.model.PhysicalBaseEntity;
import br.com.inspection.tag.Tag;
import br.com.inspection.target.information.TargetInformation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "target")
public class Target extends PhysicalBaseEntity {

    @NotNull
    @Column(name = "name")
    @Size(max = 50)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "target_id", nullable = false)
    private List<TargetInformation> informations;

    @ManyToMany
    @JoinTable(name = "target_tag",
            joinColumns = @JoinColumn(name = "target_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnoreProperties({"target", "targets"})
    private Set<Tag> tags = new HashSet<>();

}
