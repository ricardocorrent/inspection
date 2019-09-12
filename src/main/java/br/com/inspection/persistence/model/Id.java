package br.com.inspection.persistence.model;

import java.util.UUID;

/**
 * This class is used to create the response JSON with an entity id.
 *
 * @author Sezar Thiago Caldeira
 * @since 2016-06-21
 */
public class Id {

    private UUID id;

    public Id(final UUID id) {
        this.id = id;
    }

    @SuppressWarnings("javadoc")
    public UUID getId() {
        return id;
    }

    @SuppressWarnings("javadoc")
    public void setId(final UUID id) {
        this.id = id;
    }
}

