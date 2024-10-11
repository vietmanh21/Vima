package com.manhnguyen21.common.exception;

import com.manhnguyen21.common.dto.response.ErrorVm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorVm> handlerNotFoundException(NotFoundException ex, WebRequest wr) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = ex.getMessage();
        ErrorVm errorVm = new ErrorVm(status.toString(), status.getReasonPhrase(), message, null);
        return ResponseEntity.status(status).body(errorVm);
    }
}
