package br.com.inspection.tag;

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
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/tag")
public class TagController {

    private final TagService tagService;

    @Inject
    public TagController(final TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    private ResponseEntity<?> insert(@RequestBody final TagVO tagVO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tagService.insert(tagVO));
    }

    @GetMapping(path = "/{id}", produces = {"application/json", "application/xml"})
    private ResponseEntity<?> getTagById(@PathVariable final UUID id) {
        return ResponseEntity.ok(tagService.findById(id));
    }

    @PutMapping(path = "/{id}")
    private ResponseEntity<?> update(@PathVariable final UUID id, @RequestBody final TagVO tagVO) {
        tagVO.setId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tagService.update(tagVO));
    }

    @GetMapping(path = "/tags")
    private ResponseEntity<PagedResources<Resource<TagVO>>> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                 @RequestParam(value = "limit", defaultValue = "4") int limit,
                                                                 @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                                 PagedResourcesAssembler<TagVO> assembler) {
        final Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        final Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));

        return new ResponseEntity<>(assembler.toResource(tagService.list(pageable)), HttpStatus.OK);
    }

    @GetMapping(path = "/tags/{title}")
    private ResponseEntity<PagedResources<TagVO>> findTagByTitle(@PathVariable final String title,
                                                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                                                 @RequestParam(value = "limit", defaultValue = "4") int limit,
                                                                 @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                                 PagedResourcesAssembler assembler) {
        final Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        final Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));

        return new ResponseEntity<>(assembler.toResource(tagService.findTagByTitle(title, pageable)), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<?> delete(@PathVariable final UUID id) {
        this.tagService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
