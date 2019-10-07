package br.com.inspection.tag;

import br.com.inspection.server.model.PhysicalBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "tag")
public class Tag extends PhysicalBaseEntity {

    @NotNull
    @Column(name = "title")
    private String title;

}
