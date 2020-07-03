package com.example.university_register2.clientproxy;

import com.example.university_register2.model.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

public interface StudentResourceV1 {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Student> getAllStudents();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    void addNewStudent(Student newStudent);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{studentId}")
    Student getStudentById(@PathParam("studentId") UUID studentId);

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{studentId}")
    void updateStudentById(@PathParam("studentId") UUID studentId, Student update);


    @DELETE
    @Path("{studentId}")
    void deleteStudentById(@PathParam("studentId") UUID studentId);


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("something_very_secured/{auth}")
    List<Student> getStudentByAuth(@PathParam("auth") String auth);

}
