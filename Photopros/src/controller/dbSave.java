package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.detailImage;

public class dbSave {
	private Connection myConn = database.dbConnect();
	private PreparedStatement ps;
	private String sql1;
	private String sql2;
	
	public dbSave()
	{
		
	}
	
	public void saveOriPath(detailImage path)
	{
		try{
			sql1 = "INSERT INTO originalimage"+"(image_name,image_path,user_id)"+"VALUES(?,?,?)";
			ps = myConn.prepareStatement(sql1);
			
			ps.setString(1, path.getOriImage_name());
			ps.setString(2, path.getOriImage_path());
			ps.setInt(3, path.getUser_id());
			
			ps.executeUpdate();
			ps.close();
			myConn.close();
			}catch(Exception e)
		{
				e.printStackTrace();
		}
	}
	
	public void saveProPath(detailImage fPath)
	{
		try{
			sql2 = "INSERT INTO processedimage"+"(name,processedPath,angle)"+"VALUES(?,?,?)";
			ps = myConn.prepareStatement(sql2);
			
			ps.setString(1, fPath.getProImage_name());
			ps.setString(2, fPath.getProImage_path());
			ps.setDouble(3, fPath.getProImage_angle());
			
			ps.executeUpdate();
			ps.close();
			myConn.close();
			}
		catch(Exception e)
		{
				e.printStackTrace();
		}
	}
	

}
