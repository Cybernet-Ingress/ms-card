package com.example.ms.card.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Arrays;
import java.util.Objects;

import static com.example.ms.card.exception.ExceptionConstants.INVALID_INPUT_PARAMETER_CODE;
import static com.example.ms.card.exception.ExceptionConstants.INVALID_INPUT_PARAMETER_MESSAGE;
import static com.example.ms.card.exception.ExceptionConstants.INVALID_REQUEST_CODE;
import static com.example.ms.card.exception.ExceptionConstants.INVALID_REQUEST_MESSAGE;
import static com.example.ms.card.exception.ExceptionConstants.INVALID_URL_CODE;
import static com.example.ms.card.exception.ExceptionConstants.INVALID_URL_MESSAGE;
import static com.example.ms.card.exception.ExceptionConstants.UNEXPECTED_EXCEPTION_CODE;
import static com.example.ms.card.exception.ExceptionConstants.UNEXPECTED_EXCEPTION_MESSAGE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception ex){
        log.error("Exception: ", ex);
        return new ExceptionResponse(UNEXPECTED_EXCEPTION_CODE, UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handle(NotFoundException ex){
        log.error("NotFoundException: ", ex);
        return new ExceptionResponse(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handle(NoResourceFoundException ex){
        log.error("NoResourceFoundException: ", ex);
        return new ExceptionResponse(INVALID_URL_CODE, INVALID_URL_MESSAGE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(METHOD_NOT_ALLOWED)
    public ExceptionResponse handle(HttpRequestMethodNotSupportedException ex){
        log.error("HttpRequestMethodNotSupportedException: ", ex);
        return new ExceptionResponse(INVALID_REQUEST_CODE, INVALID_REQUEST_MESSAGE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handle(MethodArgumentNotValidException ex){
        log.error("MethodArgumentNotValidException: ", ex);
        return new ExceptionResponse(INVALID_INPUT_PARAMETER_CODE,
                String.format(INVALID_INPUT_PARAMETER_MESSAGE, Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handle(MethodArgumentTypeMismatchException ex){
        log.error("MethodArgumentTypeMismatchException: ", ex);
        return new ExceptionResponse(INVALID_INPUT_PARAMETER_CODE,
                String.format(INVALID_INPUT_PARAMETER_MESSAGE, Arrays.toString(Objects.requireNonNull(ex.getRequiredType()).getEnumConstants())));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handle(DataIntegrityViolationException ex){
        log.error("DataIntegrityViolationException: ", ex);
        return new ExceptionResponse(INVALID_INPUT_PARAMETER_CODE,
                String.format(INVALID_INPUT_PARAMETER_MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handle(MissingServletRequestParameterException ex){
        log.error("MissingServletRequestParameterException: ", ex);
        return new ExceptionResponse(INVALID_INPUT_PARAMETER_CODE,
                String.format(INVALID_INPUT_PARAMETER_MESSAGE, ex.getMessage()));
    }
}
