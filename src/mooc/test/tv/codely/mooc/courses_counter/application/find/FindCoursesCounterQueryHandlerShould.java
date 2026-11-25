package tv.codely.mooc.courses_counter.application.find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses_counter.CoursesCounterModuleUnitTestCase;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterMother;
import tv.codely.mooc.courses_counter.domain.CoursesCounterNotInitialized;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class FindCoursesCounterQueryHandlerShould extends CoursesCounterModuleUnitTestCase {
    private FindCoursesCounterQueryHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new FindCoursesCounterQueryHandler(new CoursesCounterFinder(repository));
    }

    @Test
    void find_an_existing_courses_counter() {
        CoursesCounter          counter  = CoursesCounterMother.random();
        FindCoursesCounterQuery query    = new FindCoursesCounterQuery();
        CoursesCounterResponse  response = CoursesCounterResponseMother.create(counter.total().value());

        shouldSearch(counter);

        assertEquals(response, handler.handle(query));
    }

    @Test
    void throw_a_domain_error_when_the_courses_counter_does_not_exist() {
        FindCoursesCounterQuery query = new FindCoursesCounterQuery();

        shouldSearch();

        CoursesCounterNotInitialized error = assertThrows(
            CoursesCounterNotInitialized.class,
            () -> handler.handle(query)
        );
        assertEquals("courses_counter_not_initialized", error.errorCode());
    }
}
