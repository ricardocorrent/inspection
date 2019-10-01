package br.com.inspection.server;

import br.com.inspection.adapter.DozerAdapter;
import br.com.inspection.exception.RegisterNotFoundException;
import br.com.inspection.persistence.model.BaseModel;
import br.com.inspection.persistence.model.BaseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.UUID;

public abstract class AbstractService<T extends BaseModel, Z extends BaseVO> {

    @Inject
    private PagingAndSortingRepository<T, UUID> repository;

    public Z insert(final Z z) {
        final T t = convertEntityVOToEntity(z);
        this.doGenerateInsertValues(t);
        return convertEntityToEntityVO(repository.save(t));
    }

    public Z update(final Z z) {
        final T tFromDb = repository
                .findById(z.getKey()).orElseThrow(RegisterNotFoundException::new);

        final T t = this.convertEntityVOToEntity(z);

        if (tFromDb != null) {
            this.doGenerateUpdateValues(t, tFromDb);
            return convertEntityToEntityVO(repository.save(tFromDb));
        } else {
            throw new RegisterNotFoundException();
        }
    }

    public void delete(final UUID uuid) {
        final T zFromDb = repository.findById(uuid).orElseThrow(RegisterNotFoundException::new);

        if (zFromDb != null) {
            this.repository.delete(zFromDb);
        }
    }

    public Z getById(final UUID uuid) {
        final T t = repository.findById(uuid).orElseThrow(RegisterNotFoundException::new);
        return DozerAdapter.parseObject(t, getClazz());
    }

    public Page<Z> list(final Pageable pageable) {
        final Page<T> page = repository.findAll(pageable);
        return page.map(this::convertEntityToEntityVO);
    }

    public Z convertEntityToEntityVO(final T t) {
        return DozerAdapter.parseObject(t, getClazz());
    }

    public T convertEntityVOToEntity(final Z z) {
        return DozerAdapter.parseObject(z, getEntityClazz());
    }

    public abstract Class<Z> getClazz();

    public abstract Class<T> getEntityClazz();

    protected void doGenerateInsertValues(final T t) {
        t.setId(UUID.randomUUID());
        t.setCreatedAt(OffsetDateTime.now());
        t.setUpdatedAt(OffsetDateTime.now());
    }

    protected void doGenerateUpdateValues(final T t, final T tFromDb) {
        tFromDb.setCreatedAt(tFromDb.getCreatedAt());
        tFromDb.setUpdatedAt(OffsetDateTime.now());
    }

}