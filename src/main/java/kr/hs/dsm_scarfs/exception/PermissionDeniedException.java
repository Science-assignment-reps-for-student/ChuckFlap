package kr.hs.dsm_scarfs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException() {
        super("Permission Denied");
    }
}
