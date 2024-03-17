package com.mateoarias.studentsrestapi.students;

import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
    code = HttpStatus.NOT_FOUND,
    reason = "There is not student with the specified parameters")
class StudentNotFoundException extends RuntimeException {

  public StudentNotFoundException(String... parameters) {
    super("There is no resource with the given parameters: " + Arrays.toString(parameters));
  }
}
