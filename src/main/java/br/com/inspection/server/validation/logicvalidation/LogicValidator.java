package br.com.inspection.server.validation.logicvalidation;

import br.com.inspection.server.model.BaseModel;
import br.com.inspection.server.validation.builderror.ListBuilderLogicError;
import br.com.inspection.server.validation.exception.LogicException;
import org.apache.commons.collections.CollectionUtils;

public abstract class LogicValidator<T extends BaseModel> {

    @SuppressWarnings("unchecked")
    public void validateAndThrowFoundErrors(final Object object) {
        final T entity = (T) object;
        final ListBuilderLogicError violations = validateEntity(entity);
        if (CollectionUtils.isNotEmpty(violations.getErrors())) {
            throw new LogicException(violations.getErrors());
        }
    }

    protected abstract ListBuilderLogicError validateEntity(final T entity);
}
