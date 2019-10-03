package br.com.inspection.server.validation.builderror;

import br.com.inspection.server.validation.exception.LogicError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ListBuilderLogicError implements BuilderLogicError {

    private List<LogicError> errors;

    /**
     * Default constructor with {@code errors} array initialization.
     */
    public ListBuilderLogicError() {
        this.errors = new ArrayList<>();
    }

    /**
     * Constructor with errors array initialization.
     *
     * @param errors a list of {@link LogicError}
     */
    public ListBuilderLogicError(final List<LogicError> errors) {
        this.errors = errors;
    }

    /**
     * Adds an error to the list.
     *
     * @param error the error to be added
     * @return      this instance
     */
    public ListBuilderLogicError addError(final LogicError error) {
        this.errors.add(error);
        return this;
    }

    /**
     * Adds a list of errors to the current object.
     *
     * @param errors a list of {@link LogicError}
     * @return       this instance
     */
    public ListBuilderLogicError addError(final List<LogicError> errors) {
        this.errors.addAll(errors);
        return this;
    }

    /**
     * Retrieves the current error list
     *
     * @return a list containing {@link LogicError}s
     */
    public List<LogicError> getErrors() {
        return Collections.unmodifiableList(this.errors);
    }

    /**
     * @return  a Collection of {@link LogicError}
     * @see     BuilderLogicError#build()
     */
    @Override
    public Collection<LogicError> build() {
        return getErrors();
    }
}
