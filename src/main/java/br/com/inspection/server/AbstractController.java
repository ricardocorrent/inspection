package br.com.inspection.server;

import br.com.inspection.persistence.model.BaseModel;
import br.com.inspection.persistence.model.BaseVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.UUID;

public abstract class AbstractController<T extends BaseModel, Z extends BaseVO> {

    @Inject
    private AbstractService<T, Z> abstractService;

    @PostMapping
    private ResponseEntity<?> insert(@Valid @RequestBody final Z z) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(abstractService.insert(z));
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<?> delete(@PathVariable final UUID id) {
        this.abstractService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping(path = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getEntityById(@PathVariable("id") final UUID id) {
        final Z z = abstractService.getById(id);
        this.generateHateoas(z);
        return ResponseEntity.ok(z);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable final UUID id, @Valid @RequestBody final Z z) {
        z.setKey(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(abstractService.update(z));
    }

    public abstract void generateHateoas(final Z z);

}
