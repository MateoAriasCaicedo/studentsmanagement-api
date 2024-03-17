package com.mateoarias.studentsrestapi.students;

import java.util.Collection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
class StudentsController {

  private final StudentsService studentsService;

  public StudentsController(StudentsService studentsService) {
    this.studentsService = studentsService;
  }

  @GetMapping("/find/{id}")
  StudentInformationDTO findStudent(@PathVariable("id") Integer id) {
    return studentsService.findStudentById(id);
  }

  @PostMapping("/register")
  void createStudent(@RequestBody StudentInformationDTO student) {
    studentsService.registerStudent(student);
  }

  @DeleteMapping("/delete/{id}")
  StudentInformationDTO deleteStudent(@PathVariable("id") Integer id) {
    return studentsService.deleteStudent(id);
  }

  @GetMapping("/all/{limit}")
  Collection<StudentInformationDTO> findAllStudents(@PathVariable("limit") int limit) {
    return studentsService.findAllStudents(limit);
  }

  @PutMapping("/update/{id}")
  void updateStudent(@PathVariable("id") int id, @RequestBody StudentInformationDTO student) {
    studentsService.updateStudent(id, student);
  }
}
