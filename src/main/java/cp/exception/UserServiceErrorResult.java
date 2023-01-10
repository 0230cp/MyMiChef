package cp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserServiceErrorResult {

    NO_DATA(HttpStatus.BAD_REQUEST, "NO DATA IN DATABASE")
    ,DUPLICATED_ID(HttpStatus.BAD_REQUEST, "Duplicated Id Register Request");


    private final HttpStatus httpStatus;
    private final String message;
}
