package cp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum JoinErrorResult {

    DUPLICATED_ID(HttpStatus.BAD_REQUEST, "Duplicated Id Register Request");


    private final HttpStatus httpStatus;
    private final String message;

}
