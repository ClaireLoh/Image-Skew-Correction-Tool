package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import model.detailUser;

public class RegistrationController
{
	private Connection myConn = database.dbConnect();
	private PreparedStatement ps;
	private String sql;
	
	public RegistrationController()
	{
		
	}
	
	public void register(detailUser user)
	{
		try{
			sql = "INSERT INTO user"+ "(name,password,email,phoneNo)"+ "VALUES(?,?,?,?)";
			ps = myConn.prepareStatement(sql);
			
			ps.setString(1, user.getName());
			ps.setString(2,user.getPwd());
			ps.setString(3,user.getEmail());
			ps.setString(4,user.getPhoneNo());
			
			JOptionPane.showMessageDialog(null, "Sucessfull! Now redirect to main page.....");
			
			ps.executeUpdate();
			ps.close();
			myConn.close();
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "The username already in use");
		}
	}
}