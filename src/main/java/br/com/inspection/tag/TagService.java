package br.com.inspection.tag;

import br.com.inspection.server.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class TagService extends AbstractService<Tag, TagVO> {

    @Inject
    private TagRepository tagRepository;

    public Page<TagVO> findTagByTitle(final String title, final Pageable pageable) {
        final Page<Tag> page = tagRepository.findTagByTitle(title, pageable);
        return page.map(this::convertEntityToEntityVO);
    }

    @Override
    protected void doGenerateUpdateValues(final Tag tag, final Tag tagFromDb) {
        super.doGenerateUpdateValues(tag, tagFromDb);
        tagFromDb.setTitle(tag.getTitle());
    }

    @Override
    public Class<TagVO> getClazz() {
        return TagVO.class;
    }

    @Override
    public Class<Tag> getEntityClazz() {
        return Tag.class;
    }

}
