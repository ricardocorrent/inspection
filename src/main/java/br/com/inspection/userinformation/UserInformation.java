package br.com.inspection.userinformation;

import br.com.inspection.persistence.model.PhysicalBaseEntity;
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
@Table(name = "user_information")
public class UserInformation extends PhysicalBaseEntity {

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

    @Override
    public void prePersist() {
        super.prePersist();
    }

    @Override
    public void preUpdate() {
        super.preUpdate();
    }
}
