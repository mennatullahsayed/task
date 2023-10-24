package io.waggeh.waggeh.config;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.util.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponseWrapper<Object>> handleNotFound(final NotFoundException exception) {
        final ApiResponseWrapper<Object> errorResponse = new ApiResponseWrapper<Object>(false, exception.getMessage());
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseWrapper<Object>> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException exception) {
        final ApiResponseWrapper<Object> errorResponse = new ApiResponseWrapper<Object>(false, exception.getMessage());
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponseWrapper<Object>> handleResponseStatus(
            final ResponseStatusException exception) {
        final ApiResponseWrapper<Object> errorResponse = new ApiResponseWrapper<Object>(false, exception.getMessage());
        return ResponseEntity.ok(errorResponse);
    }

    @ExceptionHandler(Throwable.class)
    @ApiResponse(responseCode = "4xx/5xx", description = "Error")
    public ResponseEntity<ApiResponseWrapper<Object>> handleThrowable(final Throwable exception) {
        exception.printStackTrace();
        final ApiResponseWrapper<Object> errorResponse = new ApiResponseWrapper<Object>(false, exception.getMessage());
        return ResponseEntity.ok(errorResponse);
    }

}
