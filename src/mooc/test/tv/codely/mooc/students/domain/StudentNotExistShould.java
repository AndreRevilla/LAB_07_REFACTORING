package tv.codely.mooc.students.domain;

import org.junit.jupiter.api.Test;
import tv.codely.shared.domain.DomainError;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class StudentNotExistShould {

    @Test
    void shouldBeADomainErrorWithItsOwnErrorCode() {
        // Arrange
        StudentId studentId = StudentIdMother.random();

        // Act
        StudentNotExist error = new StudentNotExist(studentId);

        // Assert
        assertTrue(error instanceof DomainError);
        assertEquals("student_not_exist", error.errorCode());
        assertEquals(String.format("The student <%s> doesn't exist", studentId.value()), error.getMessage());
    }
}
