package br.com.inspection.rule;
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
@RequestMapping(value = "/api/rule")
public class RuleController extends AbstractController<Rule, RuleVO> {

    @Inject
    private RuleService ruleService;

    @GetMapping(path = "/rules")
    public ResponseEntity<PagedResources<Resource<RuleVO>>> list(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                   @RequestParam(value = "limit", defaultValue = "4") int limit,
                                                                   @RequestParam(value = "direction", defaultValue = "asc") String direction,
                                                                   PagedResourcesAssembler<RuleVO> assembler) {
        final Sort.Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        final Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "title"));
        final Page<RuleVO> listOfRules = ruleService.list(pageable);
        listOfRules.stream().forEach(this::generateHateoas);

        return new ResponseEntity<>(assembler.toResource(listOfRules), HttpStatus.OK);
    }

    @Override
    public void generateHateoas(final RuleVO ruleVO) {
        ruleVO.add(linkTo(methodOn(RuleController.class).getEntityById(ruleVO.getKey())).withSelfRel());
    }

}
