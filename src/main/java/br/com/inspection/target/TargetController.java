package br.com.inspection.target;

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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/target")
public class TargetController {

    @Inject
    private TargetService targetService;

    @PostMapping
    private ResponseEntity<?> insert(@Valid @RequestBody final TargetVO targetVO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(targetService.insert(targetVO));
    }

    @GetMapping(path = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getTargetById(@PathVariable("id") final UUID id) {
        final TargetVO targetVO = targetService.getById(id);

        targetVO.add(linkTo(methodOn(TargetController.class).getTargetById(id)).withSelfRel());

        return ResponseEntity.ok(targetVO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable final UUID id, @Valid @RequestBody final TargetVO targetVO) {
        targetVO.setKey(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(targetService.update(targetVO));
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<?> delete(@PathVariable final UUID id) {
        this.targetService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping(path = "/targets")
    public ResponseEntity<PagedResources<Resource<TargetVO>>> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "limit", defaultValue = "4") int limit,
                                                                @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                                PagedResourcesAssembler<TargetVO> assembler) {
        final Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        final Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));
        final Page<TargetVO> listOfTargets = targetService.list(pageable);
        listOfTargets.stream()
                .forEach(targetVO -> targetVO.add(linkTo(methodOn(TargetController.class).getTargetById(targetVO.getKey())).withSelfRel()));

        return new ResponseEntity<>(assembler.toResource(listOfTargets), HttpStatus.OK);
    }

    @GetMapping(path = "/targets/{title}")
    public ResponseEntity findTagByTitle(@PathVariable final String title,
                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "limit", defaultValue = "4") int limit,
                                         @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                         PagedResourcesAssembler assembler) {
        final Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        final Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));
        final Page<TargetVO> targetsByTitle = targetService.findTargetByTitle(title, pageable);
        targetsByTitle.stream()
                .forEach(targetVO -> targetVO.add(linkTo(methodOn(TargetController.class).getTargetById(targetVO.getKey())).withSelfRel()));
        return new ResponseEntity<>(assembler.toResource(targetsByTitle), HttpStatus.OK);
    }
}
