package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.FileController;
import controller.LoginController;
import model.detailUser;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class LoginPage extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame loginFrame;
	private JLabel lblTitle;
	private JLabel label;
	private String [] lblNames = {"Username","Password"};
	private JTextField txtField;
	private JPasswordField passField;
	private String [] btnNames = {"Submit","Reset","Back"};
	private JButton btn;
	private JLabel lblDot;
	private JLabel label_2;
	private JLabel lblNewLabel;
	private JLabel lblHelpToManage;
	private JLabel lblHelpToCorrect;
	private JLabel lblLessManualProcess;
	private JLabel label_1;

	public LoginPage()
	{	
		init();
		loginFrame.setVisible(true);
		//loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void init()
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int widthScreen = gd.getDisplayMode().getWidth()-30;
		int heightScreen = gd.getDisplayMode().getHeight()-100;
		
		loginFrame = new JFrame();
		loginFrame.getContentPane().setBackground(SystemColor.activeCaption);
		loginFrame.getContentPane().setLayout(null);
		loginFrame.setBounds(0,0,widthScreen+6,heightScreen+33);
			lblTitle = new JLabel("Login");
			lblTitle.setFont(new Font("Pristina", Font.BOLD, 26));
			lblTitle.setBounds(235,128,169,46);
		loginFrame.getContentPane().add(lblTitle);
		int y=240;
		for(String name:lblNames)
		{
			label = new JLabel (name);
			label.setBounds(10,y,80,20);
			loginFrame.getContentPane().add(label);
			y+=50;
		}
		y=240;
		for(int i=0;i<lblNames.length;i++)
		{
			lblDot = new JLabel(":");
			lblDot.setBounds(72,y,3,20);
			loginFrame.getContentPane().add(lblDot);
			y+=50;
		}
		
	
			txtField = new JTextField(50);
			txtField.setBounds(165,240,252,29);
			loginFrame.getContentPane().add(txtField);
			
			passField = new JPasswordField();
			passField.setBounds(165,290,252,29);
			loginFrame.getContentPane().add(passField);
			
			
			ImageIcon loginIcon = new ImageIcon("C:\\Users\\user\\workspaceNeon\\Photopros\\bg.jpg");
			Image login = loginIcon.getImage();
			Image loginPage = login.getScaledInstance(672, 663, java.awt.Image.SCALE_SMOOTH);
			loginIcon = new ImageIcon(loginPage);
			
			lblNewLabel = new JLabel("About This Tool");
			lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 22));
			lblNewLabel.setBounds(908, 93, 252, 68);
			loginFrame.getContentPane().add(lblNewLabel);
			
			lblHelpToManage = new JLabel("Help To Manage Hundred of Images");
			lblHelpToManage.setFont(new Font("Times New Roman", Font.BOLD, 17));
			lblHelpToManage.setBounds(963, 194, 301, 73);
			loginFrame.getContentPane().add(lblHelpToManage);
			
			lblHelpToCorrect = new JLabel("Help To Correct The Skew Angle of Images");
			lblHelpToCorrect.setFont(new Font("Times New Roman", Font.BOLD, 17));
			lblHelpToCorrect.setBounds(911, 302, 342, 41);
			loginFrame.getContentPane().add(lblHelpToCorrect);
			
			lblLessManualProcess = new JLabel("Less Manual Process : Just Click Upload , Process , Show Result and Confirm");
			lblLessManualProcess.setFont(new Font("Times New Roman", Font.BOLD, 17));
			lblLessManualProcess.setBounds(682, 387, 582, 55);
			loginFrame.getContentPane().add(lblLessManualProcess);
			label_2 = new JLabel("");
			label_2.setIcon(loginIcon);
			label_2.setBounds(654, 0, 672, 663);
			loginFrame.getContentPane().add(label_2);
			
			ImageIcon manIcon = new ImageIcon("C:\\Users\\user\\Downloads\\man77.png");
			Image man = manIcon.getImage();
			Image mai = man.getScaledInstance(238, 182, java.awt.Image.SCALE_SMOOTH);
			manIcon = new ImageIcon(mai);
			
			
			label_1 = new JLabel("");
			label_1.setIcon(manIcon);
			label_1.setBounds(417, 481, 238, 182);
			loginFrame.getContentPane().add(label_1);
		
		
		int x=165;
		for(String n:btnNames)
		{
			btn = new JButton(n);
			btn.setBounds(x,350,80,20);
			btn.addActionListener(this);
			loginFrame.getContentPane().add(btn);
			x+=90;
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Submit"))
		{
			if (txtField.getText().isEmpty()||passField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Please insert name and password to login");
			}
			else
			{
				if(new LoginController().doLogin(txtField.getText(),passField.getText()))
				{
					int ID=new LoginController().getID(txtField.getText());
					detailUser user = new detailUser();
					user.setName(txtField.getText());
					loginFrame.dispose();
					new FileController().createFile();
					new Main(ID);
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "Username or Password not valid..Please try again");
					
				}
			}
		}
		else if(e.getActionCommand().equals("Back"))
		{
			loginFrame.setVisible(false);
			new Home().frame.setVisible(true);
		}
		else if(e.getActionCommand().equals("Reset"))
		{
			txtField.setText("");
			passField.setText("");
		}
	}
	public static void main(String [] args)
	{
		new LoginPage();
	}
}