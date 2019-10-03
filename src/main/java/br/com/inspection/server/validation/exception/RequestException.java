package br.com.inspection.server.validation.exception;

public class RequestException extends RuntimeException {

    private final int httpStatus;

    public RequestException(final int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public RequestException(final Throwable cause, final int httpStatus) {
        super(cause);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

}
