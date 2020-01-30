package br.com.inspection.item;

import br.com.inspection.server.AbstractController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/item")
public class ItemController extends AbstractController<Item, ItemVO> {

    @Override
    public void generateHateoas(final ItemVO itemVO) {
        itemVO.add(linkTo(methodOn(ItemController.class).getEntityById(itemVO.getKey())).withSelfRel());
    }

    @Override
    protected String getListAllSortProperty() {
        return "code";
    }

}
