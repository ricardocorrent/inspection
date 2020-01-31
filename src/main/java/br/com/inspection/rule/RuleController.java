package br.com.inspection.rule;

import br.com.inspection.server.AbstractController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/rule")
public class RuleController extends AbstractController<Rule, RuleVO> {

    @Override
    public void generateHateoas(final RuleVO ruleVO) {
        ruleVO.add(linkTo(methodOn(RuleController.class).getEntityById(ruleVO.getKey())).withSelfRel());
    }

    @Override
    protected String getListAllSortProperty() {
        return "title";
    }

}
