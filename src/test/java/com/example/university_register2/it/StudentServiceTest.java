package com.example.university_register2.it;

import com.example.university_register2.dao.FakeStudentDao;
import com.example.university_register2.model.Student;
import com.example.university_register2.service.StudentService;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static com.example.university_register2.model.Gender.FEMALE;
import static com.example.university_register2.model.Gender.MALE;
import static com.example.university_register2.model.Major.MEDICINE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class StudentServiceTest {

    @Mock
    private FakeStudentDao fakeStudentDao;

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentService(fakeStudentDao);
    }

    @Test
    void shouldGetAllStudents() {
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

        ImmutableList<Student> students = new ImmutableList.Builder<Student>()
                .add(juanita)
                .build();

        given(fakeStudentDao.selectAllStudents()).willReturn(students);

        List<Student> allStudents = studentService.getAllStudents();

        assertThat(allStudents).hasSize(1);

        Student student = allStudents.get(0);

        assertThat(student).isEqualToComparingFieldByField(juanita);
    }

    @Test
    void shouldAddNewStudent() {
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

        given(fakeStudentDao.persistNewStudent(any(UUID.class), any(Student.class)))
                .willReturn(1);

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        studentService.addNewStudent(juanita);
        verify(fakeStudentDao).persistNewStudent(eq(juanitaId), captor.capture());

        Student student = captor.getValue();

        assertThat(student).isEqualToComparingFieldByField(juanita);
    }

    @Test
    void getStudentById() {
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

        given(fakeStudentDao.selectStudentById(juanitaId))
                .willReturn(juanita);

        assertThat(studentService.getStudentById(juanitaId))
                .isEqualToComparingFieldByField(juanita);
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

        given(fakeStudentDao.selectStudentById(juanitaId))
                .willReturn(juanita);
        given(fakeStudentDao.updateStudentById(juanitaId, juanita))
                .willReturn(1);

        ArgumentCaptor<UUID> captorUid
                = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<Student> captorStudent
                = ArgumentCaptor.forClass(Student.class);

        studentService.updateStudentById(juanitaId, juanita);

        verify(fakeStudentDao)
                .updateStudentById(captorUid.capture(), captorStudent.capture());
    }

    @Test
    void shouldDeleteStudentById() {
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

        given(fakeStudentDao.selectStudentById(juanitaId))
                .willReturn(juanita);

        studentService.deleteStudentById(juanitaId);

        verify(fakeStudentDao).deleteStudentById(juanitaId);
    }

    @Test
    void shouldGetStudentsByAuth() {
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

        ImmutableList<Student> allStudents = new ImmutableList.Builder<Student>()
                .add(juanita)
                .add(walter)
                .build();

        given(fakeStudentDao.selectAllStudents()).willReturn(allStudents);

        assertThat(studentService.getAllStudents()).hasSize(2);

        List<Student> m3 = studentService.getStudentsByAuth("M3");

        assertThat(m3).hasSize(1);
        assertThat(m3.get(0)).isEqualToComparingFieldByField(juanita);

        List<Student> m4 = studentService.getStudentsByAuth("M4");

        assertThat(m4).hasSize(1);
        assertThat(m4.get(0)).isEqualToComparingFieldByField(walter);

    }

}