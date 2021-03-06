package kr.hs.dsm_scarfs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAuthEmailException extends RuntimeException {
    public InvalidAuthEmailException() {
        super("Invalid AuthEmail");
    }
}
