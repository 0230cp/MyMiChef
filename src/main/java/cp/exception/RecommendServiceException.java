package cp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecommendServiceException extends RuntimeException{

    private final RecommendServiceErrorResult recommendServiceErrorResult;
}
