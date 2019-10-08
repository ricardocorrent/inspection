package br.com.inspection.rule;

import br.com.inspection.item.Item;
import br.com.inspection.rule.information.RuleInformation;
import br.com.inspection.server.model.PhysicalBaseEntity;
import br.com.inspection.tag.Tag;
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

    @ManyToMany
    @JoinTable(name = "rule_tag",
            joinColumns = @JoinColumn(name = "rule_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnoreProperties({"rule", "rules"})
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "rule_id", nullable = false)
    private Set<Item> items;

}
