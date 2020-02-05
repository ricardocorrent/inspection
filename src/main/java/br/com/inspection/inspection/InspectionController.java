package br.com.inspection.inspection;

import br.com.inspection.server.AbstractController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/inspection")
public class InspectionController extends AbstractController<Inspection, InspectionVO> {

    @Override
    public void generateHateoas(final InspectionVO inspectionVO) {
        inspectionVO.add(
                linkTo(methodOn(InspectionController.class).getEntityById(inspectionVO.getKey()))
                        .withSelfRel()
        );
    }

    @Override
    protected String getListAllSortProperty() {
        return "title";
    }

}
