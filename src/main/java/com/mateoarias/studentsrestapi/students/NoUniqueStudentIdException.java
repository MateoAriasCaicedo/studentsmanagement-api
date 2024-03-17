package com.mateoarias.studentsrestapi.students;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    code = HttpStatus.CONFLICT,
    reason = "The given student id is not unique and no unique student can be returned")
class NoUniqueStudentIdException extends RuntimeException {

  public NoUniqueStudentIdException(Integer id) {
    super("The given student id is not unique and no unique student can be returned: " + id);
  }
}
