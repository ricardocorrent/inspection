package br.com.inspection.tag;

import br.com.inspection.adapter.DozerAdapter;
import br.com.inspection.exception.RegisterNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

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

    public List<TagVO> list() {
        return DozerAdapter.parseListObjects((List<Tag>) tagRepository.findAll(), TagVO.class);
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

}
