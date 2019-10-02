package br.com.inspection.persistence.model;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseModel implements Serializable {

    public abstract UUID getId();

    public abstract void setId(final UUID id);

    public abstract OffsetDateTime getCreatedAt();

    public abstract void setCreatedAt(final OffsetDateTime createdAt);

    public abstract OffsetDateTime getUpdatedAt();

    public abstract void setUpdatedAt(final OffsetDateTime updatedAt);

}