package tv.codely.mooc.courses_counter.domain;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseIdMother;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class CoursesCounterShould {
    @Test
    void increment_the_total_and_remember_the_course() {
        // Arrange
        CoursesCounter counter  = CoursesCounterMother.random();
        int            before   = counter.total().value();
        CourseId       courseId = CourseIdMother.random();

        // Act
        counter.increment(courseId);

        // Assert
        assertEquals(before + 1, counter.total().value());
        assertTrue(counter.hasIncremented(courseId));
    }

    @Test
    void not_count_the_same_course_twice() {
        // Arrange
        CourseId       courseId = CourseIdMother.random();
        CoursesCounter counter  = CoursesCounterMother.withOne(courseId);

        // Act + Assert
        assertThrows(CourseAlreadyCounted.class, () -> counter.increment(courseId));
        assertEquals(1, counter.total().value());
    }

    @Test
    void not_expose_its_internal_list_of_courses() {
        // Arrange
        CoursesCounter counter = CoursesCounterMother.random();

        // Act + Assert
        assertThrows(
            UnsupportedOperationException.class,
            () -> counter.existingCourses().add(CourseIdMother.random())
        );
    }

    @Test
    void not_change_when_the_list_used_to_create_it_changes() {
        // Arrange
        List<CourseId> courses = new ArrayList<>();
        courses.add(CourseIdMother.random());
        CoursesCounter counter = CoursesCounterMother.create(
            CoursesCounterIdMother.random(),
            CoursesCounterTotalMother.one(),
            courses
        );

        // Act
        courses.add(CourseIdMother.random());

        // Assert
        assertEquals(1, counter.existingCourses().size());
    }
}
