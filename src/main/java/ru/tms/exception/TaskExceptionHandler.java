package ru.tms.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tms.DTO.MessageExceptionDTO;

@RestControllerAdvice
@Slf4j
public class TaskExceptionHandler {
    @ExceptionHandler(TaskException.class)
    public ResponseEntity<?> bankErrorHandler(TaskException taskException) {
        String msg = "Error";
        log.info(msg + taskException.getMessage());
        return new ResponseEntity<>(new MessageExceptionDTO(taskException.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
