package nc.project.network.validator;

import nc.project.network.entity.Role;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RoleValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Role.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Role role = (Role) o;

        Pattern namePattern = Pattern.compile(Constants.TEXT_PATTERN);
        Matcher name = namePattern.matcher(role.getRoleName());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "Required","Поле не должно быть пустым!");
        if (!name.matches()) {
            errors.rejectValue("roleName", "user.size.username");
        }
    }
}
