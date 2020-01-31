package br.com.inspection.item;

import br.com.inspection.item.vo.ItemsFromRuleIdResponseVO;
import br.com.inspection.item.vo.RuleToItemsRsponseVO;
import br.com.inspection.rule.RuleController;
import br.com.inspection.server.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/item")
public class ItemController extends AbstractController<Item, ItemVO> {

    @Autowired
    private ItemService itemService;

    public ItemController(final ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(path = "/items/{ruleId}")
    public ResponseEntity<?> getItemsListFromRuleId(@PathVariable("ruleId") final UUID ruleId) {
        final RuleToItemsRsponseVO ruleToItemsRsponseVO = itemService.getItemsByRuleId(ruleId);
        generateHateoas(ruleToItemsRsponseVO);
        generateItemsHateoas(ruleToItemsRsponseVO.getItems());
        return ResponseEntity.ok(ruleToItemsRsponseVO);
    }

    private void generateHateoas(final RuleToItemsRsponseVO ruleToItemsRsponseVO) {
        ruleToItemsRsponseVO.add(linkTo(methodOn(RuleController.class).getEntityById(ruleToItemsRsponseVO.getKey())).withSelfRel());
    }

    private void generateItemsHateoas(final List<ItemsFromRuleIdResponseVO> items) {
        items.forEach(itemsFromRuleIdResponseVO -> {
            itemsFromRuleIdResponseVO.add(
                    linkTo(methodOn(ItemController.class).getEntityById(itemsFromRuleIdResponseVO.getKey()))
                            .withSelfRel()
            );
        });
    }

    @Override
    public void generateHateoas(final ItemVO itemVO) {
        itemVO.add(linkTo(methodOn(ItemController.class).getEntityById(itemVO.getKey())).withSelfRel());
    }

    @Override
    protected String getListAllSortProperty() {
        return "code";
    }

    @Override
    public ResponseEntity<PagedResources<Resource<ItemVO>>> listAll(final int page, final int limit, final String direction, final PagedResourcesAssembler<ItemVO> assembler) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
