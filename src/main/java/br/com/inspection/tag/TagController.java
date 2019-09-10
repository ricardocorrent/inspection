package br.com.inspection.tag;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
        TagVO tagVO = tagService.findById(id);
        tagVO.add(linkTo(methodOn(TagController.class).getTagById(id)).withSelfRel());
        return ResponseEntity.ok(tagVO);
    }

    @PutMapping(path = "/{id}")
    private ResponseEntity<?> update(@PathVariable final UUID id, @RequestBody final TagVO tagVO) {
        tagVO.setKey(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tagService.update(tagVO));
    }

    @GetMapping(path = "/tags")
    private ResponseEntity<?> list() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tagService.list());
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<?> delete(@PathVariable final UUID id) {
        this.tagService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
