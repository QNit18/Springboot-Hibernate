package com.anony18.crudDemo;

import com.anony18.crudDemo.dao.AppDAO;
import com.anony18.crudDemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner->{
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);

			//createInstructorWithCourse(appDAO);

			//findInstructorWithCourses(appDAO);
			//findCOursesForInstructor(appDAO);

			//findInstructorWithCoursesJoinFetch(appDAO);

			//updateInstructor(appDAO);
			//updateCourse(appDAO);

			//deleteInstructorWithCourse(appDAO);
			//deleteCourseById(appDAO);


			// createCourseAndReviews(appDAO);
			// retrieveCourseAndReviews(appDAO);
			//deleteCourseAndReviews(appDAO);

			//createCourseAndStudents(appDAO);
			//findCourseAndStudents(appDAO);
			//findStudentsAndCourse(appDAO);
			//addMoreCourseForStuden(appDAO);
			//deleteCourse(appDAO);
			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId = 2;
		System.out.println("Deleting student: " + theId);
		appDAO.deleteStudent(theId);
		System.out.println("Done!");
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void addMoreCourseForStuden(AppDAO appDAO) {
		int theId = 2;
		Student tempStudent = appDAO.findStudentAndCourseByCourseId(theId);

		// create more course
		Course course1 = new Course("Hello world");
		Course course2 = new Course("Dev what meaning ?");

		// add course to student
		tempStudent.addCourse(course1);
		tempStudent.addCourse(course2);

		System.out.println("Saving student: " + tempStudent);
		System.out.println("Saving courses: " + tempStudent.getCourses());

		appDAO.update(tempStudent);
		System.out.println("Done!");

	}

	private void findStudentsAndCourse(AppDAO appDAO) {
		int theId = 1;
		Student student = appDAO.findStudentAndCourseByCourseId(theId);

		System.out.println("Loaded Student: " + student);
		System.out.println("Students: " + student.getCourses());
		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndStudentByCourseId(theId);

		System.out.println("Loaded course:  " + tempCourse);
		System.out.println("Students: " + tempCourse.getStudents());
		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("Pac-man dodod");
		// create the students
		Student tempStudent1 = new Student("Van", "Quang","vanquang1833@gmail.com");
		Student tempStudent2 = new Student("Quoc", "Hung","vanquang1833@gmail.com");
		Student tempStudent3 = new Student("Thi", "Hung","vanquang1833@gmail.com");

		// add students to the course
		tempCourse.addStudent(tempStudent1);
		tempCourse.addStudent(tempStudent2);
		tempCourse.addStudent(tempStudent3);

		// save the course and associated students
		System.out.println("Saving the course" + tempCourse);
		System.out.println("Associated students: " + tempCourse.getStudents());
		appDAO.save(tempCourse);
		System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting course id: " + theId);

		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		// get the course and reviews
		int theId = 10;
		Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println(tempCourse);

		// print the reviews
		System.out.println(tempCourse.getReviews());
		System.out.println("Done!");
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course tempCourse = new Course("Pacman-How to Score One Million Points");

		// add some reviews
		tempCourse.add(new Review("Great course .. love it"));
		tempCourse.add(new Review("Perfect .. love it"));
		tempCourse.add(new Review("Oh My God .. love it"));
		// save the course
		appDAO.save(tempCourse);
		System.out.println("DONE!");
	}

	private void deleteCourseById(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Deleting course id: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done");
	}

	private void deleteInstructorWithCourse(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Finding course id: " + theId);
		Course tempCourse = appDAO.findCourseById(theId);

		System.out.println("Updatinggg");
		tempCourse.setTitle("Enjoy cai moment nay!!!");
		appDAO.update(tempCourse);
		System.out.println("Done!!!!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Updatinggg");
		tempInstructor.setLastName("Van");
		tempInstructor.setFirstName("Quang");
		appDAO.update(tempInstructor);
		System.out.println("Done");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId = 1;

		// find the íntructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("The associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findCOursesForInstructor(AppDAO appDAO) {
		int theId = 1;

		// find the instructor
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);

		// find courses for instructor
		List<Course> course = appDAO.findCourseByInstructorId(theId);

		// asssociate the objects
		tempInstructor.setCourses(course);

		System.out.println("The associated courses: " + tempInstructor.getCourses());
		System.out.println("DONE!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("The associated courses: " + tempInstructor.getCourses());
		System.out.println("DONE!");
	}

	private void createInstructorWithCourse(AppDAO appDAO) {
		// create instructor
		Instructor tempInstructor
				= new Instructor("Susan", "Last", "hihi123@gmail.com");

		// create instructor detail
		InstructorDetail temmpInstructorDetail
				= new InstructorDetail("https://www.youtube.com","Video Games");

		// associate the object
		tempInstructor.setInstructorDetail(temmpInstructorDetail);

		// create some course
		Course tempCourse1 = new Course("Air guitar - The Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball MasterClass");

		// aadd courses to instrcutor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		// save the instructor
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("The course: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 1;
		appDAO.deleteInstructorDetail(theId);
		System.out.println("Delete InstrucDetails done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor detail id: " + theId);
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetail(theId);
		System.out.println("InstructorDetail: " + tempInstructorDetail);
		System.out.println("The asscociated instructor: " + tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("The instructor will delete: " + theId);
		appDAO.deleteInstructor(theId);
		System.out.println("The instructor deleted!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("The instructor: " + tempInstructor);
		System.out.println("The associated instructorDetail only: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// create instructor
		Instructor tempInstructor
				= new Instructor("Quan", "Minh", "hihi123@gmail.com");

		// create instructor detail
		InstructorDetail temmpInstructorDetail
				= new InstructorDetail("https://www.youtube.com/@nobicoto33231","coding chill");

		// associate the object
		tempInstructor.setInstructorDetail(temmpInstructorDetail);

		// save the instructor
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
		System.out.println("Done!");
	}

}
