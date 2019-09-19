package br.com.inspection.tag;

import br.com.inspection.adapter.DozerAdapter;
import br.com.inspection.exception.RegisterNotFoundException;
import br.com.inspection.server.AbstractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TagService extends AbstractService<Tag, TagVO> {

    @Inject
    private TagRepository tagRepository;

    public TagVO insert(final TagVO tagVO) {
        final Tag tag = DozerAdapter.parseObject(tagVO, Tag.class);
        this.doGenerateInsertValues(tag);
        return DozerAdapter.parseObject(super.insert(tag), TagVO.class);
    }

    public TagVO update(final TagVO tagVO) {
        final Tag tagFromDb = this.tagRepository
                .findById(tagVO.getKey()).orElse(null);

        final Tag tag = DozerAdapter.parseObject(tagVO, Tag.class);

        if (tagFromDb != null) {
            this.doGenerateUpdateValues(tag, tagFromDb);
            return DozerAdapter.parseObject(this.tagRepository.save(tagFromDb), TagVO.class);
        } else {
            throw new RegisterNotFoundException();
        }
    }

    public Page<TagVO> list(final Pageable pageable) {
        final Page<Tag> page = tagRepository.findAll(pageable);
        return page.map(this::convertToTagVO);
    }

    private TagVO convertToTagVO(final Tag tag) {
        return DozerAdapter.parseObject(tag, TagVO.class);
    }

    public Page<TagVO> findTagByTitle(final String title, final Pageable pageable) {
        final Page<Tag> page = tagRepository.findTagByTitle(title, pageable);
        return page.map(this::convertToTagVO);
    }

    private void doGenerateInsertValues(final Tag tag) {
        tag.setId(UUID.randomUUID());
        tag.setCreatedAt(OffsetDateTime.now());
        tag.setUpdatedAt(OffsetDateTime.now());
    }

    private void doGenerateUpdateValues(final Tag tag, final Tag tagFromDb) {
        tagFromDb.setTitle(tag.getTitle());
        tagFromDb.setUpdatedAt(OffsetDateTime.now());
    }

    @Override
    public Class<TagVO> getClazz() {
        return TagVO.class;
    }

}
