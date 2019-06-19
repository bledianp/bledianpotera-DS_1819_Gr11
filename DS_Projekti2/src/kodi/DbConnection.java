package kodi;
import java.sql.*;
import java.sql.Statement;

public class DbConnection {

	private static Connection dbconnection;
	
	public static Connection getConnection()
	{
		if(dbconnection == null)
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				dbconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/siguriadb?useSSL=false", "root", "4321");
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return dbconnection ;
		
	}
	
	
	
	
}
