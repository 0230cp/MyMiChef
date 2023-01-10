package cp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserServiceException extends RuntimeException{

    private final UserServiceErrorResult userServiceErrorResult;
}
