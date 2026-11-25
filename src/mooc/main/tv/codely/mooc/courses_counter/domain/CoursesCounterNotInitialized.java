package tv.codely.mooc.courses_counter.domain;

import tv.codely.shared.domain.DomainError;

public final class CoursesCounterNotInitialized extends DomainError {
    public CoursesCounterNotInitialized() {
        super("courses_counter_not_initialized", "The courses counter has not been initialized");
    }
}
