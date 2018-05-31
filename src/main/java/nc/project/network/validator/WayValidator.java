package nc.project.network.validator;

import nc.project.network.controller.forms.Way;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class WayValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Way.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Way way = (Way) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstVertex", "Required", "Поле не должно быть пустым!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "secondVertex", "Required", "Поле не должно быть пустым!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "parameterWay", "Required", "Поле не должно быть пустым!");
        int parameter = way.getParameterWay();
        if (parameter != 0 && parameter != 1){
            errors.rejectValue("parameterWay", "way.parameter");
        }
    }
}
