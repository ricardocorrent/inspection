package br.com.inspection.rule.information;

import br.com.inspection.server.model.PhysicalBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "rule_information")
public class RuleInformation extends PhysicalBaseEntity {

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

}