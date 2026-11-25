package tv.codely.mooc.enrollments.domain;

import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.shared.domain.DomainError;

public final class AlreadyEnrolled extends DomainError {
    public AlreadyEnrolled(StudentId studentId, CourseId courseId) {
        super(
            "already_enrolled",
            String.format(
                "The student <%s> is already enrolled in the course <%s>",
                studentId.value(),
                courseId.value()
            )
        );
    }
}
