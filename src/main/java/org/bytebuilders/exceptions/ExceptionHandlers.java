package org.bytebuilders.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception){
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//            return e.getMessage();
        }

        @ExceptionHandler(IllegalAuthException.class)
        public ResponseEntity<ErrorResponse> handleIllegalAuthException(IllegalAuthException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }

        @ExceptionHandler(IllegalVisitorException.class)
        public ResponseEntity<ErrorResponse> handleVisitorNotFoundException(IllegalVisitorException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleOtpExpiredException(UserNotFoundException exception){
            ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }


