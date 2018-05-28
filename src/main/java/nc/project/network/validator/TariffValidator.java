package nc.project.network.validator;

import nc.project.network.entity.Tariff;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TariffValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Tariff.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Tariff tariff = (Tariff) o;

        Pattern namePattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Required","Имя не должно быть пустым!");
        Matcher name = namePattern.matcher(tariff.getDescription());
        if (!name.matches()) {
            errors.rejectValue("description", "user.size.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "speed", "Required");
        if (tariff.getSpeed() <= 100 || tariff.getSpeed() >= 1000) {
            errors.rejectValue("speed", "user.tariff.invalid.speed");
        }

    }
}
