package br.com.inspection.server;

import br.com.inspection.server.model.BaseModel;
import br.com.inspection.server.model.BaseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.UUID;

/**
 * @param <T> Entity
 * @param <Z> EntityVo
 */
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

    @GetMapping(path = "/list-all")
    public ResponseEntity<PagedResources<Resource<Z>>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                               @RequestParam(value = "limit", defaultValue = "4") int limit,
                                                               @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                               PagedResourcesAssembler<Z> assembler) {
        final Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        final Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, getListAllSortProperty()));
        final Page<Z> listOfEntities = abstractService.list(pageable);
        listOfEntities.stream().forEach(this::generateHateoas);

        return new ResponseEntity<>(assembler.toResource(listOfEntities), HttpStatus.OK);
    }

    public abstract void generateHateoas(final Z z);

    protected abstract String getListAllSortProperty();

}
