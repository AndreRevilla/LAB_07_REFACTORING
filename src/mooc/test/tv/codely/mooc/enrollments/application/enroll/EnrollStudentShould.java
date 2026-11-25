package tv.codely.mooc.enrollments.application.enroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseIdMother;
import tv.codely.mooc.courses.domain.CourseMother;
import tv.codely.mooc.courses.domain.CourseNotExist;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.mooc.enrollments.domain.AlreadyEnrolled;
import tv.codely.mooc.enrollments.domain.Enrollment;
import tv.codely.mooc.enrollments.domain.EnrollmentId;
import tv.codely.mooc.enrollments.domain.EnrollmentIdMother;
import tv.codely.mooc.enrollments.domain.EnrollmentMother;
import tv.codely.mooc.enrollments.domain.EnrollmentPolicy;
import tv.codely.mooc.enrollments.domain.EnrollmentRepository;
import tv.codely.mooc.enrollments.domain.StudentLookup;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentIdMother;
import tv.codely.mooc.students.domain.StudentNotExist;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

final class EnrollStudentShould {

    private CourseRepository     courseRepository;
    private StudentLookup        studentLookup;
    private EnrollmentRepository enrollmentRepository;
    private EnrollStudent        enroller;

    private EnrollmentId enrollmentId;
    private StudentId    studentId;
    private CourseId     courseId;

    @BeforeEach
    void setUp() {
        courseRepository     = mock(CourseRepository.class);
        studentLookup        = mock(StudentLookup.class);
        enrollmentRepository = mock(EnrollmentRepository.class);
        enroller             = new EnrollStudent(
            courseRepository,
            studentLookup,
            enrollmentRepository,
            new EnrollmentPolicy()
        );

        enrollmentId = EnrollmentIdMother.random();
        studentId    = StudentIdMother.random();
        courseId     = CourseIdMother.random();
    }

    @Test
    void enroll_student_when_course_and_student_exist() {
        // Arrange
        givenCourseExists();
        givenStudentExists();
        givenStudentEnrollments(List.of());

        // Act
        enroller.enroll(enrollmentId, studentId, courseId);

        // Assert
        ArgumentCaptor<Enrollment> captor = ArgumentCaptor.forClass(Enrollment.class);
        verify(enrollmentRepository, times(1)).save(captor.capture());
        Enrollment saved = captor.getValue();
        assertEquals(enrollmentId, saved.id());
        assertEquals(studentId, saved.studentId());
        assertEquals(courseId, saved.courseId());
    }

    @Test
    void fail_when_course_does_not_exist() {
        // Arrange
        when(courseRepository.search(courseId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CourseNotExist.class, () -> enroller.enroll(enrollmentId, studentId, courseId));
        verify(enrollmentRepository, never()).save(any());
    }

    @Test
    void fail_when_student_does_not_exist() {
        // Arrange
        givenCourseExists();
        when(studentLookup.exists(studentId)).thenReturn(false);

        // Act & Assert
        assertThrows(StudentNotExist.class, () -> enroller.enroll(enrollmentId, studentId, courseId));
        verify(enrollmentRepository, never()).save(any());
    }

    @Test
    void fail_when_student_is_already_enrolled() {
        // Arrange
        givenCourseExists();
        givenStudentExists();
        givenStudentEnrollments(List.of(EnrollmentMother.of(studentId, courseId)));

        // Act & Assert
        assertThrows(AlreadyEnrolled.class, () -> enroller.enroll(enrollmentId, studentId, courseId));
        verify(enrollmentRepository, never()).save(any());
    }

    private void givenCourseExists() {
        when(courseRepository.search(courseId)).thenReturn(Optional.of(CourseMother.random()));
    }

    private void givenStudentExists() {
        when(studentLookup.exists(studentId)).thenReturn(true);
    }

    private void givenStudentEnrollments(List<Enrollment> enrollments) {
        when(enrollmentRepository.searchByStudent(studentId)).thenReturn(enrollments);
    }
}
