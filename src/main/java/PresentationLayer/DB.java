/*
 * This class is used to create a connection with the project database.
 */
package PresentationLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 *
 * @author ndoni, tahiraj, muco
 */
public class DB {
    public static Connection getConnection() {
		Connection con=null;
		try{
                            Properties props = new Properties(); 
    props.put("user", "root");
    props.put("password", "12345678");
    props.put("useUnicode", "true");
    props.put("useServerPrepStmts", "false"); // use client-side prepared statement
    props.put("characterEncoding", "UTF-8"); // ensure charset is utf8 here

			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectdatabase",props);
		}catch(ClassNotFoundException | SQLException e){System.out.println(e);}
		return con;
	}
}
