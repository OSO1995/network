package nc.project.network.validator;

import nc.project.network.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        Pattern usernamePattern = Pattern.compile(Constants.TEXT2_PATTERN);
        Pattern passwordPattern = Pattern.compile(Constants.PASSWORD_PATTERN);
        Pattern namePattern = Pattern.compile(Constants.TEXT_PATTERN);
        Matcher firstNameMatcher = namePattern.matcher(user.getPersonalInfo().getFirstName());
        Matcher secondNameMatcher = namePattern.matcher(user.getPersonalInfo().getSecondName());
        Matcher userMatcher = usernamePattern.matcher(user.getUsername());
        Matcher passMather = passwordPattern.matcher(user.getPassword());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required","Имя не должно быть пустым!");
        validateUserName(errors, user, userMatcher);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required","Пароль не должен быть пустым!");
        validatePassword(errors, user, passMather);

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personalInfo.firstName", "Required","Поле не должно быть пустым!");
        if (!firstNameMatcher.matches()){
            errors.rejectValue("personalInfo.firstName", "user.name.invalid");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personalInfo.secondName", "Required","Поле не должно быть пустым!");
        if (!secondNameMatcher.matches()){
            errors.rejectValue("personalInfo.secondName", "user.name.invalid");
        }

    }

    private void validatePassword(Errors errors, User user, Matcher passMather) {
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "user.size.password");
        }

        if(!passMather.matches()){
            errors.rejectValue("password", "password.pattern.invalid");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }
    }

    private void validateUserName(Errors errors, User user, Matcher userMatcher) {
        if (user.getUsername().length() < 2 || user.getUsername().length() > 20) {
            errors.rejectValue("username", "user.size.username");
        }

        if (!userMatcher.matches()){
            errors.rejectValue("username", "user.pattern.invalid");
        }
    }
}
