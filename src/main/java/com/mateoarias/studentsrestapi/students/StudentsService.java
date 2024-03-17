package com.mateoarias.studentsrestapi.students;

import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
class StudentsService {

  private final StudentsRepository studentsRepository;

  public StudentsService(StudentsRepository studentsRepository) {
    this.studentsRepository = studentsRepository;
  }

  void registerStudent(StudentInformationDTO student) {
    studentsRepository.create(student);
  }

  StudentInformationDTO deleteStudent(int id) {
    return studentsRepository.delete(id);
  }

  void updateStudent(int id, StudentInformationDTO student) {
    studentsRepository.update(id, student);
  }

  StudentInformationDTO findStudentById(int id)
      throws StudentNotFoundException, NoUniqueStudentIdException {
    return studentsRepository.find(id);
  }

  Collection<StudentInformationDTO> findAllStudents(int limit) {
    return studentsRepository.findAll(limit);
  }
}
