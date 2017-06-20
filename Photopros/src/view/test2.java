package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class test2 {

	private JFrame frame;
	private JPanel panel_1,panel;
	private static ArrayList<File> fImagesCollection = new ArrayList<File>();
	final String directoryName = "C:/ImagePro/Result";
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test2 window = new test2();
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
	public test2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		panel = new JPanel();
		panel_1 = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(panel);
		frame.getContentPane().add(scrollPane);
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(panel_1);
		frame.getContentPane().add(scrollPane_1);
		
		File directory = new File("C:/ImagePro/Result/sheep.jpg");
		BufferedImage buffImage;
		try{
			
			buffImage = ImageIO.read(directory);

			JLabel lbl = new JLabel(new ImageIcon(buffImage));
			panel.add(lbl);
		
		}catch(IOException ex)
		{
			
		}
		
		Runnable run = new Runnable()
				{
					public void run()
					{
						
						File directory1 = new File("C:\\ImagePro\\Result\\");
					
					
							for(File file : directory1.listFiles())
							{
								if(file.getName().toLowerCase().endsWith(".jpg"))
								{
									ImageIcon picTempori = new ImageIcon(file.getAbsolutePath().toString());
									
									Image img = picTempori.getImage();
									Image newimg = img.getScaledInstance(((150*picTempori.getIconWidth())/picTempori.getIconHeight()), 150,  java.awt.Image.SCALE_SMOOTH);
									ImageIcon newIcon = new ImageIcon(newimg);
									JButton picButton = new JButton();
									picButton.setIcon(newIcon);
									picButton.setText(file.getName().toString());
									picButton.setFont(new Font("Times New Roman", Font.BOLD, 19));//*
									picButton.setForeground(Color.BLACK);//*
									picButton.setHorizontalTextPosition(JLabel.CENTER);
									picButton.setVerticalTextPosition(JLabel.BOTTOM);
									picButton.setOpaque(false);
									picButton.setContentAreaFilled(false);
									picButton.setBorderPainted(false);
									picButton.addActionListener(new ActionListener(){
										public void actionPerformed(ActionEvent arg1)
										{
											String testSplitarg0 = arg1.getSource().toString();
											String[] outputSplitarg0 = testSplitarg0.split("\\,");
											
											String testSplitarg0Text = outputSplitarg0[25];
											String[] outputSplitText= testSplitarg0Text.split("\\=");
											String selectedImageShortName =outputSplitText[1]; 
							
											String fullImageDirectory = ""; 
											
											for (int i=0 ; i<file.length() ; i++)
											{
												String fileDirectory = file.getAbsolutePath().toString();
													if(fileDirectory.contains(selectedImageShortName))
									               {
														fullImageDirectory = fileDirectory;
									               }
											}
										}
									});
									
									panel_1.add(picButton);
									System.out.println("Success");
									
								}
								else 
								{
									System.out.println("Images no Found");
								}
							}
						
		
					}
				};
		Thread thr1 = new Thread(run);
		thr1.start();
		
		
	}

}
