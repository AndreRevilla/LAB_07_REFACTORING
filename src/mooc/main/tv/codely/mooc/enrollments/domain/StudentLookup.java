package tv.codely.mooc.enrollments.domain;

import tv.codely.mooc.students.domain.StudentId;

public interface StudentLookup {
    boolean exists(StudentId studentId);
}
