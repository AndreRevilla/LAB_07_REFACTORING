package tv.codely.mooc.enrollments.infrastructure.persistence;

import tv.codely.mooc.enrollments.domain.Enrollment;
import tv.codely.mooc.enrollments.domain.EnrollmentRepository;
import tv.codely.mooc.students.domain.StudentId;

import java.util.ArrayList;
import java.util.List;

public final class InMemoryEnrollmentRepository implements EnrollmentRepository {
    private final List<Enrollment> storage = new ArrayList<>();

    @Override
    public void save(Enrollment enrollment) {
        storage.add(enrollment);
    }

    @Override
    public List<Enrollment> searchByStudent(StudentId studentId) {
        return storage.stream()
            .filter(enrollment -> enrollment.belongsTo(studentId))
            .toList();
    }
}
