package com.example.university_register2.resource;

import com.example.university_register2.model.Student;
import com.example.university_register2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

@Validated
@Component
@Path("test/api/v1/students")
public class StudentResourceResteasyTest {

    private StudentService studentService;

    @Autowired
    public StudentResourceResteasyTest(StudentService studentService) {
        this.studentService = studentService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void addNewStudent(@Valid Student newStudent) {
        studentService.addNewStudent(newStudent);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{studentId}")
    public Student getStudentById(@PathParam("studentId") UUID studentId) {
        return studentService.getStudentById(studentId);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{studentId}")
    public void updateStudentById(@PathParam("studentId") UUID studentId,
                                  Student update) {
        studentService.updateStudentById(studentId, update);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{studentId}")
    public void deleteStudentById(@PathParam("studentId") UUID studentId) {
        studentService.deleteStudentById(studentId);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("something_very_secured/{auth}")
    public List<Student> getStudentByAuth(@PathParam("auth") String auth) {
        return studentService.getStudentsByAuth(auth);
    }

}
