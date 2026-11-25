package tv.codely.mooc.enrollments.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseIdMother;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentIdMother;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class EnrollmentPolicyShould {

    private EnrollmentPolicy policy;
    private StudentId        studentId;

    @BeforeEach
    void setUp() {
        policy    = new EnrollmentPolicy();
        studentId = StudentIdMother.random();
    }

    @Test
    void allow_first_enrollment() {
        // Arrange
        List<Enrollment> existing = List.of();
        CourseId         courseId = CourseIdMother.random();

        // Act & Assert
        assertDoesNotThrow(() -> policy.ensureNotEnrolled(existing, studentId, courseId));
    }

    @Test
    void reject_duplicate_enrollment() {
        // Arrange
        CourseId         courseId = CourseIdMother.random();
        List<Enrollment> existing = List.of(EnrollmentMother.of(studentId, courseId));

        // Act & Assert
        assertThrows(AlreadyEnrolled.class, () -> policy.ensureNotEnrolled(existing, studentId, courseId));
    }

    @Test
    void allow_same_student_in_another_course() {
        // Arrange
        List<Enrollment> existing    = List.of(EnrollmentMother.of(studentId, CourseIdMother.random()));
        CourseId         otherCourse = CourseIdMother.random();

        // Act & Assert
        assertDoesNotThrow(() -> policy.ensureNotEnrolled(existing, studentId, otherCourse));
    }
}
