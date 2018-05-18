package nc.project.network.validator;

import nc.project.network.entity.Request;
import nc.project.network.entity.Role;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Request.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Request role = (Request) o;

        Pattern namePattern = Pattern.compile("^[а-яА-ЯёЁa-zA-Z0-9]+$");
        Matcher name = namePattern.matcher(role.getRequest());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "request", "Required");
        if (!name.matches()) {
            errors.rejectValue("request", "user.size.username");
        }
    }

}
