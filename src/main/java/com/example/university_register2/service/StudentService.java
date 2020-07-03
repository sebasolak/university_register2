package com.example.university_register2.service;

import com.example.university_register2.dao.StudentDao;
import com.example.university_register2.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentDao studentDao;

    @Autowired
    public StudentService(@Qualifier("studentDao") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getAllStudents() {
        return studentDao.selectAllStudents();
    }

    public int addNewStudent(Student newStudent) {
        UUID studentUid = newStudent.getStudentId() == null ? UUID.randomUUID() : newStudent.getStudentId();
        newStudent.setStudentId(studentUid);
        return studentDao.persistNewStudent(studentUid, newStudent);
    }

    public Student getStudentById(UUID studentId) {
        return studentDao.selectStudentById(studentId);
    }

    public int updateStudentById(UUID studentId, Student update) {
        update.setStudentId(studentId);
        return studentDao.updateStudentById(studentId, update);
    }

    public int deleteStudentById(UUID studentId) {
        return studentDao.deleteStudentById(studentId);
    }

    public List<Student> getStudentsByAuth(String auth) {
        return studentDao.selectAllStudents().stream()
                .filter(student -> auth.equals(student.getAuth()))
                .collect(Collectors.toList());
    }
}



























