package kr.hs.dsm_scarfs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotLeaderException extends RuntimeException {
    public UserNotLeaderException() {
        super("User Not Leader");
    }
}
