package com.anony18.crudDemo.dao;

import com.anony18.crudDemo.entity.Course;
import com.anony18.crudDemo.entity.Instructor;
import com.anony18.crudDemo.entity.InstructorDetail;
import com.anony18.crudDemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    // define field for entity manager
    private final EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructor(int theId) {
        Instructor instructor = entityManager.find(Instructor.class, theId);
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetail(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetail(int theId) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCourseByInstructorId(int theId) {
        // create Query
        TypedQuery<Course> query = entityManager.createQuery("FROM Course WHERE instructor.id = :data", Course.class);
        query.setParameter("data", theId);

        // execute query
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        // create query
        TypedQuery<Instructor> query = entityManager.createQuery("SELECT i FROM Instructor i JOIN FETCH i.courses where i.id = :data", Instructor.class);

        query.setParameter("data", theId);

        // excute query

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        List<Course> courses = tempInstructor.getCourses();

        // break associations of all courses for instructor
        for (Course temp : courses){
            temp.setInstructor(null); // remove the instructor from courses
        }

        // delete the instructor
        entityManager.remove(tempInstructor);
        // we only delete the instructor //  not the associated course based on our cascade types
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId){
        // retrieve the course
        Course tempCourse = entityManager.find(Course.class, theId);

        // delete the course
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                + "JOIN FETCH c.reviews "
                + "where c.id = :data", Course.class
        );
        query.setParameter("data", theId);

        // excute query
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentByCourseId(int theId) {
        // create query
        TypedQuery<Course> courseTypedQuery = entityManager.createQuery(
                "select c from Course c "
                + "JOIN FETCH c.students "
                + "where c.id = :data", Course.class
        );
        courseTypedQuery.setParameter("data", theId);
        // excute query
        return courseTypedQuery.getSingleResult();
    }

    @Override
    public Student findStudentAndCourseByCourseId(int theId) {
        // create query
        TypedQuery<Student> studentTypedQuery = entityManager.createQuery(
                "select s from Student s "
                + "JOIN FETCH s.courses "
                + "where s.id = :data", Student.class
        );
        studentTypedQuery.setParameter("data", theId);
        // excute query
        return studentTypedQuery.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(int theId) {
        Student student = entityManager.find(Student.class, theId);
        entityManager.remove(student);
    }
}
