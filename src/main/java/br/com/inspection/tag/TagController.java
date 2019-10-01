package br.com.inspection.tag;

import br.com.inspection.server.AbstractController;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/tag")
public class TagController extends AbstractController<Tag, TagVO> {

    @Inject
    private TagService tagService;

    @Override
    public void generateHateoas(final TagVO tagVO) {
        tagVO.add(linkTo(methodOn(TagController.class).getEntityById(tagVO.getKey())).withSelfRel());
    }

    @GetMapping(path = "/tags")
    public ResponseEntity<PagedResources<Resource<TagVO>>> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                @RequestParam(value = "limit", defaultValue = "4") int limit,
                                                                @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                                PagedResourcesAssembler<TagVO> assembler) {
        final Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        final Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));
        final Page<TagVO> listOfTags = tagService.list(pageable);
        listOfTags.stream().forEach(this::generateHateoas);

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
        tagsByTitle.stream().forEach(this::generateHateoas);

        return new ResponseEntity<>(assembler.toResource(tagsByTitle), HttpStatus.OK);
    }

}
