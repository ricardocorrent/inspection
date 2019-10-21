package br.com.inspection.server;

import br.com.inspection.server.adapter.DozerAdapter;
import br.com.inspection.server.validation.exception.RegisterNotFoundException;
import br.com.inspection.server.model.BaseModel;
import br.com.inspection.server.model.BaseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.inject.Inject;
import java.util.UUID;

public abstract class AbstractService<T extends BaseModel, Z extends BaseVO> {

    @Inject
    private PagingAndSortingRepository<T, UUID> repository;

    public Z insert(final Z z) {
        final T t = convertEntityVOToEntity(z);
        return convertEntityToEntityVO(repository.save(t));
    }

    public Z update(final Z z) {
        final T tFromDb = repository
                .findById(z.getKey()).orElseThrow(RegisterNotFoundException::new);

        final T t = this.convertEntityVOToEntity(z);
        //this.doGenerateUpdateValues(t);
        if (tFromDb != null) {
            return convertEntityToEntityVO(repository.save(t));
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

    protected void doGenerateUpdateValues(final T t) {}

}
