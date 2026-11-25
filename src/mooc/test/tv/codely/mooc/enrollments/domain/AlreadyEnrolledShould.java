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
    void be_a_domain_error_with_its_own_error_code() {
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
    void describe_the_student_and_the_course() {
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
