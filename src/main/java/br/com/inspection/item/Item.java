package br.com.inspection.item;

import br.com.inspection.server.model.PhysicalBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item extends PhysicalBaseEntity {

    @NotNull
    @Column(name = "code")
    @Size(max = 50)
    private String code;

    @NotNull
    @Column(name = "description")
    @Size(max = 150)
    private String description;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Item parent;

    @OneToMany(mappedBy="parent", cascade={CascadeType.ALL})
    private Set<Item> children;

}
