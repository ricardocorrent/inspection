package br.com.inspection.tag;

import br.com.inspection.server.model.PhysicalBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tag")
public class Tag extends PhysicalBaseEntity {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @NotNull
    @Column(name = "title")
    private String title;

    @Override
    public void prePersist() {
        super.prePersist();
    }

    @Override
    public void preUpdate() {
        super.preUpdate();
    }

}
