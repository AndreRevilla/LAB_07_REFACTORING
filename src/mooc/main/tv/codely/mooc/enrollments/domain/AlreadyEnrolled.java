package tv.codely.mooc.enrollments.domain;

import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.students.domain.StudentId;

public final class AlreadyEnrolled extends RuntimeException {
    public AlreadyEnrolled(StudentId studentId, CourseId courseId) {
        super(String.format(
            "The student <%s> is already enrolled in the course <%s>",
            studentId.value(),
            courseId.value()
        ));
    }
}
