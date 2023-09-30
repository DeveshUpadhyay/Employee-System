package com.dev.employee.utility;

import com.dev.employee.exception.EmployeeException;
import jakarta.validation.ConstraintViolationException;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    private Environment environment;

    private static Log logger = LogFactory.getLog(ExceptionControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception){
        logger.error(exception.getMessage(), exception);
        ErrorInfo error = new ErrorInfo();
        error.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler({RestClientException.class, HttpClientErrorException.class})
    public ResponseEntity<ErrorInfo> restClientException(RestClientException exception){

        ErrorInfo error = new ErrorInfo();
        String exceptionMessage = exception.getMessage();
        if(!exceptionMessage.equals("Something went wrong, please check the log.")){
            exceptionMessage = exceptionMessage.substring(exceptionMessage.indexOf('{')+1,exceptionMessage.indexOf('}'));
            exceptionMessage = exceptionMessage.split(",")[0].split(":")[1];
            exceptionMessage = exceptionMessage.substring(exceptionMessage.indexOf('"')+1, exceptionMessage.lastIndexOf('"'));
        }
        error.setErrorMessage(exceptionMessage);
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ErrorInfo> employeeExceptionHandler(EmployeeException exception){
        ErrorInfo error = new ErrorInfo();
        error.setErrorMessage(environment.getProperty(exception.getMessage()));
        error.setTimeStamp(LocalDateTime.now());
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
//    public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception){
//        ErrorInfo error = new ErrorInfo();
//        String exceptionMessage="";
//        if(exception instanceof MethodArgumentNotValidException){
//            MethodArgumentNotValidException exception1 = (MethodArgumentNotValidException) exception;
//            exceptionMessage = exception1.getBindingResult().getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(", "));
//        }else{
//            ConstraintViolationException exception1 = (ConstraintViolationException) exception;
//            exceptionMessage = exception1.getConstraintViolations().stream().map(x->x.getMessage()).collect(Collectors.joining(", "));
//        }
//        error.setErrorMessage(exceptionMessage);
//        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
//        error.setTimeStamp(LocalDateTime.now());
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }

}
