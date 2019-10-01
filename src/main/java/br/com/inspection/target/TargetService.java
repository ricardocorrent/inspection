package br.com.inspection.target;

import br.com.inspection.server.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class TargetService extends AbstractService<Target, TargetVO> {

    @Inject
    private TargetRepository targetRepository;

    public Page<TargetVO> findTargetByTitle(final String name, final Pageable pageable) {
        final Page<Target> page = targetRepository.findTargetByName(name, pageable);
        return page.map(this::convertEntityToEntityVO);
    }

    @Override
    protected void doGenerateUpdateValues(final Target target, final Target targetFromDb) {
        super.doGenerateUpdateValues(target, targetFromDb);
        targetFromDb.setName(target.getName());
    }

    @Override
    public Class<TargetVO> getClazz() {
        return TargetVO.class;
    }

    @Override
    public Class<Target> getEntityClazz() {
        return Target.class;
    }

}
