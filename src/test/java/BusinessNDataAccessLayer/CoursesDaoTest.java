/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessNDataAccessLayer;

import Models.Courses;
import Models.EnrolledCourses;
import PresentationLayer.AddCourseForm;
import PresentationLayer.DropCourseForm;
import PresentationLayer.MyCoursesListForm;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class CoursesDaoTest {
    public static CoursesDao coursesDao = new CoursesDao();

    /**
     * Test of checkBook method, of class CoursesDao.
     */
    @Test
    public void testCheckBook() {
        Courses course1 = new Courses(5,"Calculus 1","Major", "Akli Fundo", "Computer Engineering", "Tue 17:00-19:00", "AC33");
        Courses course2 = new Courses(6,"Calculus 3","Minor", "Denisa Salillari", "Computer Engineering", "Fri 12:00-14:00", "BB19");
        Courses course3 = new Courses(7,"Algebra","Major", "Dritan Nace", "Computer Engineering", "Tue 09:00-12:00", "AC33");
        Courses course4 = new Courses(8,"Signal Theory","Major", "Ilir Shinko", "Computer Engineering", "Thur 10:00-12:00", "BB19");
        
	coursesDao.save(course1);
	coursesDao.save(course2);
	coursesDao.save(course3);
	coursesDao.save(course4);
        
        List<Courses> result = coursesDao.getOrderedCourses();
        
        boolean value = CoursesDao.checkBook("1");
        
        Assert.assertEquals(value,result.get(0).getCourseName().equals("Networking"));
	Assert.assertEquals("Calculus 1", result.get(4).getCourseName());
        Assert.assertEquals("Major", result.get(4).getProgram());
        Assert.assertEquals("Mon 16:00 - 18:00", result.get(0).getSchedule());
        Assert.assertEquals("3FE", result.get(0).getLocation());
	Assert.assertEquals(6, result.get(5).getCourseID());
	Assert.assertEquals("Dritan Nace", result.get(6).getInstructor());
        Assert.assertEquals("Computer Engineering", result.get(7).getMajor());
	Assert.assertEquals(8, result.size());
    }

    /**
     * Test of all method, of class CoursesDao.
     */
    @Test
    public void testAll() {
	List<Courses> result = coursesDao.all();
	boolean status=true;
        if(result.size()>0)
            status=false;

	Assert.assertEquals(status, result.isEmpty());
    }

    /**
     * Test of CourseValidate method, of class CoursesDao.
     */
    @Test
    public void testCourseValidate() {
        AddCourseForm addCourse = new AddCourseForm();
        boolean status = addCourse.validateCourse("1");
        CoursesDao.major="Computer Engineering";
        
        List<Courses> result = coursesDao.getOrderedCourses();
        
        Assert.assertEquals(CoursesDao.major, result.get(0).getMajor());
    }

    /**
     * Test of EnrollingCourse method, of class CoursesDao.
     */
    @Test
    public void testEnrollingCourse() {
        AddCourseForm addCourse = new AddCourseForm();
        int result = addCourse.enrollCourse(4, "3", "2021-06-02", "2021-07-02");
        CoursesDao.major="Computer Engineering";
        boolean enrolledSuccessfully= false;
        if(result>0)
            enrolledSuccessfully = true;
        System.out.println("Logimi "+ enrolledSuccessfully);
        
        boolean validate1 = addCourse.validateCourse("3");
        boolean validate2 = addCourse.validateUser("1");
        
        Assert.assertEquals(true, enrolledSuccessfully);
        Assert.assertEquals(true, enrolledSuccessfully);
    }

    /**
     * Test of DropingCourse method, of class CoursesDao.
     */
    @Test
    public void testDropingCourse() {
        AddCourseForm addCourse = new AddCourseForm();
        int result1 = addCourse.enrollCourse(4, "3", "2021-06-02", "2021-07-02");
        CoursesDao.major="Computer Engineering";
        boolean enrolledSuccessfully= false;
        if(result1>0)
            enrolledSuccessfully = true;
        
        DropCourseForm dropCourse = new DropCourseForm();
        int result2 = dropCourse.dropCourse(4,"3");
        boolean droppedSuccess=true;
        if(result2<0)
            droppedSuccess=false;      
      
       Assert.assertEquals(enrolledSuccessfully, droppedSuccess);
    }

    /**
     * Test of CheckEnrollCourses method, of class CoursesDao.
     */
    @Test
    public void testCheckEnrollCourses() {
         List<EnrolledCourses> result = coursesDao.enrolledCourses();
         
         boolean status=false;
         if(result.get(0).getCourseId() == 1)
             status=true;
         
         Assert.assertEquals(CoursesDao.CheckEnrollCourses(1),status);
    }

    /**
     * Test of Check method, of class CoursesDao.
     */
    
    @After
    public void cleanDatabase() {
	try {
	    PreparedStatement ps = CoursesDao.getC().prepareStatement("delete from courses where CourseID>4");
	    ps.execute();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    } 
}
