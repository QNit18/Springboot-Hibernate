package com.anony18.crudDemo.dao;

import com.anony18.crudDemo.entity.Course;
import com.anony18.crudDemo.entity.Instructor;
import com.anony18.crudDemo.entity.InstructorDetail;
import com.anony18.crudDemo.entity.Student;
import jakarta.persistence.TypedQuery;

import java.util.List;

public interface AppDAO {

    void save(Instructor TheInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructor(int theId);

    InstructorDetail findInstructorDetail(int theId);

    void deleteInstructorDetail(int theId);

    List<Course> findCourseByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor tempInstructor);

    Course findCourseById(int theId);
    void update(Course course);

    void deleteInstructorById(int theId);

    void deleteCourseById(int theId);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);

    Course findCourseAndStudentByCourseId(int theId);
    Student findStudentAndCourseByCourseId(int theId);

    void update(Student tempStudent);

    void deleteStudent(int theId);

}
