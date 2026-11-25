package tv.codely.mooc.enrollments.infrastructure.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.enrollments.domain.Enrollment;
import tv.codely.mooc.enrollments.domain.EnrollmentMother;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentIdMother;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class InMemoryEnrollmentRepositoryShould {

    private InMemoryEnrollmentRepository repository;
    private StudentId                    studentId;

    @BeforeEach
    void setUp() {
        repository = new InMemoryEnrollmentRepository();
        studentId  = StudentIdMother.random();
    }

    @Test
    void return_all_enrollments_of_a_student() {
        // Arrange
        repository.save(EnrollmentMother.forStudent(studentId));
        repository.save(EnrollmentMother.forStudent(studentId));

        // Act
        List<Enrollment> result = repository.searchByStudent(studentId);

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void return_empty_list_when_student_has_no_enrollments() {
        // Act
        List<Enrollment> result = repository.searchByStudent(StudentIdMother.random());

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void return_only_enrollments_of_that_student() {
        // Arrange
        Enrollment own = EnrollmentMother.forStudent(studentId);
        repository.save(own);
        repository.save(EnrollmentMother.random());

        // Act
        List<Enrollment> result = repository.searchByStudent(studentId);

        // Assert
        assertEquals(List.of(own), result);
    }
}
