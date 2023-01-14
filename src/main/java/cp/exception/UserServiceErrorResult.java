package cp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserServiceErrorResult {

    NO_DATA(HttpStatus.BAD_REQUEST, "NO DATA IN DATABASE")
    ,DUPLICATED_ID(HttpStatus.BAD_REQUEST, "Duplicated Id Register Request")
    ,USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "User was not found")
    ,INGREDIENT_NOT_FOUND(HttpStatus.BAD_REQUEST,"Ingredient was not found")
    ,INGREDIENT_NOT_MATCHED(HttpStatus.BAD_REQUEST,"Ingredient doesn't match")
    ,MENU_NOT_FOUND(HttpStatus.BAD_REQUEST,"Menu was not found");

    private final HttpStatus httpStatus;
    private final String message;
}
