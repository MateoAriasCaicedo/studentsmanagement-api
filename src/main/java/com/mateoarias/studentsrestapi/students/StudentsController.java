package com.mateoarias.studentsrestapi.students;

import java.util.Collection;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Controller with the endpoints for the students' management. */
@RestController
@RequestMapping("/students")
class StudentsController {

  /** The {@link StudentsService} used to link the endpoints with the services. */
  private final StudentsService studentsService;

  /**
   * Instantiates a new {@link StudentsController} with the {@link StudentsService} to be used by
   * the controller.
   *
   * @param studentsService The {@link StudentsService} to be used by the controller.
   */
  public StudentsController(StudentsService studentsService) {
    this.studentsService = studentsService;
  }

  /**
   * Allows the user to find a {@link StudentInformationDTO} by the student ID.
   *
   * @param id The student ID.
   * @return The {@link StudentInformationDTO} associated with the student ID.
   */
  @GetMapping("/find/{id}")
  StudentInformationDTO findStudent(@PathVariable("id") Integer id) {
    return studentsService.findStudentById(id);
  }

  /**
   * Allows the user to create a new student by providing the {@link StudentInformationDTO} to be
   * associated with the new student.
   *
   * @param student The {@link StudentInformationDTO} to be associated with the new student.
   */
  @PostMapping("/register")
  void createStudent(@RequestBody StudentInformationDTO student) {
    studentsService.registerStudent(student);
  }

  /**
   * Allows the user to delete a student by its ID.
   *
   * @param id The ID of the student to be deleted.
   * @return The deleted student.
   */
  @DeleteMapping("/delete/{id}")
  StudentInformationDTO deleteStudent(@PathVariable("id") Integer id) {
    return studentsService.deleteStudent(id);
  }

  /**
   * Allows the user to find all the students represented as {@link StudentInformationDTO}s.
   *
   * @param limit The maximum number of students to be returned in the collection.
   * @return A {@link Collection<StudentInformationDTO>} containing all the found students. The
   *     returned collection size won't exceed the specified limit.
   */
  @GetMapping("/all/{limit}")
  Collection<StudentInformationDTO> findAllStudents(@PathVariable("limit") int limit) {
    return studentsService.findAllStudents(limit);
  }

  /**
   * Allows the user to update the student basic information by its ID and the {@link
   * StudentInformationDTO} containing the new data.
   *
   * @param id The ID of the student to be updated.
   * @param student The {@link StudentInformationDTO} containing the updated student information.
   */
  @PutMapping("/update/{id}")
  void updateStudent(@PathVariable("id") int id, @RequestBody StudentInformationDTO student) {
    studentsService.updateStudent(id, student);
  }
}
