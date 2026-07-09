package com.lira.dev.locadora_veiculos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VeiculoNotFoundException.class)
    public ResponseEntity<ErrorResponse> veiculoNotFoundHandler(VeiculoNotFoundException ex){
        ErrorResponse erro = new ErrorResponse(ex.getMessage(),404, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorResponse>ClienteNotFoundHandler(ClienteNotFoundException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(),404,LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(LocacaoNotFoundException.class)
    public ResponseEntity<ErrorResponse>LocacaoNotFoundHandler(ClienteNotFoundException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(),404,LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestHandler(BadRequestException ex){
        ErrorResponse erro = new ErrorResponse(ex.getMessage(),400, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> MethodArgumentNotValidHandler(MethodArgumentNotValidException exception){

        String msg = exception.getBindingResult()
                .getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(java.util.stream.Collectors.joining(", "));

        BadRequestException minhaExcecao = new BadRequestException(msg);

        return badRequestHandler(minhaExcecao);
    }

}
