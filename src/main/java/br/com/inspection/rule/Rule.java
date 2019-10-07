package br.com.inspection.rule;

import br.com.inspection.rule.information.RuleInformation;
import br.com.inspection.server.model.PhysicalBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rule")
public class Rule extends PhysicalBaseEntity {

    @NotNull
    @Column(name = "title")
    @Size(max = 100)
    private String title;

    @NotNull
    @Column(name = "description")
    @Size(max = 150)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "rule_id", nullable = false)
    private List<RuleInformation> informations;

}
