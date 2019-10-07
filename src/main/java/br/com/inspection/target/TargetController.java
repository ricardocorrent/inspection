package br.com.inspection.target;

import br.com.inspection.server.AbstractController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/target")
public class TargetController extends AbstractController<Target, TargetVO> {

    @Inject
    private TargetService targetService;

    @GetMapping(path = "/list-all/{name}")
    public ResponseEntity findTagByTitle(@PathVariable final String name,
                                         @RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "limit", defaultValue = "4") int limit,
                                         @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                         PagedResourcesAssembler assembler) {
        final Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        final Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));
        final Page<TargetVO> targetsByTitle = targetService.findTargetByTitle(name, pageable);
        targetsByTitle.stream().forEach(this::generateHateoas);
        return new ResponseEntity<>(assembler.toResource(targetsByTitle), HttpStatus.OK);
    }

    @Override
    public void generateHateoas(final TargetVO targetVO) {
        targetVO.add(linkTo(methodOn(TargetController.class).getEntityById(targetVO.getKey())).withSelfRel());
    }

    @Override
    protected String getListAllSortProperty() {
        return "name";
    }
}
