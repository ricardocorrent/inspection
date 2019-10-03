package br.com.inspection.server.validation.exception;

import java.util.Map;

public interface ILogicError {

    String getField();

    String getMessageTemplate();

    String getMessage();

    void setMessage(String message);

    String getValue();

    String getCode();

    void setValue(String value);

    void setCode(String code);

    Map<String, Object> getParams();

    void setParams(Map<String, Object> params);

}
