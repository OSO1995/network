package nc.project.network.validator;

import nc.project.network.entity.UserBase;
import nc.project.network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserBase.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserBase userBase = (UserBase) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (userBase.getUsername().length() < 8 || userBase.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByUsername(userBase.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (userBase.getPassword().length() < 8 || userBase.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!userBase.getConfirmPassword().equals(userBase.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }
}
