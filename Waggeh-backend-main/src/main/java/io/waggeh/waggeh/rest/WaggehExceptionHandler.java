package io.waggeh.waggeh.rest;

import io.waggeh.waggeh.exception.SlotExpiredException;
import io.waggeh.waggeh.exception.SlotHeldException;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Component
@ControllerAdvice
public class WaggehExceptionHandler extends ResponseEntityExceptionHandler {

}
