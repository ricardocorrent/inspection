package br.com.inspection.inspection;

import br.com.inspection.server.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class InspectionService extends AbstractService<Inspection, InspectionVO> {

    @Override
    public Class<InspectionVO> getClazz() {
        return InspectionVO.class;
    }

    @Override
    public Class<Inspection> getEntityClazz() {
        return Inspection.class;
    }

}
