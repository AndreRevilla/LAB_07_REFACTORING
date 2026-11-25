package tv.codely.mooc.enrollments.domain;

import tv.codely.shared.domain.UuidMother;

public final class EnrollmentIdMother {
    public static EnrollmentId create(String value) {
        return new EnrollmentId(value);
    }

    public static EnrollmentId random() {
        return create(UuidMother.random());
    }
}
