package com.mateoarias.studentsrestapi.exceptions;

import java.sql.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when trying to access the database raises an error, either because the database
 * is unavailable or the network is not properly working.
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "The database is unavailable.")
public class DatabaseAccessException extends RuntimeException {

  /**
   * Creates a new {@link DatabaseAccessException} with the specified {@link SQLException}.
   *
   * @param exception The new {@link DatabaseAccessException} with the specified {@link
   *     SQLException}.
   */
  public DatabaseAccessException(@NonNull SQLException exception) {
    super("The database is not available right now. Reason: " + exception.getMessage());
  }
}
