package lk.ijse.gdse.back_end.exceptions;

import lk.ijse.gdse.back_end.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // handle all exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<String>> handleGenericException(Exception e){
        return new ResponseEntity(new APIResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // or 500
                "Internal Server Error occurred",
                null
        ),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<APIResponse<String>> handleResourceNotFound(ResourceNotFound re){
        return new ResponseEntity(new APIResponse<>(
                HttpStatus.NOT_FOUND.value(),
                re.getMessage(),
                null
        ),HttpStatus.NOT_FOUND);
    }
}
