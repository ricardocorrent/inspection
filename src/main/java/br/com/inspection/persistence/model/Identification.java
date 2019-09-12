package br.com.inspection.persistence.model;

public interface Identification<T extends Object> {

    /**
     * @return the object identification value to be retrieved.
     */
    T getId();

    /**
     * @param id the object identification value to be set.
     */
    void setId(final T id);
}
