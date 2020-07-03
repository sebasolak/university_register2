package com.example.university_register2.dao;

import com.example.university_register2.model.Student;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.university_register2.model.Gender.FEMALE;
import static com.example.university_register2.model.Gender.MALE;
import static com.example.university_register2.model.Major.*;

@Repository("studentDao")
public class FakeStudentDao implements StudentDao{

    private final Map<UUID, Student> studentData;

    public FakeStudentDao() {
        studentData = new HashMap<>();
        UUID wilmaId = UUID.randomUUID();
        Student wilma = new Student(wilmaId,
                "Wilma",
                "Cooper",
                "wilma.cooper@example.com",
                FEMALE,
                23,
                3,
                MEDICINE,
                new HashMap<String, List<Double>>() {{
                    put("Immunology", new ArrayList<>(Arrays.asList(5.0, 4.5, 3.5, 4.0, 3.0)));
                    put("Biochemistry", new ArrayList<>(Arrays.asList(3.0, 2.5, 4.0)));
                    put("Pharmacology", new ArrayList<>(Arrays.asList(3.5, 4.5, 5.0, 4.5)));
                    put("Traumatology", new ArrayList<>(Arrays.asList(3.0, 4.0, 2.0, 3.0, 3.5)));
                    put("Laboratory_diagnostics", new ArrayList<>(Arrays.asList(4.5, 5.0, 4.0, 4.5)));
                }},
                "M1"
        );

        studentData.put(wilmaId, wilma);

        UUID larryId = UUID.randomUUID();
        Student larry = new Student(larryId,
                "Larry",
                "Armstrong",
                "larry.armstrong@example.com",
                MALE,
                24,
                6,
                MEDICINE,
                new HashMap<String, List<Double>>() {{
                    put("Immunology", new ArrayList<>(Arrays.asList(4.5, 4.5, 3.0, 5.0, 3.5)));
                    put("Biochemistry", new ArrayList<>(Arrays.asList(5.0, 4.5, 4.0)));
                    put("Pharmacology", new ArrayList<>(Arrays.asList(4.0, 3.5, 3.5, 4.0)));
                    put("Traumatology", new ArrayList<>(Arrays.asList(5.0, 4.0, 4.5, 5.0, 4.5)));
                    put("Laboratory_diagnostics", new ArrayList<>(Arrays.asList(2.5, 3.0, 3.0, 4.0)));
                }},
                "M2"
        );

        studentData.put(larryId, larry);

        UUID almaId = UUID.randomUUID();
        Student alma = new Student(almaId,
                "Alma",
                "Harrison",
                "alma.harrison@example.com",
                MALE,
                21,
                1,
                COMPUTER_SCIENCE,
                new HashMap<String, List<Double>>() {{
                    put("Web_technology", new ArrayList<>(Arrays.asList(4.5, 4.5, 5.0, 5.0, 3.5)));
                    put("Algorithms", new ArrayList<>(Arrays.asList(4.0, 4.5, 4.0)));
                    put("Database_systems", new ArrayList<>(Arrays.asList(4.0, 5.0, 3.5, 3.0)));
                    put("Electronics", new ArrayList<>(Arrays.asList(4.5, 4.0, 4.5, 5.0, 4.5)));
                    put("Big_data", new ArrayList<>(Arrays.asList(3.5, 4.0, 3.5, 4.0)));
                    put("AI", new ArrayList<>(Arrays.asList(4.5, 5.0, 5.0, 3.0, 3.0)));
                    put("Programming_languages", new ArrayList<>(Arrays.asList(5.0, 4.0, 5.0, 5.0, 5.0, 4.5, 5.0)));
                }},
                "C1"
        );

        studentData.put(almaId, alma);

        UUID sheilaId = UUID.randomUUID();
        Student sheila = new Student(sheilaId,
                "Sheila",
                "Stephens",
                "sheila.stephens@example.com",
                MALE,
                28,
                5,
                POLITICAL_SCIENCE,
                new HashMap<String, List<Double>>() {{
                    put("Foreign_policy", new ArrayList<>(Arrays.asList(3.5, 3.0, 5.0, 4.0, 3.5)));
                    put("Health_policy", new ArrayList<>(Arrays.asList(2.0, 5.0, 5.0, 5.0)));
                    put("International_law", new ArrayList<>(Arrays.asList(5.0, 3.5, 3.5)));
                    put("Comparative_politics", new ArrayList<>(Arrays.asList(4.5, 5.0, 3.0, 3.5, 2.0)));
                    put("Political_analysis", new ArrayList<>(Arrays.asList(4.5, 3.0, 4.5, 5.0)));
                }},
                "P1"
        );
        studentData.put(sheilaId, sheila);

    }

    @Override
    public List<Student> selectAllStudents() {
        return new ArrayList<>(studentData.values());
    }

    @Override
    public int persistNewStudent(UUID studentId,Student newStudent) {
        studentData.put(studentId, newStudent);
        return 1;
    }

    @Override
    public Student selectStudentById(UUID studentId) {
        return studentData.get(studentId);
    }

    @Override
    public int updateStudentById(UUID studentId, Student update) {
        update.setStudentId(studentId);
        studentData.put(studentId, update);
        return 1;
    }

    @Override
    public int deleteStudentById(UUID studentId) {
        studentData.remove(studentId);
        return 1;
    }

    @Override
    public List<Student> selectStudentsByAuth(String auth) {
        return studentData.values().stream()
                .filter(student -> auth.equals(student.getAuth()))
                .collect(Collectors.toList());
    }
}
