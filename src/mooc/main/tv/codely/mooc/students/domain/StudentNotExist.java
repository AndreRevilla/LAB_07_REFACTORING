package tv.codely.mooc.students.domain;

import tv.codely.shared.domain.DomainError;

public final class StudentNotExist extends DomainError {
    public StudentNotExist(StudentId studentId) {
        super("student_not_exist", String.format("The student <%s> doesn't exist", studentId.value()));
    }
}
