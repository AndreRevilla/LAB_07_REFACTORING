package tv.codely.mooc.enrollments.domain;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseIdMother;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentIdMother;
import tv.codely.shared.domain.DomainError;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class AlreadyEnrolledShould {

    @Test
    void shouldBeADomainErrorWithItsOwnErrorCode() {
        // Arrange
        StudentId studentId = StudentIdMother.random();
        CourseId  courseId  = CourseIdMother.random();

        // Act
        AlreadyEnrolled error = new AlreadyEnrolled(studentId, courseId);

        // Assert
        assertTrue(error instanceof DomainError);
        assertEquals("already_enrolled", error.errorCode());
    }

    @Test
    void shouldDescribeTheStudentAndTheCourse() {
        // Arrange
        StudentId studentId = StudentIdMother.random();
        CourseId  courseId  = CourseIdMother.random();

        // Act
        AlreadyEnrolled error = new AlreadyEnrolled(studentId, courseId);

        // Assert
        assertEquals(
            String.format(
                "The student <%s> is already enrolled in the course <%s>",
                studentId.value(),
                courseId.value()
            ),
            error.getMessage()
        );
    }
}
