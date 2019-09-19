package br.com.inspection.server;

import br.com.inspection.adapter.DozerAdapter;
import br.com.inspection.exception.RegisterNotFoundException;
import br.com.inspection.persistence.model.BaseModel;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.inject.Inject;
import java.util.UUID;

public abstract class AbstractService<T extends BaseModel, Z> {

    @Inject
    private PagingAndSortingRepository<T, UUID> repository;

    public abstract Class<Z> getClazz();

    public Z getById(final UUID uuid) {
        final T t = repository.findById(uuid).orElseThrow(RegisterNotFoundException::new);
        return DozerAdapter.parseObject(t, getClazz());
    }

    public void delete(final UUID uuid) {
        final T zFromDb = repository.findById(uuid).orElseThrow(RegisterNotFoundException::new);

        if (zFromDb != null) {
            this.repository.delete(zFromDb);
        }
    }

    public T insert(final T t) {
        return repository.save(t);
    }

    public T update(final T t) {
        if (t != null) {
            return repository.save(t);
        }
        throw new RegisterNotFoundException();
    }

}
