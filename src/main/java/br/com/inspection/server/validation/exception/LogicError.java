package br.com.inspection.server.validation.exception;

import java.beans.Transient;
import java.util.Collections;
import java.util.Map;

public class LogicError implements ILogicError {

    private String field;

    private String messageTemplate;

    private String message;

    private String value;

    private String code;

    private Map<String, Object> params;


    @SuppressWarnings("unused")
    private LogicError() {
    }

    /**
     * Constructor with minimal information instantiation.
     *
     * @param field   that was validated.
     * @param message that already was internationalized.
     */
    public LogicError(final String field, final String message) {
        this(field, message, message, null, null);
    }

    /**
     * Constructor with {@code messageTemplate}, {@code value} and {@code param} additional parameters initialization,
     * without message.
     *
     * @param field           that was validated.
     * @param messageTemplate to perform internationalization.
     * @param value           that was rejected by validation.
     * @param params          to compose message.
     */
    public LogicError(final String field, final String messageTemplate, final String value, final Map<String, Object> params) {
        this.field = field;

        this.messageTemplate = messageTemplate;

        //Prevent API crash
        this.message = messageTemplate;

        this.value = value;

        this.params = params;
    }

    /**
     * Constructor with {@code messageTemplate}, {@code value} and {@code param} additional parameters initialization.
     *
     * @param field           that was validated.
     * @param messageTemplate to perform internationalization.
     * @param message         that already was internationalized.
     * @param value           that was rejected by validation.
     * @param params          to compose message.
     */
    public LogicError(final String field, final String messageTemplate, final String message, final String value, final Map<String, Object> params) {
        this.field = field;

        this.messageTemplate = messageTemplate;

        this.message = message;

        this.value = value;

        this.params = params;
    }

    /**
     * Constructor with {@code value} additional parameters initialization.
     *
     * @param field   that was validated.
     * @param message that already was internationalized.
     * @param value   that was rejected by validation.
     */
    public LogicError(final String field, final String message, final String value) {
        this.field = field;

        this.messageTemplate = message;

        this.message = message;

        this.value = value;
    }

    /**
     * Constructor with all possible information that can be stored in a {@link LogicError} initialization.
     *
     * @param field           that was validated.
     * @param messageTemplate to perform internationalization.
     * @param message         that already was internationalized.
     * @param value           that was rejected by validation.
     * @param code            to identify the error
     * @param params          to compose message.
     */
    public LogicError(final String field, final String messageTemplate, final String message, final String value,
                      final String code, final Map<String, Object> params) {
        this.field = field;
        this.messageTemplate = messageTemplate;
        this.message = message;
        this.value = value;
        this.code = code;
        this.params = params;
    }

    @Override
    public String getField() {
        return field;
    }

    @Transient
    @Override
    public String getMessageTemplate() {
        return messageTemplate;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Map<String, Object> getParams() {
        //Prevents NullPointer in unmodifiableMap
        if (params == null) {
            return Collections.emptyMap();
        }
        //Don't send internal collections to out of object, preventing undue manipulation
        return Collections.unmodifiableMap(this.params);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }


}
