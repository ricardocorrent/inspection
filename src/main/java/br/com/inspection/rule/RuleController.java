package br.com.inspection.rule;

import br.com.inspection.server.AbstractController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/rule")
public class RuleController extends AbstractController<Rule, RuleVO> {

    private RuleService ruleService;

    @Inject
    public RuleController(final RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @Override
    public void generateHateoas(final RuleVO ruleVO) {
        ruleVO.add(linkTo(methodOn(RuleController.class).getEntityById(ruleVO.getKey())).withSelfRel());
    }

    @Override
    protected String getListAllSortProperty() {
        return "title";
    }

    @PutMapping(path = "/updating/{id}")
    public ResponseEntity<RuleVO> update(final UUID id, final @Valid RuleRequestVO ruleRequestVO) {
        ruleRequestVO.setKey(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ruleService.update(ruleRequestVO));
    }

    @Override
    public ResponseEntity<?> update(final UUID id, final @Valid RuleVO ruleVO) {
        return super.update(id, ruleVO);
    }
}
