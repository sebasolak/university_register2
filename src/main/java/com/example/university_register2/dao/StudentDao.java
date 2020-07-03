package com.example.university_register2.dao;

import com.example.university_register2.model.Student;

import java.util.List;
import java.util.UUID;

public interface StudentDao {

    List<Student> selectAllStudents();

    int persistNewStudent(UUID studentId, Student newStudent);

    Student selectStudentById(UUID studentId);

    int updateStudentById(UUID studentId, Student update);

    int deleteStudentById(UUID studentId);

    List<Student> selectStudentsByAuth(String auth);

}
