package com.example.university_register2.it;

import com.example.university_register2.dao.FakeStudentDao;
import com.example.university_register2.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.example.university_register2.model.Gender.*;
import static com.example.university_register2.model.Major.*;
import static org.assertj.core.api.Assertions.assertThat;


class FakeStudentDaoTest {

    private FakeStudentDao fakeStudentDao;

    @BeforeEach
    void setUp() {
        fakeStudentDao = new FakeStudentDao();
    }

    @Test
    void shouldSelectAllStudents() {
        List<Student> allStudents = fakeStudentDao.selectAllStudents();
        assertThat(allStudents).hasSize(4);

        Student student = allStudents.get(0);
        assertThat(student.getStudentId()).isInstanceOf(UUID.class);
        assertThat(student.getFirstName()).isIn("Wilma", "Larry", "Alma", "Sheila");
        assertThat(student.getLastName()).isIn("Cooper", "Armstrong", "Harrison", "Stephens");
        assertThat(student.getEmail()).isIn("wilma.cooper@example.com", "larry.armstrong@example.com",
                "alma.harrison@example.com", "sheila.stephens@example.com");
        assertThat(student.getGender()).isIn(MALE, FEMALE);
        assertThat(student.getAge()).isBetween(21, 28);
        assertThat(student.getStudyYear()).isBetween(1, 6);
        assertThat(student.getMajor()).isIn(MEDICINE, COMPUTER_SCIENCE, POLITICAL_SCIENCE);
        assertThat(student.getCourses()).isInstanceOf(HashMap.class);
        assertThat(student.getAuth()).isIn("M1", "M2", "C1", "P1");
        assertThat(student.getAverageGrades()).isInstanceOf(List.class);
    }

    @Test
    void shouldPersistNewStudent() {
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

        List<Student> allStudents = fakeStudentDao.selectAllStudents();
        assertThat(allStudents).hasSize(4);

        fakeStudentDao.persistNewStudent(juanitaId, juanita);
        allStudents = fakeStudentDao.selectAllStudents();
        assertThat(allStudents).hasSize(5);

    }

    @Test
    void selectStudentById() {
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

        fakeStudentDao.persistNewStudent(juanitaId, juanita);

        Student juanitaMaybe = fakeStudentDao.selectStudentById(juanitaId);

        assertThat(juanitaMaybe).isEqualToComparingFieldByField(juanita);
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

        fakeStudentDao.persistNewStudent(juanitaId, juanita);

        Student student = fakeStudentDao.selectStudentById(juanitaId);

        assertThat(student).isEqualToComparingFieldByField(juanita);

        Student walter = new Student(null,
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

        fakeStudentDao.updateStudentById(juanitaId, walter);

        student = fakeStudentDao.selectStudentById(juanitaId);

        assertThat(student).isEqualToComparingFieldByField(walter);

    }

    @Test
    void deleteStudentById() {
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

        fakeStudentDao.persistNewStudent(juanitaId, juanita);

        Student student = fakeStudentDao.selectStudentById(juanitaId);

        assertThat(student).isEqualToComparingFieldByField(juanita);
        assertThat(student).isNotNull();
        assertThat(fakeStudentDao.selectAllStudents()).hasSize(5);

        fakeStudentDao.deleteStudentById(juanitaId);
        student = fakeStudentDao.selectStudentById(juanitaId);
        assertThat(student).isNull();
        assertThat(fakeStudentDao.selectAllStudents()).hasSize(4);
    }

    @Test
    void shouldSelectStudentsByAuth() {
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

        fakeStudentDao.persistNewStudent(juanitaId, juanita);
        Student student = fakeStudentDao.selectStudentsByAuth("M3").get(0);
        assertThat(student).isEqualToComparingFieldByField(juanita);
    }
}