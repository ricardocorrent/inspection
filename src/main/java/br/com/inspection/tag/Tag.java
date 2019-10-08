package br.com.inspection.tag;

import br.com.inspection.rule.Rule;
import br.com.inspection.server.model.PhysicalBaseEntity;
import br.com.inspection.target.Target;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tag")
public class Tag extends PhysicalBaseEntity {

    @NotNull
    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnoreProperties({"tags", "informations", "createdAt", "updatedAt"})
    private Set<Target> targets = new HashSet<>();

    @ManyToMany(mappedBy = "tags")
    @JsonIgnoreProperties({"tags", "informations", "createdAt", "updatedAt"})
    private Set<Rule> rules = new HashSet<>();

}
