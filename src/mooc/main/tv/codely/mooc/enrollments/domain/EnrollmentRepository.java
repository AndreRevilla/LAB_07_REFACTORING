package tv.codely.mooc.enrollments.domain;

import tv.codely.mooc.students.domain.StudentId;

import java.util.List;

public interface EnrollmentRepository {
    void save(Enrollment enrollment);

    List<Enrollment> searchByStudent(StudentId studentId);
}
