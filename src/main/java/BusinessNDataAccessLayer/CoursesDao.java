/**
 * This class contains data access object for courses,
 * it contacts to the database to save a new course, to retrieve courses from database
 * , also to check if the user has already enrolled in the course before.
 */
package BusinessNDataAccessLayer;

import Models.Courses;
import Models.EnrolledCourses;
import java.sql.*;
import PresentationLayer.DB;
import PresentationLayer.MainPage;
import java.util.ArrayList;
import java.util.List;

public class CoursesDao {

    /**
     * Here is set the major of the logged in user, it is used in the methods of
     * this class.
     */
    public static String major = MainPage.Major;

    /**
     * This class is used to check if a specific course exists in the database.
     */
    public static boolean checkBook(String courselno) {
        boolean status = false;
        try {
            Connection con = DB.getConnection(); //Gets the connection from the database
            PreparedStatement ps = con.prepareStatement("select * from courses where CourseID=?");
            ps.setString(1, courselno);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    /**
     * All method returns all the courses from the databases and saves them into
     * an array list
     */
    public List<Courses> all() {

        List<Courses> allCourses = new ArrayList<Courses>();

        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from courses");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int courseID = rs.getInt("CourseID");
                String courseName = rs.getString("CourseName");
                String program = rs.getString("Program");
                String instructor = rs.getString("Instructor");
                String major = rs.getString("Major");
                String schedule = rs.getString("Schedule");
                String location = rs.getString("Location");
                allCourses.add(new Courses(courseID, courseName, program, instructor, major, schedule, location));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allCourses;

    }

    /**
     * Enrolled courses returns a type list of courses that are already enrolled
     * by users.
     */
    public List<EnrolledCourses> enrolledCourses() {

        List<EnrolledCourses> enrollCourses = new ArrayList<EnrolledCourses>();

        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from enrollcourses");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int courseID = rs.getInt("CourseID");
                String userID = rs.getString("UserID");
                String issueDate = rs.getString("IssueDate");
                String returnDate = rs.getString("ReturnDate");
                enrollCourses.add(new EnrolledCourses(courseID, userID, issueDate, returnDate));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return enrollCourses;

    }

    /**
     * Save method is being used to save a new course into the database it is
     * being used mostly for testing purposes.
     */
    public void save(Courses course) {
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into courses (CourseID, CourseName, Program, Instructor, Major, Schedule, Location) values (?,?,?,?,?,?,?)");
            ps.setInt(1, course.getCourseID());
            ps.setString(2, course.getCourseName());
            ps.setString(3, course.getProgram());
            ps.setString(4, course.getInstructor());
            ps.setString(5, course.getMajor());
            ps.setString(6, course.getSchedule());
            ps.setString(7, course.getLocation());

            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Course validate returns a true or false, it practically confirms if the
     * course id the users is trying to enroll is in alignment with the major of
     * the logged in user.
     */
    public static boolean CourseValidate(String CourseID) {
        boolean status = false;
        try (Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from courses where CourseID = ? and Major='" + MainPage.Major + "'");
            ps.setString(1, CourseID);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    /**
     * This class is used to check if the user that is trying to enroll or drop
     * a course is existing in the database.
     */
    public static boolean UserValidate(String LoginID) {
        boolean status = false;
        try (Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from login where LoginID = ?");
            ps.setString(1, LoginID);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    /**
     * Enrolling course to store the course which the user is enrolling. Saves
     * the course data to database.
     */
    public static int EnrollingCourse(int CourseID, String LoginID, String IDate, String RDate) {
        int status = 0;
        try {

            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into enrollcourses values(?,?,?,?)");
            ps.setInt(1, CourseID);
            ps.setString(2, LoginID);
            ps.setString(3, IDate);
            ps.setString(4, RDate);
            status = ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    /**
     * This class is used to get all courses from the database and order them by
     * id it saves the records to a list. This class is used mostly for testing
     * purposes.
     */
    public List<Courses> getOrderedCourses() {
        List<Courses> allCourses = new ArrayList<Courses>();
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM courses ORDER BY CourseID");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int courseId = rs.getInt("CourseID");
                String courseName = rs.getString("CourseName");
                String program = rs.getString("Program");
                String instructor = rs.getString("Instructor");
                String major = rs.getString("major");
                String schedule = rs.getString("Schedule");
                String location = rs.getString("Location");

                allCourses.add(new Courses(courseId, courseName, program, instructor, major, schedule, location));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allCourses;
    }

    /**
     * Dropping course takes two parameters the user id and course id. This
     * class removes a course from enrolled courses list of the loged in user.
     */
    public static int DropingCourse(int CourseID, String LoginID) {
        int status = 0;
        try {

            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from enrollcourses where CourseID=? and UserID=?");
            ps.setInt(1, CourseID);
            ps.setString(2, LoginID);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    /**
     * This method is used to display the courses of which the user has already
     * enrolled.
     */
    public static boolean CheckEnrollCourses(int CourseID, String LoginID) {
        boolean status = false;
        try (Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from enrollcourses where CourseID=? and UserID=?");
            ps.setInt(1, CourseID);
             ps.setString(2, LoginID);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    /**
     * This method is used to check in how many courses has the user logged in,
     * and if he has exceeded the limit.
     */
    public static int Check(String LoginID) {
        boolean status = false;
        int num = 0;
        try (Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select * from course_count UserID=?");
            ps.setString(2, LoginID);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            num = rs.getInt("CourseNo");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (num == 5) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * This method is used to return the database connection.
     */
    public static Connection getC() {
        Connection con = DB.getConnection();
        return con;
    }

}
