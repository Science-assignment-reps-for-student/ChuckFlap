package kr.hs.dsm_scarfs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("User Already Exits");
    }

}
