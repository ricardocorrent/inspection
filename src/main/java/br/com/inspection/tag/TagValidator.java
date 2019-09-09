package br.com.inspection.tag;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

@Component
public class TagValidator implements Validator {

    @Override
    public boolean supports(final Class<?> aClass) {
        return aClass.isAssignableFrom(Tag.class);
    }

    @Override
    public void validate(final Object o, final Errors errors) {
        Tag tag = (Tag) o;

        if (StringUtils.isEmpty(tag.getTitle())) {
            try {
                throw new Exception("title");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
