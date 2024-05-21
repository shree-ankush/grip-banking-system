package com.tsf.bankingsystem.exception.mapper;



import com.tsf.bankingsystem.exception.BadRequestException;
import com.tsf.bankingsystem.exception.GenericResponse;
import com.tsf.bankingsystem.exception.StatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionMapper extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> toResponse(Exception exception) {
        StatusResponse statusResponse;
        HttpStatus httpStatus;

        statusResponse = StatusResponse.builder()
                .success(false)
                .statusCode(500)
                .errorMessage(exception.getMessage())
                .build();
        log.error("Generic exception caught due to  " + exception.getMessage() + " and " + exception.toString(), exception);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        GenericResponse genericResponse = GenericResponse.builder().statusResponse(statusResponse).build();
        return ResponseEntity.status(httpStatus).body(genericResponse);

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> toResponse(BadRequestException exception) {
        log.error("Bad request exception : ", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getGenericResponse());
    }
}
