package tv.codely.apps.mooc.backend.controller.courses_counter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import tv.codely.mooc.courses_counter.domain.CoursesCounterNotInitialized;
import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.shared.domain.bus.query.QueryBus;

final class CoursesCounterGetControllerErrorMappingShould {

	@Test
	void map_a_not_initialized_counter_to_not_found() {
		CoursesCounterGetController controller = new CoursesCounterGetController(
			mock(QueryBus.class),
			mock(CommandBus.class)
		);

		assertEquals(HttpStatus.NOT_FOUND, controller.errorMapping().get(CoursesCounterNotInitialized.class));
	}
}
