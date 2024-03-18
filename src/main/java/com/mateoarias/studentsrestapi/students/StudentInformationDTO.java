package com.mateoarias.studentsrestapi.students;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO representing the basic information of a student.
 *
 * @param firstName The student first name.
 * @param lastName The student last name.
 * @param birthDate The student birth date, represented by a string with the form of yyyy-mm-dd.
 */
record StudentInformationDTO(String firstName, String lastName, String birthDate) {

  /**
   * Creates a list of {@link StudentInformationDTO} from the given {@link ResultSet} that contains
   * the necessary information to instantiate the students.
   *
   * @param resultSet The result set where the students are returned from the database.
   * @return A list of {@link StudentInformationDTO}. Note that, if the {@link ResultSet} does not
   *     contain the value for a field of the {@link StudentInformationDTO}, therefore it will be
   *     null.
   * @throws SQLException If there is an error with the {@link ResultSet}. For example, if it is
   *     closed.
   */
  static List<StudentInformationDTO> fromResultSet(ResultSet resultSet) throws SQLException {

    var output = new ArrayList<StudentInformationDTO>();

    while (resultSet.next()) {
      output.add(
          new StudentInformationDTO(
              resultSet.getString(StudentDBUtils.FIRST_NAME),
              resultSet.getString(StudentDBUtils.LAST_NAME),
              resultSet.getString(StudentDBUtils.BIRTH_DATE)));
    }

    return output;
  }
}
