package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
	
	private Connection myConn;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	private String query;
	private String queryQuota;
	private String username;
	
	
	public LoginController()
	{
		
	}
	public boolean doLogin(String name,String password){
		myConn = database.dbConnect();
		boolean valid = false;
		try{
			sql = "SELECT password FROM user WHERE name = ? ";
			ps = myConn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
				if(password.equals(rs.getString(1))){
					valid = true;
				}
			}
			rs.close();
			ps.close();
			myConn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return valid;
	}
	
	public int getID(String name)
	{
		myConn = database.dbConnect();
		int id=0;
		try{
			query = "SELECT user_id FROM user WHERE name =?";
			ps = myConn.prepareStatement(query);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next())
			{
				id=rs.getInt(1);
			}
			
			rs.close();
			ps.close();
			myConn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return id;
	}
	
	public int getQuota(int ID)
	{
		myConn = database.dbConnect();
		int quota = 0;
		
		try{
			queryQuota = "SELECT quota FROM user WHERE user_id=?";
			ps =myConn.prepareStatement(queryQuota);
			ps.setInt(1, ID);
			rs = ps.executeQuery();
			while(rs.next())
			{
				quota = rs.getInt(1);
			}
			rs.close();
			ps.close();
			myConn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return quota;
	}
	
	public String getName(int ID)
	{
		myConn = database.dbConnect();
		try
		{
			sql = "SELECT name FROM user WHERE user_id = ?";
			ps = myConn.prepareStatement(sql);
			ps.setInt(1,ID);
			rs = ps.executeQuery();
			while(rs.next())
			{
				username = rs.getString(1);
			}
			rs.close();
			ps.close();
			myConn.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return username;
	}
}