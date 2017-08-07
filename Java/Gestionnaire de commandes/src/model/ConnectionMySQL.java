package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionMySQL {

	private static String url = "//localhost:3306/ecommerce?verifyServerCertificate=false&useSSL=true";
	private static String identifiant = "root";
	private static String mdp = "";	
	
	public static Connection getConnection(){

		Connection con = null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql:"+url,identifiant,mdp);

            
		}catch(Exception e){
			System.err.println(e);
			con = null;
		}
		return con;
	}

	
}
