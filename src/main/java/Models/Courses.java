/*
 * This is model class of courses table, created mainly for 
 * testing purposes. It contains all the fields of the courses table.
 */
package Models;

/**
 *
 * @author ndoni, muco, tahiraj
 */
public class Courses {
    private int CourseID;
    private String CourseName;
    private String Program;
    private String Instructor;
    private String Major;
    private String Schedule;
    private String Location;

     /**
    * Initialization of the constructor of the courses model class.
    */
    public Courses(int CourseID, String CourseName, String Program, String Instructor, String Major, String Schedule, String Location) {
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.Program = Program;
        this.Instructor = Instructor;
        this.Major = Major;
        this.Schedule = Schedule;
        this.Location = Location;
    }  

     /**
    * Below are the get methods for each field,
    * they are used to retrieve fields values in other classes.
    */
    public int getCourseID() {
        return CourseID;
    }
    
    public String getCourseName() {
        return CourseName;
    }

    public String getMajor() {
        return Major;
    } 

    public String getInstructor() {
        return Instructor;
    }

    public String getProgram() {
        return Program;
    }

    public String getSchedule() {
        return Schedule;
    }

    public String getLocation() {
        return Location;
    }
  
}
