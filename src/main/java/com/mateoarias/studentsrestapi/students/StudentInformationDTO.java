package com.mateoarias.studentsrestapi.students;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.lang.NonNull;

public record StudentInformationDTO(String firstName, String lastName, String birthDate) {

  static List<StudentInformationDTO> fromResultSet(@NonNull ResultSet resultSet)
      throws SQLException {

    var output = new ArrayList<StudentInformationDTO>();

    while (resultSet.next()) {
      output.add(
          new StudentInformationDTO(
              resultSet.getString(StudentTableUtils.FIRST_NAME),
              resultSet.getString(StudentTableUtils.LAST_NAME),
              resultSet.getString(StudentTableUtils.BIRTH_DATE)));
    }

    return output;
  }
}
