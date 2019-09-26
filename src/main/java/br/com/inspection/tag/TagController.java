package br.com.inspection.tag;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/api/tag")
public class TagController {

    @Inject
    private TagService tagService;

    @PostMapping
    private ResponseEntity<?> insert(@Valid @RequestBody final TagVO tagVO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tagService.insert(tagVO));
    }

    @GetMapping(path = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getTagById(@PathVariable("id") final UUID id) {
        final TagVO tagVO = tagService.getById(id);

        tagVO.add(linkTo(methodOn(TagController.class).getTagById(id)).withSelfRel());

        return ResponseEntity.ok(tagVO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable final UUID id, @Valid @RequestBody final TagVO tagVO) {
        tagVO.setKey(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tagService.update(tagVO));
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<?> delete(@PathVariable final UUID id) {
        this.tagService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping(path = "/tags")
    public ResponseEntity<PagedResources<Resource<TagVO>>> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "limit", defaultValue = "4") int limit,
                                                                @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                                PagedResourcesAssembler<TagVO> assembler) {
        final Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        final Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));
        final Page<TagVO> listOfTags = tagService.list(pageable);
        listOfTags.stream()
                .forEach(tagVO -> tagVO.add(linkTo(methodOn(TagController.class).getTagById(tagVO.getKey())).withSelfRel()));

        return new ResponseEntity<>(assembler.toResource(listOfTags), HttpStatus.OK);
    }

    @GetMapping(path = "/tags/{title}")
    public ResponseEntity findTagByTitle(@PathVariable final String title,
                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "limit", defaultValue = "4") int limit,
                                         @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                         PagedResourcesAssembler assembler) {
        final Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        final Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));
        final Page<TagVO> tagsByTitle = tagService.findTagByTitle(title, pageable);
        tagsByTitle.stream()
                .forEach(tagVO -> tagVO.add(linkTo(methodOn(TagController.class).getTagById(tagVO.getKey())).withSelfRel()));
        return new ResponseEntity<>(assembler.toResource(tagsByTitle), HttpStatus.OK);
    }

}
