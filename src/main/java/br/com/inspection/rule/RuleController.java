package br.com.inspection.rule;

import br.com.inspection.item.Item;
import br.com.inspection.item.ItemRepository;
import br.com.inspection.item.ItemService;
import br.com.inspection.item.ItemVO;
import br.com.inspection.server.AbstractController;
import br.com.inspection.server.adapter.DozerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/rule")
public class RuleController extends AbstractController<Rule, RuleVO> {

    @Autowired
    private RuleService ruleService;

    @Override
    public void generateHateoas(final RuleVO ruleVO) {
        ruleVO.add(linkTo(methodOn(RuleController.class).getEntityById(ruleVO.getKey())).withSelfRel());
    }

    public void generateHateoas(final RuleItemsVO ruleItemsVO) {
        ruleItemsVO.add(linkTo(methodOn(RuleController.class).getEntityById(ruleItemsVO.getKey())).withSelfRel());
    }

    @Override
    protected String getListAllSortProperty() {
        return "title";
    }

    @GetMapping(path = "/{id}/items")
    public ResponseEntity<?> getItemsListFromRule(@PathVariable("id") final UUID id) {
        final RuleItemsVO ruleItemsVO = ruleService.findItemsByRuleId(id);
        generateHateoas(ruleItemsVO);
        return ResponseEntity.ok(ruleItemsVO);
    }

}
