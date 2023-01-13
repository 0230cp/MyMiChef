package cp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum RecommendServiceErrorResult {

    NO_DATA(HttpStatus.BAD_REQUEST, "NO DATA IN DATABASE");

    private final HttpStatus httpStatus;
    private final String message;
}
