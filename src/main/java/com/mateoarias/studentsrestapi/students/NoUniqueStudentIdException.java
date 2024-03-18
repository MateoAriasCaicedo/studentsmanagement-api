package com.mateoarias.studentsrestapi.students;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Thrown when the given ID is not unique, and therefore no unique student can be processed. */
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "The given student id is not unique.")
class NoUniqueStudentIdException extends RuntimeException {

  /**
   * Creates a new {@link NoUniqueStudentIdException} with the ID that is not unique.
   *
   * @param id The non unique ID.
   */
  public NoUniqueStudentIdException(Integer id) {
    super("The given student id is not unique and no unique student can be returned: " + id);
  }
}
