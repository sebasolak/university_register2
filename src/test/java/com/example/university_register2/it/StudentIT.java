package com.example.university_register2.it;

import com.example.university_register2.clientproxy.StudentResourceV1;
import com.example.university_register2.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static com.example.university_register2.model.Gender.FEMALE;
import static com.example.university_register2.model.Gender.MALE;
import static com.example.university_register2.model.Major.MEDICINE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StudentIT {

    @Autowired
    private StudentResourceV1 studentResourceV1;


    @Test
    void shouldAddNewStudent() {
        //Given
        UUID juanitaId = UUID.randomUUID();
        Student juanita = new Student(juanitaId,
                "Juanita",
                "Craig",
                "juanita.craig@example.com",
                FEMALE,
                20,
                2,
                MEDICINE,
                new HashMap<String, List<Double>>() {{
                    put("Immunology", new ArrayList<>(Arrays.asList(4.5, 4.5, 5.0, 5.0, 4.5)));
                    put("Biochemistry", new ArrayList<>(Arrays.asList(5.0, 4.5, 5.0)));
                    put("Pharmacology", new ArrayList<>(Arrays.asList(4.0, 4.5, 4.5, 4.0)));
                    put("Traumatology", new ArrayList<>(Arrays.asList(5.0, 5.0, 4.5, 5.0, 5.0)));
                    put("Laboratory_diagnostics", new ArrayList<>(Arrays.asList(4.5, 5.0, 5.0, 4.0)));
                }},
                "M3"
        );

        //When
        studentResourceV1.addNewStudent(juanita);

        //Then
        Student student = studentResourceV1.getStudentById(juanitaId);
        assertThat(student).isEqualToComparingFieldByField(juanita);
    }

    @Test
    void shouldDeleteUser() {
        //Given
        UUID juanitaId = UUID.randomUUID();
        Student juanita = new Student(juanitaId,
                "Juanita",
                "Craig",
                "juanita.craig@example.com",
                FEMALE,
                20,
                2,
                MEDICINE,
                new HashMap<String, List<Double>>() {{
                    put("Immunology", new ArrayList<>(Arrays.asList(4.5, 4.5, 5.0, 5.0, 4.5)));
                    put("Biochemistry", new ArrayList<>(Arrays.asList(5.0, 4.5, 5.0)));
                    put("Pharmacology", new ArrayList<>(Arrays.asList(4.0, 4.5, 4.5, 4.0)));
                    put("Traumatology", new ArrayList<>(Arrays.asList(5.0, 5.0, 4.5, 5.0, 5.0)));
                    put("Laboratory_diagnostics", new ArrayList<>(Arrays.asList(4.5, 5.0, 5.0, 4.0)));
                }},
                "M3"
        );

        //When
        studentResourceV1.addNewStudent(juanita);

        //Then
        Student student = studentResourceV1.getStudentById(juanitaId);
        assertThat(student).isEqualToComparingFieldByField(juanita);

        //When
        studentResourceV1.deleteStudentById(juanitaId);

        //Then
        student = studentResourceV1.getStudentById(juanitaId);
        assertThat(student).isNull();
    }

    @Test
    void shouldUpdateStudentById() {
        UUID juanitaId = UUID.randomUUID();
        Student juanita = new Student(juanitaId,
                "Juanita",
                "Craig",
                "juanita.craig@example.com",
                FEMALE,
                20,
                2,
                MEDICINE,
                new HashMap<String, List<Double>>() {{
                    put("Immunology", new ArrayList<>(Arrays.asList(4.5, 4.5, 5.0, 5.0, 4.5)));
                    put("Biochemistry", new ArrayList<>(Arrays.asList(5.0, 4.5, 5.0)));
                    put("Pharmacology", new ArrayList<>(Arrays.asList(4.0, 4.5, 4.5, 4.0)));
                    put("Traumatology", new ArrayList<>(Arrays.asList(5.0, 5.0, 4.5, 5.0, 5.0)));
                    put("Laboratory_diagnostics", new ArrayList<>(Arrays.asList(4.5, 5.0, 5.0, 4.0)));
                }},
                "M3"
        );

        //When
        studentResourceV1.addNewStudent(juanita);

        //Then
        Student student = studentResourceV1.getStudentById(juanitaId);
        assertThat(student).isEqualToComparingFieldByField(juanita);

        //Given
        Student updateStudent = new Student(juanitaId,
                "Walter",
                "Hill",
                "walter.hill@example.com",
                MALE,
                28,
                5,
                MEDICINE,
                new HashMap<String, List<Double>>() {{
                    put("Immunology", new ArrayList<>(Arrays.asList(4.5, 4.5, 5.0, 5.0, 4.5)));
                    put("Biochemistry", new ArrayList<>(Arrays.asList(5.0, 4.5, 5.0)));
                    put("Pharmacology", new ArrayList<>(Arrays.asList(4.0, 4.5, 4.5, 4.0)));
                    put("Traumatology", new ArrayList<>(Arrays.asList(5.0, 5.0, 4.5, 5.0, 5.0)));
                    put("Laboratory_diagnostics", new ArrayList<>(Arrays.asList(4.5, 5.0, 5.0, 4.0)));
                }},
                "M4"
        );

        //When
        studentResourceV1.updateStudentById(juanitaId, updateStudent);

        //Then
        student = studentResourceV1.getStudentById(juanitaId);
        assertThat(student).isEqualToComparingFieldByField(updateStudent);
    }

    @Test
    void shouldGetStudentsByAuth() {
        //Given
        UUID juanitaId = UUID.randomUUID();
        Student juanita = new Student(juanitaId,
                "Juanita",
                "Craig",
                "juanita.craig@example.com",
                FEMALE,
                20,
                2,
                MEDICINE,
                new HashMap<String, List<Double>>() {{
                    put("Immunology", new ArrayList<>(Arrays.asList(4.5, 4.5, 5.0, 5.0, 4.5)));
                    put("Biochemistry", new ArrayList<>(Arrays.asList(5.0, 4.5, 5.0)));
                    put("Pharmacology", new ArrayList<>(Arrays.asList(4.0, 4.5, 4.5, 4.0)));
                    put("Traumatology", new ArrayList<>(Arrays.asList(5.0, 5.0, 4.5, 5.0, 5.0)));
                    put("Laboratory_diagnostics", new ArrayList<>(Arrays.asList(4.5, 5.0, 5.0, 4.0)));
                }},
                "M3"
        );


        UUID walterId = UUID.randomUUID();
        Student walter = new Student(walterId,
                "Walter",
                "Hill",
                "walter.hill@example.com",
                MALE,
                28,
                5,
                MEDICINE,
                new HashMap<String, List<Double>>() {{
                    put("Immunology", new ArrayList<>(Arrays.asList(4.5, 4.5, 5.0, 5.0, 4.5)));
                    put("Biochemistry", new ArrayList<>(Arrays.asList(5.0, 4.5, 5.0)));
                    put("Pharmacology", new ArrayList<>(Arrays.asList(4.0, 4.5, 4.5, 4.0)));
                    put("Traumatology", new ArrayList<>(Arrays.asList(5.0, 5.0, 4.5, 5.0, 5.0)));
                    put("Laboratory_diagnostics", new ArrayList<>(Arrays.asList(4.5, 5.0, 5.0, 4.0)));
                }},
                "M4"
        );

        //When
        studentResourceV1.addNewStudent(juanita);
        studentResourceV1.addNewStudent(walter);

        //Then
        Student juanitaMaybe = studentResourceV1.getStudentByAuth("M3").get(0);
        Student walterMaybe = studentResourceV1.getStudentByAuth("M4").get(0);

        assertThat(juanitaMaybe).isEqualToComparingFieldByField(juanita);
        assertThat(walterMaybe).isEqualToIgnoringGivenFields(walter,"studentId");
    }

    @Test
    void shouldThrowException() {
        UUID juanitaId = UUID.randomUUID();
        Student juanita = new Student(juanitaId,
                null,
                null,
                null,
                null,
                144,
                0,
                null,
                null,
                null
        );

        assertThatThrownBy(() ->studentResourceV1.addNewStudent(juanita))
                .isInstanceOf(RuntimeException.class);
    }
}
