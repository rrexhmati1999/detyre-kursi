/*
 * This is model class of enrolled courses table,
 * contains all the fields of that table.
 */
package Models;

/**
 *
 * @author ndoni, muco, tahiraj
 */
public class EnrolledCourses {
    private int courseId;
    private String userId;
    private String ifDate;
    private String rtDate;

     /**
    * Initializes the constructor of this class.
    */
    public EnrolledCourses(int courseId, String userId, String ifDate, String rtDate) {
        this.courseId = courseId;
        this.userId = userId;
        this.ifDate = ifDate;
        this.rtDate = rtDate;
    }

     /**
    * Returns value of an enrolled course id.
    */
    public int getCourseId() {
        return courseId;
    }    
}
