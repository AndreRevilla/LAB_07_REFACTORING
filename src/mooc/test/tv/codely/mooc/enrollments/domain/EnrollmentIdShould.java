package tv.codely.mooc.enrollments.domain;

import org.junit.jupiter.api.Test;
import tv.codely.shared.domain.UuidMother;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class EnrollmentIdShould {

    @Test
    void shouldBeCreatedFromAValidUuid() {
        // Arrange
        String uuid = UuidMother.random();

        // Act
        EnrollmentId id = new EnrollmentId(uuid);

        // Assert
        assertEquals(uuid, id.value());
    }

    @Test
    void shouldRejectAValueThatIsNotAUuid() {
        // Arrange
        String invalidValue = "e1";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new EnrollmentId(invalidValue));
    }

    @Test
    void shouldBeEqualToAnotherIdWithTheSameValue() {
        // Arrange
        String uuid = UuidMother.random();

        // Act & Assert
        assertEquals(new EnrollmentId(uuid), new EnrollmentId(uuid));
    }
}
