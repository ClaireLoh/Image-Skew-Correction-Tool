package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class selectdbController 
{
	
	private Connection myConn = database.dbConnect();
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	private int numberRow = 0;
	
	public selectdbController()
	{
		
	}
	public int checkQuota(int ID){
		//boolean valid = false;
		int count = 0;
		try{
			sql = "SELECT count(image_id) FROM originalimage WHERE user_id = ? ";
			ps = myConn.prepareStatement(sql);
			ps.setInt(1, ID);
			rs = ps.executeQuery();
			while(rs.next())
			{
				count=rs.getInt(1);
			}
			rs.close();
			ps.close();
			myConn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	public void updateQuota(int ID, int qt)
	{
		try{
			sql = "UPDATE user SET quota = quota + ? WHERE user_id = ?";
			ps = myConn.prepareStatement(sql);
			ps.setInt(1, qt);
			ps.setInt(2, ID);
			ps.executeUpdate();
			
			ps.close();
			myConn.close();
		}
		catch(Exception w)
		{
			w.printStackTrace();
		}
	}

}
