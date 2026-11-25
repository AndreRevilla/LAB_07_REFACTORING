package tv.codely.mooc.enrollments.domain;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseIdMother;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentIdMother;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class EnrollmentShould {

    @Test
    void be_created_with_valid_data() {
        // Arrange
        EnrollmentId id        = EnrollmentIdMother.random();
        StudentId    studentId = StudentIdMother.random();
        CourseId     courseId  = CourseIdMother.random();

        // Act
        Enrollment enrollment = new Enrollment(id, studentId, courseId);

        // Assert
        assertEquals(id, enrollment.id());
        assertEquals(studentId, enrollment.studentId());
        assertEquals(courseId, enrollment.courseId());
    }

    @Test
    void fail_when_id_is_null() {
        // Arrange
        StudentId studentId = StudentIdMother.random();
        CourseId  courseId  = CourseIdMother.random();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Enrollment(null, studentId, courseId));
    }

    @Test
    void fail_when_student_id_is_null() {
        // Arrange
        EnrollmentId id       = EnrollmentIdMother.random();
        CourseId     courseId = CourseIdMother.random();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Enrollment(id, null, courseId));
    }

    @Test
    void fail_when_course_id_is_null() {
        // Arrange
        EnrollmentId id        = EnrollmentIdMother.random();
        StudentId    studentId = StudentIdMother.random();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Enrollment(id, studentId, null));
    }

    @Test
    void match_same_student_and_same_course() {
        // Arrange
        StudentId  studentId  = StudentIdMother.random();
        CourseId   courseId   = CourseIdMother.random();
        Enrollment enrollment = EnrollmentMother.of(studentId, courseId);

        // Act
        boolean matches = enrollment.matches(studentId, courseId);

        // Assert
        assertTrue(matches);
    }

    @Test
    void not_match_a_different_student() {
        // Arrange
        CourseId   courseId   = CourseIdMother.random();
        Enrollment enrollment = EnrollmentMother.of(StudentIdMother.random(), courseId);

        // Act
        boolean matches = enrollment.matches(StudentIdMother.random(), courseId);

        // Assert
        assertFalse(matches);
    }

    @Test
    void not_match_a_different_course() {
        // Arrange
        StudentId  studentId  = StudentIdMother.random();
        Enrollment enrollment = EnrollmentMother.of(studentId, CourseIdMother.random());

        // Act
        boolean matches = enrollment.matches(studentId, CourseIdMother.random());

        // Assert
        assertFalse(matches);
    }

    @Test
    void belong_to_its_student() {
        // Arrange
        StudentId  studentId  = StudentIdMother.random();
        Enrollment enrollment = EnrollmentMother.forStudent(studentId);

        // Act & Assert
        assertTrue(enrollment.belongsTo(studentId));
        assertFalse(enrollment.belongsTo(StudentIdMother.random()));
    }
}
