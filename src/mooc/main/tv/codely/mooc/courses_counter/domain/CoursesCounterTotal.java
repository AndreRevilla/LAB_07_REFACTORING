package tv.codely.mooc.courses_counter.domain;

import tv.codely.shared.domain.IntValueObject;

public final class CoursesCounterTotal extends IntValueObject {
    public CoursesCounterTotal(Integer value) {
        super(value);

        ensureIsValid(value);
    }

    private CoursesCounterTotal() {
        super(null);
    }

    public static CoursesCounterTotal initialize() {
        return new CoursesCounterTotal(0);
    }

    public CoursesCounterTotal increment() {
        return new CoursesCounterTotal(value() + 1);
    }

    private static void ensureIsValid(Integer value) {
        if (value == null || value < 0) {
            throw new IllegalArgumentException(String.format("The courses counter total <%s> must be zero or positive", value));
        }
    }
}
