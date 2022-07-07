/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessNDataAccessLayer;

import PresentationLayer.LoginForm;
import PresentationLayer.UserLogin;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class LoginDaoTest {
    
   public static LoginDao loginDao = new LoginDao();
   
    /**
     * Test of validate method, of class LoginDao. Execution of two cases false and true.
     */
    @Test
    public void testValidateLoginFalse() {
        LoginForm loginForm1 = new LoginForm();
        boolean status1 = loginForm1.LoginUser("kristi", "kristi1233");
        boolean result1 = false;
        Assert.assertEquals(result1,status1);
    }
    
    @Test
    public void testValidateLoginTrue(){
        LoginForm loginForm2 = new LoginForm();
        boolean status2 = loginForm2.LoginUser("kristi", "kristi123");
        boolean result2 = true;
        Assert.assertEquals(result2,status2);
        
        UserLogin userLogin = new UserLogin();
        boolean resultUserLogin=userLogin.validateUser("kristi123");
        
        Assert.assertEquals(result2, resultUserLogin);
    }
}
