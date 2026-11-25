package tv.codely.mooc.enrollments.domain;

import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.students.domain.StudentId;

public final class Enrollment {
    private final EnrollmentId id;
    private final StudentId    studentId;
    private final CourseId     courseId;

    public Enrollment(EnrollmentId id, StudentId studentId, CourseId courseId) {
        this.id        = requireNotNull(id, "id");
        this.studentId = requireNotNull(studentId, "studentId");
        this.courseId  = requireNotNull(courseId, "courseId");
    }

    private static <T> T requireNotNull(T value, String field) {
        if (value == null) {
            throw new IllegalArgumentException(field + " cannot be null.");
        }
        return value;
    }

    public EnrollmentId id() {
        return id;
    }

    public StudentId studentId() {
        return studentId;
    }

    public CourseId courseId() {
        return courseId;
    }

    public boolean belongsTo(StudentId studentId) {
        return this.studentId.equals(studentId);
    }

    public boolean matches(StudentId studentId, CourseId courseId) {
        return belongsTo(studentId) && this.courseId.equals(courseId);
    }
}
