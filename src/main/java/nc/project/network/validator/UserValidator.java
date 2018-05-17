package nc.project.network.validator;

import nc.project.network.entity.User;
import nc.project.network.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        Pattern usernamePattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$");
        Pattern passwordPattern = Pattern.compile("(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$");
        Pattern namePattern = Pattern.compile("^[а-яА-ЯёЁa-zA-Z0-9]+$");
        Matcher firstNameMatcher = namePattern.matcher(user.getPersonalInfo().getFirstName());
        Matcher secondNameMatcher = namePattern.matcher(user.getPersonalInfo().getSecondName());
        Matcher userMatcher = usernamePattern.matcher(user.getUsername());
        Matcher passMather = passwordPattern.matcher(user.getPassword());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUsername().length() < 2 || user.getUsername().length() > 20) {
            errors.rejectValue("username", "user.size.username");
        }

        if (!userMatcher.matches()){
            errors.rejectValue("username", "user.pattern.invalid");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "user.duplicate.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "user.size.password");
        }

        if(!passMather.matches()){
            errors.rejectValue("password", "password.pattern.invalid");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personalInfo.firstName", "Required");
        if (!firstNameMatcher.matches()){
            errors.rejectValue("personalInfo.firstName", "user.name.invalid");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personalInfo.secondName", "Required");
        if (!secondNameMatcher.matches()){
            errors.rejectValue("personalInfo.secondName", "user.name.invalid");
        }

    }
}
