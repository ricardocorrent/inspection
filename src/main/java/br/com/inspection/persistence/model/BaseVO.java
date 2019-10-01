package br.com.inspection.persistence.model;

import java.io.Serializable;
import java.util.UUID;

public interface BaseVO extends Serializable {

    UUID getKey();

    void setKey(final UUID id);

}
