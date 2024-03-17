package com.mateoarias.studentsrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Database not given access")
public class DatabaseAccessException extends RuntimeException {

  public DatabaseAccessException(@NonNull Exception exception) {
    super("The database is not available right now. Reason: " + exception.getMessage());
  }
}
