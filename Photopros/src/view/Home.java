package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Color;
import java.awt.SystemColor;

public class Home {

	JFrame frame;
	JLabel lblBackground;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int widthScreen = gd.getDisplayMode().getWidth()-30;
		int heightScreen = gd.getDisplayMode().getHeight()-100;
		
		frame = new JFrame();
		frame.setBounds(0, 0, widthScreen+6, heightScreen+33);//imageProcess.setBounds(0, 0, widthScreen+6, heightScreen+33);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(686, 2, 650, 671);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblBackground = new JLabel();
		//lblBackground.setForeground(new ImageIcon(""));
		
		JLabel lblWelcomeToPhotopros = new JLabel("Welcome To SICT");
		lblWelcomeToPhotopros.setFont(new Font("Pristina", Font.BOLD, 28));
		lblWelcomeToPhotopros.setBounds(218, 31, 247, 42);
		panel.add(lblWelcomeToPhotopros);
		
		JButton button = new JButton("Login");
		button.setFont(new Font("Times New Roman", Font.BOLD, 22));
		button.setBackground(SystemColor.activeCaption);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new LoginPage();
				frame.dispose();
			}
		});
		button.setBounds(140, 359, 192, 52);
		panel.add(button);
		
		JButton button_1 = new JButton("Sign Up");
		button_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		button_1.setBackground(SystemColor.activeCaption);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new RegisterPage();
				frame.setVisible(false);
			}
		});
		button_1.setBounds(400, 359, 192, 52);
		panel.add(button_1);
		
		ImageIcon userIcon = new ImageIcon("C:\\Users\\user\\Downloads\\user.png");
		Image user = userIcon.getImage();
		Image newUser = user.getScaledInstance(204, 189, java.awt.Image.SCALE_SMOOTH);
		userIcon = new ImageIcon(newUser);
		JLabel label = new JLabel("");
		label.setIcon(userIcon);
		label.setBounds(256, 102, 204, 189);
		panel.add(label);
		ImageIcon bgIcon = new ImageIcon("C:\\Users\\user\\Downloads\\bg2.jpg");
		Image img = bgIcon.getImage();
		Image newimg = img.getScaledInstance(692, 671, java.awt.Image.SCALE_SMOOTH);
		bgIcon = new ImageIcon(newimg);
		
		JLabel lblNewLabel_1 = new JLabel("We are here to provide ");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Pristina", Font.BOLD, 26));
		lblNewLabel_1.setBounds(423, 171, 213, 69);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblSimplicityInCorrect = new JLabel("Simplicity in Correct Skew Image");
		lblSimplicityInCorrect.setForeground(Color.WHITE);
		lblSimplicityInCorrect.setFont(new Font("Pristina", Font.BOLD, 25));
		lblSimplicityInCorrect.setBounds(356, 310, 326, 72);
		frame.getContentPane().add(lblSimplicityInCorrect);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(bgIcon);
		lblNewLabel.setBounds(-6, 2, 692, 671);
		frame.getContentPane().add(lblNewLabel);
	}
}
