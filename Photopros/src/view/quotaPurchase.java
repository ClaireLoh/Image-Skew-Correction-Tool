package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.JTextField;

import controller.LoginController;
import controller.selectdbController;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class quotaPurchase {

	private JFrame frame;
	private JTextField textField;
	private ImageIcon iconPurchase;
	private static int id;
	private int quota;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					quotaPurchase window = new quotaPurchase(id);
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
	public quotaPurchase(int id) {
		initialize(id);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int widthScreen = gd.getDisplayMode().getWidth()-30;
		int heightScreen = gd.getDisplayMode().getHeight()-100;
		
		frame = new JFrame();
		frame.setBounds(0, 0, widthScreen+6, heightScreen+33);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 1336, 673);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPurchase = new JLabel("Purchase Quota");
		lblPurchase.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPurchase.setBounds(552, 34, 162, 28);
		panel.add(lblPurchase);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(498, 131, 70, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quota to purchase");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(460, 202, 167, 20);
		panel.add(lblNewLabel_1);
		
		LoginController getusername = new LoginController();
		String name = getusername.getName(id);
		
		JLabel lblNewLabel_2 = new JLabel(name);
		lblNewLabel_2.setForeground(new Color(0, 255, 0));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBounds(626, 121, 230, 45);
		panel.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(626, 198, 216, 29);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnPurchase = new JButton(iconPurchase);
		try{
		iconPurchase = new ImageIcon("C:\\Users\\user\\workspaceNeon\\Photopros\\image\\purchase.png");
		Image img = iconPurchase.getImage() ;  
		Image newimg = img.getScaledInstance( 92, 39,  java.awt.Image.SCALE_SMOOTH ) ;  
		iconPurchase = new ImageIcon( newimg );
		btnPurchase.setIcon(iconPurchase);
		btnPurchase.setDisabledIcon(iconPurchase);
		btnPurchase.setPressedIcon(iconPurchase);
		btnPurchase.setSelectedIcon(iconPurchase);
		btnPurchase.setDisabledSelectedIcon(iconPurchase);
		
		btnPurchase.setRolloverEnabled(true);
		btnPurchase.setRolloverIcon(iconPurchase);
		btnPurchase.setRolloverSelectedIcon(iconPurchase);
		btnPurchase.setContentAreaFilled(false);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText()!="")
				{
					quota = Integer.parseInt(textField.getText());
					JDialog.setDefaultLookAndFeelDecorated(true);
					int confirm = JOptionPane.showConfirmDialog(btnPurchase, "The total is RM"+(quota*2)+"Confirm?",null,JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(confirm == JOptionPane.YES_OPTION)
					{
						selectdbController se = new selectdbController();
						se.updateQuota(id, quota);
					}
							
					JDialog.setDefaultLookAndFeelDecorated(true);
					int response =JOptionPane.showConfirmDialog(null, "Do you want to upload image now?",null, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(response == JOptionPane.NO_OPTION)
					{
						new Home();
						frame.dispose();
					}
					else if(response == JOptionPane.YES_OPTION)
					{	
						new Main(id);
						frame.dispose();
					}
					else if(response == JOptionPane.CLOSED_OPTION)
					{
						System.exit(0);
					}
				}
				else
				{
					JOptionPane.showConfirmDialog(btnPurchase, "Please enter the quota you want to purchase");
				}
			
				
			}
		});
		btnPurchase.setBounds(588, 293, 109, 54);
		panel.add(btnPurchase);
		panel.revalidate();
		panel.repaint();
	}
}
