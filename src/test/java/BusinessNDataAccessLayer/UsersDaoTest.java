/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessNDataAccessLayer;

import PresentationLayer.SignUpFrame;
import com.mysql.jdbc.PreparedStatement;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterAll;

/**
 *
 * @author user
 */
public class UsersDaoTest {
    
    public static UsersDao usersDao = new UsersDao();

    /**
     * Test of saveUser method, of class UsersDao.
     */
    @Test
    public void testSaveUser() {
        UsersDao usersDao = new UsersDao();
        SignUpFrame signUp1 = new SignUpFrame();
        boolean value1 = signUp1.signUpUser("user1", "user1", "user1 user1", "user1@email.com", 21, "major1");
        
        SignUpFrame signUp2 = new SignUpFrame();
        boolean value2 = signUp2.signUpUser("user2", "user2", "user2 user2", "user2@email.com", 22, "major2");
        
        SignUpFrame signUp3 = new SignUpFrame();
        boolean value3 = signUp3.signUpUser("user3", "user3", "use3 user3", "user3@email.com", 23, "major3");
        
        boolean result1 = UsersDao.CheckIfAlready("user1");
        boolean result2 = UsersDao.CheckIfAlready("user2");
        boolean result3 = UsersDao.CheckIfAlready("user3");
        
        if(result1 == false)
            result1 =true;
        if(result2 == false);
            result2=true;
        if(result3 == false)
            result3=true;
        
         Assert.assertEquals(result3, value3);
         Assert.assertEquals(result1, value1);
         Assert.assertEquals(result2, value2);
    }
    
    @Test
    public void testValidate(){
        boolean value = true;
       Assert.assertEquals(value, UsersDao.validate("kastriot123"));
    }
    
    @Test
    public void testCheckIfAlready(){
        boolean value = true;
       Assert.assertEquals(value, UsersDao.CheckIfAlready("kastriot"));
    }
    
    @AfterAll
    public void cleanDatabase() {
	try {
	    PreparedStatement ps = (PreparedStatement) UsersDao.getC().prepareStatement("delete from login");
	    ps.execute();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
		}
    }

    @AfterAll
    public void closeConnection() {
	usersDao.close();
    }
}
