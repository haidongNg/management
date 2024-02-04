package com.nhd.management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.nhd.management.dto.responses.ZzGlobalErrorResponse;
import com.nhd.management.dto.responses.ZzSystemException;

@RestControllerAdvice
public class GlobalRestExceptionHandler {

  @ExceptionHandler(ZzSystemException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ZzGlobalErrorResponse handlerException(ZzSystemException exception)
  {
    ZzGlobalErrorResponse resp = new ZzGlobalErrorResponse();
    resp.setStatus(HttpStatus.NOT_FOUND.value());
    resp.setMessage(exception.getMessage());
    resp.setTimestamp(System.currentTimeMillis());
    return resp;
  }
  
  @ExceptionHandler(RuntimeException.class)
  public ZzGlobalErrorResponse handlerException(RuntimeException exception)
  {
    ZzGlobalErrorResponse resp = new ZzGlobalErrorResponse();
    if (exception instanceof AccessDeniedException) {
      resp.setStatus(HttpStatus.FORBIDDEN.value());
    } else {
      resp.setStatus(HttpStatus.NOT_FOUND.value());
    }
    resp.setMessage(exception.getMessage());
    resp.setTimestamp(System.currentTimeMillis());
    return resp;
  }
}
