package tv.codely.mooc.enrollments.domain;

import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseIdMother;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentIdMother;

public final class EnrollmentMother {
    public static Enrollment create(EnrollmentId id, StudentId studentId, CourseId courseId) {
        return new Enrollment(id, studentId, courseId);
    }

    public static Enrollment of(StudentId studentId, CourseId courseId) {
        return create(EnrollmentIdMother.random(), studentId, courseId);
    }

    public static Enrollment forStudent(StudentId studentId) {
        return of(studentId, CourseIdMother.random());
    }

    public static Enrollment random() {
        return forStudent(StudentIdMother.random());
    }
}
