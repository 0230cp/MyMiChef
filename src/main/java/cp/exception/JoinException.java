package cp.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class JoinException extends RuntimeException{

    private final JoinErrorResult joinErrorResult;


}
