package busan_dining.dagil.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.net.MalformedURLException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MalformedURLException.class)
    public String MalformedURLHandler() {
        return "정확한 형식의 URL이 아닙니다";
    }

    @ExceptionHandler(IOException.class)
    public String IOExceptionHandler(IOException ioe) {
        return "<UNK> <UNK> <UNK> <UNK>";
    }

    @ExceptionHandler(InterruptedException.class)
    public String InterruptedExceptionHandler(InterruptedException ie) {
        return "<UNK> <UNK> <UNK> <UNK>";
    }
}
