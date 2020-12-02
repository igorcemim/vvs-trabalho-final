package br.com.gerenciadorproposta.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 7962244481915744896L;

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    public BusinessException(String message) {
        super(message);
    }
}