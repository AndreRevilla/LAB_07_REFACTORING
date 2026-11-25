package tv.codely.apps.mooc.backend.controller.courses_counter;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tv.codely.mooc.courses_counter.application.find.CoursesCounterResponse;
import tv.codely.mooc.courses_counter.application.find.FindCoursesCounterQuery;
import tv.codely.mooc.courses_counter.domain.CoursesCounterNotInitialized;
import tv.codely.shared.domain.DomainError;
import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.domain.bus.query.QueryHandlerExecutionError;
import tv.codely.shared.infrastructure.spring.ApiController;

@RestController
public final class CoursesCounterGetController extends ApiController {

	public CoursesCounterGetController(QueryBus queryBus, CommandBus commandBus) {
		super(queryBus, commandBus);
	}

	@GetMapping("/courses-counter")
	public HashMap<String, Integer> index() throws QueryHandlerExecutionError {
		CoursesCounterResponse response = ask(new FindCoursesCounterQuery());

		HashMap<String, Integer> body = new HashMap<>();
		body.put("total", response.total());

		return body;
	}

	@Override
	public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
		HashMap<Class<? extends DomainError>, HttpStatus> mapping = new HashMap<>();
		mapping.put(CoursesCounterNotInitialized.class, HttpStatus.NOT_FOUND);

		return mapping;
	}
}
