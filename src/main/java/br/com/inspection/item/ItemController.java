package br.com.inspection.item;

import br.com.inspection.server.AbstractController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Override
    public ResponseEntity<PagedResources<Resource<ItemVO>>> listAll(final int page, final int limit, final String direction, final PagedResourcesAssembler<ItemVO> assembler) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
