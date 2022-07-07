/*
 * This class is used while unit testing with easy mock,
 * courses class.
 */
package Models;

import BusinessNDataAccessLayer.CoursesDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ndoni, tahiraj, muco
 */
public class CourseFilter {

    /**
    * Creates an object of CoursesDao class;
    */
    private CoursesDao coursesDao;

    /**
    * This is the initialization of the constructor,
    * which takes as a parameter an object of type coursesDao class.
    */
    public CourseFilter(CoursesDao coursesDao) {
        this.coursesDao = coursesDao;
    }

     /**
    * Course filter method is used to filter all courses,
    * that have the Id larger than 0, and saves them
    * to an array list.
    */
    public List<Courses> filter() {

        List<Courses> allCourses = coursesDao.all();

        List<Courses> filtered = new ArrayList<Courses>();

        for (Courses course : allCourses) {
            if (course.getCourseID() >= 1) {
                filtered.add(course);
            }
        }

        return filtered;
    }

     /**
    * Filter enrolled returns all the courses that are into enrolled
    * courses table and saves them to an array list, used for testing.
    */
    public List<EnrolledCourses> filterEnrolled() {

        List<EnrolledCourses> enrollCourse = coursesDao.enrolledCourses();

        List<EnrolledCourses> filterEnrolledCourses = new ArrayList<EnrolledCourses>();

        for (EnrolledCourses course : enrollCourse) {
            if (course.getCourseId() >= 1) {
                filterEnrolledCourses.add(course);
            }

        }
        return filterEnrolledCourses;
    }
}
