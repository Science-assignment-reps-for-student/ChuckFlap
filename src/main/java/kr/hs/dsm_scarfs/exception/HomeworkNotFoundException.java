package kr.hs.dsm_scarfs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HomeworkNotFoundException extends RuntimeException {
    public HomeworkNotFoundException() {
        super("Homework Not Found");
    }
}
