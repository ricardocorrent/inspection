package br.com.inspection.persistence.model;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@MappedSuperclass
public interface BaseModel extends Serializable {

    UUID getId();

    void setId(final UUID id);

    OffsetDateTime getCreatedAt();

    void setCreatedAt(final OffsetDateTime createdAt);

    OffsetDateTime getUpdatedAt();

    void setUpdatedAt(final OffsetDateTime updatedAt);
}