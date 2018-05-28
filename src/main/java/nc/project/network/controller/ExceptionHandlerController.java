package nc.project.network.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(value = {Throwable.class})
    public String defaultErrorHandler() {
        return "error/ex";
    }
}


