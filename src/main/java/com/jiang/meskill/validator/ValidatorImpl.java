package com.jiang.meskill.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author jiangs
 * @create 2022-04-13-20:18
 */
@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    public ValidationResult validate(Object bean){
        ValidationResult validationResult = new ValidationResult();
        Set<ConstraintViolation<Object>> validateSet = validator.validate(bean);
        if(validateSet.size()>0){
            validationResult.setHasError(true);
            validateSet.forEach(validate->{
                String message = validate.getMessage();
                String propertyName = validate.getPropertyPath().toString();
                validationResult.getErrorMsgMap().put(propertyName, message);
            });
        }
        return validationResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
