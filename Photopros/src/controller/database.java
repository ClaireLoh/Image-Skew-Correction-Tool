package controller;

import java.sql.*;

public class database
{
	public static Connection dbConnect()
	{
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ips", "root", "");
			System.out.println("Connect Successful");
			return con;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args)
	{
		dbConnect();
	}
}