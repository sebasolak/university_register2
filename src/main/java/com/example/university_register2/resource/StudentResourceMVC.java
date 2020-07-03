package com.example.university_register2.resource;

import com.example.university_register2.model.Student;
import com.example.university_register2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@RestController
//@RequestMapping("api/v1/students")
public class StudentResourceMVC {

    private final StudentService studentService;

    @Autowired
    public StudentResourceMVC(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Student> getAllStudents() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>(user.getAuthorities());

        return "PROFESOR".equals(grantedAuthorities.get(0).getAuthority()) ||
                "PROF_TRAINNE".equals(grantedAuthorities.get(0).getAuthority()) ?
                studentService.getAllStudents() : getStudentByAuth(grantedAuthorities.get(0).getAuthority());
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public void addNewStudent(@RequestBody Student newStudent) {
        studentService.addNewStudent(newStudent);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}"
    )
    public Student getStudentById(@PathVariable("studentId") UUID studentId) {
        return studentService.getStudentById(studentId);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}"
    )
    public void updateStudentById(@PathVariable("studentId") UUID studentId,
                                  @RequestBody Student update) {
        studentService.updateStudentById(studentId, update);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "{studentId}"
    )
    public void deleteStudentById(@PathVariable("studentId") UUID studentId) {
        studentService.deleteStudentById(studentId);
    }

    @RequestMapping(
            value = "something_very_secured/{auth}",
            method = RequestMethod.GET
    )
    public List<Student> getStudentByAuth(@PathVariable("auth") String auth) {
        return studentService.getStudentsByAuth(auth);
    }
}
