package com.mateoarias.studentsrestapi.students;

import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Exception thrown when trying to find a student from the database but it is not found. */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Student not found.")
class StudentNotFoundException extends RuntimeException {

  /**
   * Creates a new {@link StudentNotFoundException} with a message indicating which were the
   * parameters that could not resolve which student the user is trying to find.
   *
   * @param parameters The parameters that were used to search the not found student.
   */
  public StudentNotFoundException(String... parameters) {
    super("There is no student with the given parameters: " + Arrays.toString(parameters));
  }
}
