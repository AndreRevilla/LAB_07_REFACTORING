package tv.codely.mooc.enrollments.domain;

import org.junit.jupiter.api.Test;
import tv.codely.shared.domain.UuidMother;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class EnrollmentIdShould {

    @Test
    void be_created_from_a_valid_uuid() {
        // Arrange
        String uuid = UuidMother.random();

        // Act
        EnrollmentId id = new EnrollmentId(uuid);

        // Assert
        assertEquals(uuid, id.value());
    }

    @Test
    void reject_a_value_that_is_not_a_uuid() {
        // Arrange
        String invalidValue = "e1";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new EnrollmentId(invalidValue));
    }

    @Test
    void be_equal_to_another_id_with_the_same_value() {
        // Arrange
        String uuid = UuidMother.random();

        // Act & Assert
        assertEquals(new EnrollmentId(uuid), new EnrollmentId(uuid));
    }
}
