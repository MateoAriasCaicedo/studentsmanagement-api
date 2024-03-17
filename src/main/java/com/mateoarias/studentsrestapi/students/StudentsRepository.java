package com.mateoarias.studentsrestapi.students;

import com.mateoarias.studentsrestapi.exception.DatabaseAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
class StudentsRepository {

  private static final String SELECT_STUDENT_ID = "SELECT * FROM Student WHERE id = ?";

  private static final String INSERT_STUDENT =
      "INSERT INTO Student (first_name, last_name, birth_date) VALUES (?,?,?)";

  private static final String DELETE_STUDENT = "DELETE FROM Student WHERE id = ?";

  private static final String FIND_ALL_WITH_LIMIT = "SELECT * FROM Student LIMIT ?";

  private static final String UPDATE_STUDENT =
      "UPDATE Student SET first_name = ?, last_name = ?, birth_date = ? WHERE id = ?";

  private final DataSource dataSource;

  public StudentsRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void create(@NonNull StudentInformationDTO student) {
    try (var connection = dataSource.getConnection();
        var statement = connection.prepareStatement(INSERT_STUDENT)) {
      statement.setString(1, student.firstName());
      statement.setString(2, student.lastName());
      statement.setString(3, student.birthDate());
      statement.executeUpdate();
    } catch (SQLException exception) {
      throw new DatabaseAccessException(exception);
    }
  }

  public StudentInformationDTO delete(int id) {
    StudentInformationDTO student = find(id);

    try (var connection = dataSource.getConnection();
        var statement = connection.prepareStatement(DELETE_STUDENT)) {
      statement.setInt(1, id);
      statement.executeUpdate();
    } catch (SQLException exception) {
      throw new DatabaseAccessException(exception);
    }

    return student;
  }

  boolean studentExists(int id) {
    try (var connection = dataSource.getConnection();
        var statement = connection.prepareStatement(SELECT_STUDENT_ID)) {
      statement.setInt(1, id);
      try (var resultSet = statement.executeQuery()) {
        return resultSet.next();
      }
    } catch (SQLException exception) {
      throw new DatabaseAccessException(exception);
    }
  }

  void update(int id, StudentInformationDTO student) {
    if (!studentExists(id)) {
      throw new StudentNotFoundException(student.toString());
    }

    try (var connection = dataSource.getConnection();
        var statement = connection.prepareStatement(UPDATE_STUDENT)) {

      statement.setString(1, student.firstName());
      statement.setString(2, student.lastName());
      statement.setString(3, student.birthDate());
      statement.setInt(4, id);

      statement.executeUpdate();

    } catch (SQLException exception) {
      throw new DatabaseAccessException(exception);
    }
  }

  private List<StudentInformationDTO> findStudents(int id) {
    try (var connection = dataSource.getConnection();
        var statement = connection.prepareStatement(SELECT_STUDENT_ID)) {
      statement.setInt(1, id);
      try (var resultSet = statement.executeQuery()) {
        return StudentInformationDTO.fromResultSet(resultSet);
      }
    } catch (SQLException exception) {
      throw new DatabaseAccessException(exception);
    }
  }

  public StudentInformationDTO find(int id)
      throws StudentNotFoundException, NoUniqueStudentIdException, DatabaseAccessException {

    var foundStudents = findStudents(id);

    if (foundStudents.isEmpty()) {
      throw new StudentNotFoundException(String.valueOf(id));
    }

    if (foundStudents.size() > 1) {
      throw new NoUniqueStudentIdException(id);
    }

    return foundStudents.get(0);
  }

  public Collection<StudentInformationDTO> findAll(int limit) {
    try (var connection = dataSource.getConnection();
        var statement = connection.prepareStatement(FIND_ALL_WITH_LIMIT)) {
      statement.setInt(1, limit);
      try (ResultSet resultSet = statement.executeQuery()) {
        return StudentInformationDTO.fromResultSet(resultSet);
      }
    } catch (SQLException exception) {
      throw new DatabaseAccessException(exception);
    }
  }
}
