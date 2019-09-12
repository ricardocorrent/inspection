package br.com.inspection.tag;

import br.com.inspection.adapter.DozerAdapter;
import br.com.inspection.exception.RegisterNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TagService {

    @Inject
    private TagRepository tagRepository;

    public TagVO insert(final TagVO tagVO) {
        final Tag tag = DozerAdapter.parseObject(tagVO, Tag.class);
        this.doGenerateInsertValues(tag);
        return DozerAdapter.parseObject(tagRepository.save(tag), TagVO.class);
    }

    public TagVO update(final TagVO tagVO) {
        final Tag tagFromDb = this.tagRepository
                .findById(tagVO.getId()).orElse(null);

        final Tag tag = DozerAdapter.parseObject(tagVO, Tag.class);

        if (tagFromDb != null) {
            this.doGenerateUpdateValues(tag, tagFromDb);
            return DozerAdapter.parseObject(this.tagRepository.save(tagFromDb), TagVO.class);
        } else {
            throw new RegisterNotFoundException();
        }
    }

    public void delete(final UUID tagId) {
        final Tag tagfromDb =
                this.tagRepository
                        .findById(tagId)
                        .orElseThrow(RegisterNotFoundException::new);
        if (tagfromDb != null) {
            this.tagRepository.delete(tagfromDb);
        }
    }

    public TagVO findById(final UUID tagId) {
        return DozerAdapter.parseObject(
                tagRepository.findById(tagId)
                        .orElseThrow(RegisterNotFoundException::new), TagVO.class);
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

    public List<TagVO> batchIsert(final List<TagVO> tagVOList) {
        final List<Tag> tags = DozerAdapter.parseListObjects(tagVOList, Tag.class);
        tags.forEach(this::doGenerateInsertValues);

        final List<Tag> collect = tags.stream().map(tag -> this.tagRepository.save(tag)).collect(Collectors.toList());
        return DozerAdapter.parseListObjects(collect, TagVO.class);
    }
}
