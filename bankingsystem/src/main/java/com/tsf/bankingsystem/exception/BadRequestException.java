package com.tsf.bankingsystem.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
public class BadRequestException extends RuntimeException{

    private String reason;
    private Integer code;
    private String data;
    private static final org.slf4j.Logger eventsLog = org.slf4j.LoggerFactory.getLogger("failedLogs");

    public BadRequestException(String reason) {
        super(BadRequestException.class.getSimpleName());
        this.reason = reason;
        this.code = 400;
        log.error("BadRequestException raised due to reason - " + reason);
        eventsLog.error("BadRequestException raised due to reason - " + reason);
    }

    public BadRequestException(Integer code) {
        super(BadRequestException.class.getSimpleName());
        this.reason = "Bad Request";
        this.code = code;
        log.error("BadRequestException raised due to code - " + code);
        eventsLog.error("BadRequestException raised due to code - " + code);
    }

    public BadRequestException(String reason, Integer code) {
        super(BadRequestException.class.getSimpleName());
        this.reason = reason;
        this.code = code;
        log.error("BadRequestException raised with code - " + code + " due to - " + reason);
        eventsLog.error("BadRequestException raised with code  - " + code + " due to - " + reason);
    }

    public BadRequestException(String reason, Integer code, String data) {
        super(BadRequestException.class.getSimpleName());
        this.reason = reason;
        this.code = code;
        this.data = data;
        log.error("BadRequestException raised with code - " + code + " due to - " + reason + "with data -" + data);
        eventsLog.error("BadRequestException raised with code  - " + code + " due to - " + reason + "with data -" + data);
    }
    public BadRequestException(String reason, Integer code, Exception ex) {
        super(ex.getMessage());
        this.reason = reason;
        this.code = code;
        log.error("BadRequestException raised with code - " + code + " due to - " + reason);
        eventsLog.error("BadRequestException raised with code  - " + code + " due to - " + reason + "  and message " + ex.getMessage());
    }

    public StatusResponse getStatusResponse() {

        return StatusResponse.builder()
                .success(false)
                .statusCode(this.code)
                .errorMessage(this.reason)
                .reason(this.getMessage())
                .build();

    }

    public GenericResponse getGenericResponse() {

        return GenericResponse.builder()
                .statusResponse(getStatusResponse())
                .build();
    }
}