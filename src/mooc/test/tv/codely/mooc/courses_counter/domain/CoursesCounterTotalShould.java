package tv.codely.mooc.courses_counter.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class CoursesCounterTotalShould {
    @Test
    void start_at_zero() {
        assertEquals(0, CoursesCounterTotal.initialize().value());
    }

    @Test
    void return_a_new_total_when_incremented() {
        // Arrange
        CoursesCounterTotal total = CoursesCounterTotalMother.create(4);

        // Act
        CoursesCounterTotal incremented = total.increment();

        // Assert
        assertEquals(5, incremented.value());
        assertEquals(4, total.value());
    }

    @Test
    void not_allow_a_negative_total() {
        assertThrows(IllegalArgumentException.class, () -> CoursesCounterTotalMother.create(-1));
    }

    @Test
    void not_allow_an_empty_total() {
        assertThrows(IllegalArgumentException.class, () -> CoursesCounterTotalMother.create(null));
    }
}
