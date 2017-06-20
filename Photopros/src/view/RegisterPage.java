package view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.FileController;
import controller.LoginController;
import controller.RegistrationController;
import controller.validationController;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPasswordField;
import model.detailUser;
import java.awt.Font;
import java.awt.SystemColor;


class RegisterPage extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame registerFrame;

	//CENTER
	private JPanel panelCenter;
	private JLabel lblName;
	private JLabel lblcfrPass;
	private JLabel lblEmail;
	private JLabel lblPhone;
	private JLabel lblPassword;
	private JTextField tfName;
	private JTextField tfPhone;
	private JTextField tfEmail;
	private JPasswordField pass2;
	private JPasswordField pass1;
	private JButton btnSave;
	private JButton btnReset;
	private JButton btnBack;
	private JLabel lblRegister;
	private JLabel label;
	private JLabel lblAllTheRegistrations;
	
	public RegisterPage()
	{
		init();
		
		registerFrame.setVisible(true);
		
	}
	
	public void init()
	{
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int widthScreen = gd.getDisplayMode().getWidth()-30;
		int heightScreen = gd.getDisplayMode().getHeight()-100;
		registerFrame = new JFrame();
		registerFrame.setBounds(0,0,widthScreen+6, heightScreen+33);
			registerFrame.getContentPane().setLayout(null);
		
		
		//CENTER
			panelCenter = new JPanel();
			panelCenter.setBackground(SystemColor.activeCaption);
			panelCenter.setBounds(0, 0, 663, 663);
			panelCenter.setLayout(null);
				lblName = new JLabel("Name :");
				lblName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				lblName.setBounds(200,188,70,15);
				tfName = new JTextField(200);
				tfName.setBounds(280,187,200,20);
				
				lblPassword = new JLabel("Password :");
				lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				lblPassword.setBounds(175,232,70,15);
				pass1 = new JPasswordField(200);
				pass1.setBounds(280,231,200,20);
				
				lblcfrPass = new JLabel("Confirm Password :");
				lblcfrPass.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				lblcfrPass.setBounds(125,271,120,19);
				pass2 = new JPasswordField(200);
				pass2.setBounds(280,272,200,20);
				
				
				lblEmail = new JLabel("Email :");
				lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				lblEmail.setBounds(203,316,80,15);
				tfEmail = new JTextField(20);
				tfEmail.setBounds(280,315,200,20);

				
				lblPhone = new JLabel("Telno :");
				lblPhone.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				lblPhone.setBounds(200,362,70,15);
				tfPhone = new JTextField(200);
				tfPhone.setBounds(280,361,200,20);
				

			panelCenter.add(lblName);
			panelCenter.add(tfName);
			
			panelCenter.add(lblPassword);
			panelCenter.add(lblcfrPass);
			panelCenter.add(pass1);
			panelCenter.add(pass2);
			
			panelCenter.add(lblEmail);
			panelCenter.add(tfEmail);
			
			
			panelCenter.add(lblPhone);
			panelCenter.add(tfPhone);
			
		
		registerFrame.getContentPane().add(panelCenter);
		btnSave = new JButton("Register");
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSave.setBackground(SystemColor.activeCaption);
		panelCenter.add(btnSave);
		btnSave.setBounds(290,433,104,29);
		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnReset.setBackground(SystemColor.activeCaption);
		panelCenter.add(btnReset);
		btnReset.setBounds(410,434,87,29);
		btnBack = new JButton("Back");
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnBack.setBackground(SystemColor.activeCaption);
		panelCenter.add(btnBack);
		btnBack.setBounds(171,433,97,30);
		
		lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblRegister.setBounds(280, 81, 158, 46);
		panelCenter.add(lblRegister);
		
		ImageIcon loginIcon = new ImageIcon("C:\\Users\\user\\workspaceNeon\\Photopros\\bg.jpg");
		Image login = loginIcon.getImage();
		Image loginPage = login.getScaledInstance(672, 663, java.awt.Image.SCALE_SMOOTH);
		loginIcon = new ImageIcon(loginPage);
		
		lblAllTheRegistrations = new JLabel("All The Registrations Are Free of Charge");
		lblAllTheRegistrations.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblAllTheRegistrations.setBounds(701, 102, 473, 82);
		registerFrame.getContentPane().add(lblAllTheRegistrations);
		
		JLabel lblNewUserWill = new JLabel("New User will be Given Free 10 images quota");
		lblNewUserWill.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewUserWill.setBounds(703, 217, 525, 77);
		registerFrame.getContentPane().add(lblNewUserWill);
		
		label = new JLabel("");
		label.setIcon(loginIcon);
		label.setBounds(663, 0, 663, 663);
		registerFrame.getContentPane().add(label);
		btnBack.addActionListener(this);
		btnReset.addActionListener(this);
		btnSave.addActionListener(this);
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Back"))
		{
			registerFrame.setVisible(false);
			new Home().frame.setVisible(true);
			
			
		}
		else if(e.getActionCommand().equals("Reset"))
		{
			clear();
		}
		else if(e.getActionCommand().equals("Register"))
		{
			if(tfName.getText().isEmpty()||pass1.getText().isEmpty()||pass2.getText().isEmpty()||tfEmail.getText().isEmpty()||tfPhone.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(btnSave, "Please complete the Field required");
			}
			else
			{
				String pass = pass1.getText();
				String passcfr = pass2.getText();
				String email = tfEmail.getText();
				
				Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
			    Matcher matcher = pattern.matcher(tfPhone.getText());

				
				if(new validationController().isValidEmailAddress(email) && matcher.matches())
				{	if(pass.length()>=6 && !pass.contains(" "))
					{
						if(pass.equals(passcfr))
						{
							detailUser user = new detailUser();
							user.setName(tfName.getText());
							user.setEmail(email);
							user.setPwd(pass2.getText());
							user.setPhoneNo(tfPhone.getText());
						
						new RegistrationController().register(user);
						//JOptionPane.showMessageDialog(btnSave, "Register Successfully!");
						int ID=new LoginController().getID(tfName.getText());
						new FileController().createFile();
						new Main(ID);
						clear();
						registerFrame.setVisible(false);
						}
						
						else
						{
							JOptionPane.showMessageDialog(btnSave, "Password Does Not Match");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(btnSave, "Invalid Password..Password must 6 character and above");
					}
					
				}
				else
				{
					JOptionPane.showMessageDialog(btnSave, "<html><br>Format of email or phone number invalid<br>Example email:abc@yahoo.com<br>"
							+ "Example Phone number: 011-2427002");
				}
			}
			
		}
	}
	void clear()
	{
		tfName.setText(""); 
		tfEmail.setText("");
		tfEmail.setText("");
		tfPhone.setText("");
		pass1.setText("");
		pass2.setText("");
	}
	public static void main(String [] args)
	{
		new RegisterPage();
	}
}