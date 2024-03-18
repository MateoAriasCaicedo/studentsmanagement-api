package com.mateoarias.studentsrestapi.students;

import java.util.Collection;
import org.springframework.stereotype.Service;

/** Service implementing all the logic for the students management. */
@Service
class StudentsService {

  private final StudentsRepository studentsRepository;

  public StudentsService(StudentsRepository studentsRepository) {
    this.studentsRepository = studentsRepository;
  }

  /**
   * Registers a student in the system.
   *
   * @param student The student to be registered.
   */
  void registerStudent(StudentInformationDTO student) {
    studentsRepository.create(student);
  }

  /**
   * Deletes a student from the system.
   *
   * @param id The student ID.
   * @return The deleted student.
   */
  StudentInformationDTO deleteStudent(int id) {
    return studentsRepository.delete(id);
  }

  /**
   * Updates the student information.
   *
   * @param id The ID of the student to be updated.
   * @param student The new student information.
   */
  void updateStudent(int id, StudentInformationDTO student) {
    studentsRepository.update(id, student);
  }

  /**
   * Find a student by its ID.
   *
   * @param id The student ID.
   * @return The found student.
   * @throws StudentNotFoundException If the student was not found.
   * @throws NoUniqueStudentIdException If the given ID was not unique.
   */
  StudentInformationDTO findStudentById(int id)
      throws StudentNotFoundException, NoUniqueStudentIdException {
    return studentsRepository.find(id);
  }

  /**
   * Gets all the students that are registered in the system.
   *
   * @param limit The maximum students to be returned.
   * @return The found students. Note that the number of returned students won't exceed the limit.
   */
  Collection<StudentInformationDTO> findAllStudents(int limit) {
    return studentsRepository.findAll(limit);
  }
}
