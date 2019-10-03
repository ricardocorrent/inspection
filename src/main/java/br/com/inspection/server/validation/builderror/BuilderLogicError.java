package br.com.inspection.server.validation.builderror;

import br.com.inspection.server.validation.exception.LogicError;

import java.util.Collection;

public interface BuilderLogicError {

    /**
     * Builds a {@link LogicError}.
     *
     * @return a new {@link LogicError} instance
     */
    Collection<LogicError> build();

}
