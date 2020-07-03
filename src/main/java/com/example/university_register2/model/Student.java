package com.example.university_register2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

    private UUID studentId;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private Gender gender;
    @NotNull
    @Max(value = 140)
    @Min(value = 18)
    private int age;
    @NotNull
    @Max(value = 6)
    @Min(value = 1)
    private int studyYear;
    @NotNull
    private Major major;
    @NotNull
    private Map<String, List<Double>> courses;
    @NotNull
    private String auth;

    public Student(
            @JsonProperty("studentId") UUID studentId,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("gender") Gender gender,
            @JsonProperty("age") int age,
            @JsonProperty("studyYear") int studyYear,
            @JsonProperty("major") Major major,
            @JsonProperty("courses") Map<String, List<Double>> courses,
            @JsonProperty("auth") String auth) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.studyYear = studyYear;
        this.major = major;
        this.courses = courses;
        this.auth = auth;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public Major getMajor() {
        return major;
    }

    public Map<String, List<Double>> getCourses() {
        return courses;
    }

    public String getAuth() {
        return auth;
    }

    public int getDateOfBirth() {
        return LocalDate.now().minusYears(age).getYear();
    }

    public List<String> getAverageGrades() {
        List<String> gradeAverage = new ArrayList<>();
        final double[] total = {0};
        courses.forEach((s, doubles) -> {
            double avr = doubles.stream()
                    .mapToDouble(e -> e)
                    .average().orElse(0);
            total[0] += avr;
            gradeAverage.add(s + " average: " + String.format("%.2f", avr));
        });
        gradeAverage.add("Total average: " + String.format("%.2f", total[0] / gradeAverage.size()));

        return gradeAverage;
    }


}
