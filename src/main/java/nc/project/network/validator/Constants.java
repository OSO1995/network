package nc.project.network.validator;

public interface Constants {
    String TEXT_PATTERN = "^[а-яА-ЯёЁa-zA-Z0-9]+$";
    String PASSWORD_PATTERN = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    String TEXT2_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
}
