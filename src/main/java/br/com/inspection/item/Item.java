package br.com.inspection.item;

import br.com.inspection.rule.Rule;
import br.com.inspection.server.model.PhysicalBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item extends PhysicalBaseEntity {

    @NotNull
    @Column(name = "code")
    @Size(max = 100)
    private String code;

    @NotNull
    @Column(name = "description")
    @Size(max = 250)
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "rule_id", nullable = false)
    private Rule rule;
}
