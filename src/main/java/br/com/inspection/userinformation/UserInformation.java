package br.com.inspection.userinformation;

import br.com.inspection.server.model.PhysicalBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "user_information")
public class UserInformation extends PhysicalBaseEntity {

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "title")
    @Size(max = 100)
    private String title;

    @NotNull
    @NotEmpty
    @NotBlank
    @Column(name = "description")
    @Size(max = 150)
    private String description;

}
