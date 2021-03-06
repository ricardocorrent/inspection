package br.com.inspection.server.validation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegisterNotFoundException extends RequestException {

    public RegisterNotFoundException() {
        super(HttpStatus.NOT_FOUND.value());
    }

}
