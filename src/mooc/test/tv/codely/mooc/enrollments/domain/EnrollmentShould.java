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
    void shouldBeCreatedWithValidData() {
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
    void shouldFailWhenIdIsNull() {
        // Arrange
        StudentId studentId = StudentIdMother.random();
        CourseId  courseId  = CourseIdMother.random();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Enrollment(null, studentId, courseId));
    }

    @Test
    void shouldFailWhenStudentIdIsNull() {
        // Arrange
        EnrollmentId id       = EnrollmentIdMother.random();
        CourseId     courseId = CourseIdMother.random();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Enrollment(id, null, courseId));
    }

    @Test
    void shouldFailWhenCourseIdIsNull() {
        // Arrange
        EnrollmentId id        = EnrollmentIdMother.random();
        StudentId    studentId = StudentIdMother.random();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Enrollment(id, studentId, null));
    }

    @Test
    void shouldMatchSameStudentAndSameCourse() {
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
    void shouldNotMatchADifferentStudent() {
        // Arrange
        CourseId   courseId   = CourseIdMother.random();
        Enrollment enrollment = EnrollmentMother.of(StudentIdMother.random(), courseId);

        // Act
        boolean matches = enrollment.matches(StudentIdMother.random(), courseId);

        // Assert
        assertFalse(matches);
    }

    @Test
    void shouldNotMatchADifferentCourse() {
        // Arrange
        StudentId  studentId  = StudentIdMother.random();
        Enrollment enrollment = EnrollmentMother.of(studentId, CourseIdMother.random());

        // Act
        boolean matches = enrollment.matches(studentId, CourseIdMother.random());

        // Assert
        assertFalse(matches);
    }

    @Test
    void shouldBelongToItsStudent() {
        // Arrange
        StudentId  studentId  = StudentIdMother.random();
        Enrollment enrollment = EnrollmentMother.forStudent(studentId);

        // Act & Assert
        assertTrue(enrollment.belongsTo(studentId));
        assertFalse(enrollment.belongsTo(StudentIdMother.random()));
    }
}
