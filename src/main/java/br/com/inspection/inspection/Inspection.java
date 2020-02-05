package br.com.inspection.inspection;

import br.com.inspection.inspection.information.InspectionInformation;
import br.com.inspection.server.model.PhysicalBaseEntity;
import br.com.inspection.target.Target;
import br.com.inspection.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "inspection")
public class Inspection extends PhysicalBaseEntity {

    @NotNull
    @Column(name = "title")
    @Size(max = 100)
    private String title;

    @NotNull
    @Column(name = "description")
    @Size(max = 150)
    private String description;

    private Boolean draft;

    @Column(name = "sync_quantities")
    private Integer syncQuantities;

    @Size(max = 4000)
    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "target_id", nullable = false)
    private Target target;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "inspection_id", nullable = false)
    private List<InspectionInformation> information;
}
