package tv.codely.mooc.enrollments.domain;

import tv.codely.mooc.students.domain.StudentId;

public final class StudentNotExist extends RuntimeException {
    public StudentNotExist(StudentId studentId) {
        super(String.format("The student <%s> doesn't exist", studentId.value()));
    }
}
