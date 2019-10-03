package br.com.inspection.server.validation.exception;

import java.util.Collection;
import java.util.Objects;

@SuppressWarnings("serial")
public class LogicException extends RuntimeException {

    private final LogicError[] errors;

    public LogicException(final LogicError... errors) {
        Objects.requireNonNull(errors, "Errors cannot be null");
        this.errors = errors;
    }

    /**
     * Exception constructor with cause and {@link LogicError}s;
     */
    public LogicException(final Throwable cause, final LogicError... errors) {
        super(cause);

        Objects.requireNonNull(errors, "Errors cannot be null");
        this.errors = errors;
    }

    public LogicException(final Collection<LogicError> errors) {
        Objects.requireNonNull(errors, "Errors cannot be null");
        this.errors = errors.toArray(new LogicError[errors.size()]);
    }

    /**
     * Exception constructor with cause and {@link LogicError}s collection;
     */
    public LogicException(final Throwable cause, final Collection<LogicError> errors) {
        super(cause);
        Objects.requireNonNull(errors, "Errors cannot be null");
        this.errors = errors.toArray(new LogicError[errors.size()]);
    }

    /**
     * Returns an array of {@link LogicError}.
     *
     * @return an array with errors
     */
    public LogicError[] getErrors() {
        return errors;
    }

}
