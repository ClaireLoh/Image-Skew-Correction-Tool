package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import controller.ImageDeskew;
import controller.ImageProcess;
import controller.LoginController;
import controller.dbSave;
import controller.selectdbController;
import model.detailImage;
import model.detailUser;

import javax.imageio.ImageIO;

/*import controller.MyTool;
import controller.ProcessImages;
import controller.StorePrintStream;*/

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;

public class Main {

	private static ArrayList<File> fImagesCollection = new ArrayList<File>();
	private ArrayList<detailImage> angleImage = new ArrayList<detailImage>();
	
	private GridLayout grid_PictureSelect = new GridLayout(Integer.valueOf(fImagesCollection.size()) * 3, 3, 50, 50);
	private JFrame imageProcess;
	private JScrollPane scrollPane_1, scrollPane_2;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton btnReset;
	private JButton btnProcess;
	private JButton btnResult;
	private JButton btnConfirm;
	private static String sDirectoryA;
	private static String sDirectoryC;
	JButton picButton;
	ImageDeskew sk = null;
	double angle = 0;
	int idNumber;
	
	

	/**
	 * Create the application.
	 */
	public Main(int id) {
		initialize(id);
		imageProcess.setVisible(true);
		this.idNumber = id;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
		try {UIManager.setLookAndFeel(new NimbusLookAndFeel());} catch (UnsupportedLookAndFeelException e) {e.printStackTrace();}
		
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int widthScreen = gd.getDisplayMode().getWidth()-30;
		int heightScreen = gd.getDisplayMode().getHeight()-100;
		
		imageProcess = new JFrame();
		imageProcess.getContentPane().setBackground(SystemColor.activeCaption);
		imageProcess.getContentPane().setForeground(Color.BLUE);
		imageProcess.setTitle("Skew Image Correction Tool");
		imageProcess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imageProcess.setResizable(true);
		imageProcess.setBounds(0, 0, widthScreen+6, heightScreen+33);
		imageProcess.setVisible(true);
		imageProcess.setLocationRelativeTo(null);
		imageProcess.getContentPane().setLayout(null);
		
		LoginController lc = new LoginController();
		selectdbController select = new selectdbController();
		int noImage = select.checkQuota(id);
		int noQuota = lc.getQuota(id);
		System.out.println(noImage);
		System.out.println(noQuota);
		//select image 
		JButton btnUpload = new JButton("Upload");
		btnUpload.setBackground(SystemColor.activeCaption);
		btnUpload.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				    fc.setFileFilter(new JPEGImageFileFilter());
				    fc.setMultiSelectionEnabled(true);
				    int res = fc.showOpenDialog(null);
				    // We have an image!
				    try {
				        if (res == JFileChooser.APPROVE_OPTION) {
				            File[] file = fc.getSelectedFiles();
				            List<File> fImages =  Arrays.asList(file);//read file as arraylist
				            
							for(File fImage : fImages)
							{
								fImagesCollection.add(fImage);
								BufferedImage bufferedImage = ImageIO.read(fImage);
								ImageIO.write(bufferedImage, "jpg",new File("C:\\ImagePro\\oriImage_Upload\\"+fImage.getName()));
							}
							
							viewImagePanel_PictureSelect();
							btnReset.setEnabled(true);
							btnProcess.setEnabled(true);
				     
				        } // Oops!
				        else {
				            JOptionPane.showMessageDialog(null,"You must select one image to be the reference.", "Aborting...",
				                    JOptionPane.WARNING_MESSAGE);
				        }
				    } catch (Exception iOException) {
				    }
			}
		});
		btnUpload.setBounds(71, 13, 145, 36);
		imageProcess.getContentPane().add(btnUpload);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 50, 620, 550);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
		scrollPane_1.setBorder(null);
		imageProcess.getContentPane().add(scrollPane_1);
		
		panel_1 = new JPanel();
		panel_1.setOpaque(true);
		panel_1.setBorder(null);
		panel_1.setBounds(5, 10, 650, 310);
		scrollPane_1.setViewportView(panel_1);
		
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(650, 50, 620, 550);
		scrollPane_2.setOpaque(false);
		scrollPane_2.getViewport().setOpaque(false);
		scrollPane_2.setBorder(null);
		imageProcess.getContentPane().add(scrollPane_2);
		
		panel_2 = new JPanel();
		panel_2.setOpaque(true);
		panel_2.setBorder(null);
		panel_2.setBounds(5, 10, 650, 310);
		scrollPane_2.setViewportView(panel_2);
		
		
		
		btnReset = new JButton("Reset");
		btnReset.setBackground(SystemColor.activeCaption);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fImagesCollection = new ArrayList<File>();
				viewImagePanel_PictureSelect();
				btnReset.setEnabled(false);
				btnProcess.setEnabled(false);
				try {
					Files.walk(Paths.get("C:/ImagePro/oriImage_Upload/"))
					.filter(Files::isRegularFile)
					.map(Path::toFile)
					.forEach(File::delete);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnReset.setBounds(255, 13, 145, 36);
		btnReset.setEnabled(false);
		imageProcess.getContentPane().add(btnReset);
		
		btnProcess = new JButton("Process");
		btnProcess.setBackground(SystemColor.activeCaption);
		btnProcess.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {

				
				if(fImagesCollection.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please select the image.");
				}else if(!fImagesCollection.isEmpty())
				{
					if(fImagesCollection.size()<=noQuota && noImage<=noQuota)
					{	Runnable run = new Runnable()
						{
							public void run()
							{
								
								
								for(File file:fImagesCollection)
								{
									File directoryfrom = new File("C:\\ImagePro\\oriImage_Upload\\"+file.getName());
									long size = file.length();
									if(size !=0)
									{
										BufferedImage buffImage;
										detailImage di = new detailImage();
										detailImage deImage = new detailImage();
										
										try{
											buffImage = ImageIO.read(file);
											new ImageProcess();
											BufferedImage nImage = ImageProcess.imageDeskew(buffImage);
											
											ImageIO.write(nImage,"jpg",new File("C:\\ImagePro\\Result\\"+file.getName()));
											ImageIO.write(buffImage, "jpg", new File("C:\\ImagePro\\processed\\"+file.getName()));
											directoryfrom.delete();
											
											di.setOriImage_path("C:\\ImagePro\\processed\\"+file.getName());
											di.setOriImage_name(file.getName());
											deImage.setProImage_name(file.getName());
											deImage.setProImage_path("C:\\ImagePro\\CompleteImage\\"+file.getName());
											di.setUser_id(idNumber);
											sk = new ImageDeskew(buffImage);
											angle = sk.getSkewAngle();
											deImage.setProImage_angle(angle);
											
											angleImage.add(deImage);
											new dbSave().saveOriPath(di);
											new dbSave().saveProPath(deImage);
										 	
											//btnResult.setEnabled(true);
										}catch(Exception ex)
										{
											System.out.print("Image operation failed");
											ex.printStackTrace();
										}
									}
								}
							}
						};
						
						 Thread runOperation = new Thread(run);
		    			 runOperation.start();
		    			 try {
							runOperation.join();
							btnResult.setEnabled(true);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    			 
					}
					else
					{
						JDialog.setDefaultLookAndFeelDecorated(true);
						int response =JOptionPane.showConfirmDialog(null, "Do you want to purchase more quota",null, JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
						
						if(response == JOptionPane.YES_OPTION)
						{	fImagesCollection.clear();
							imageProcess.dispose();
							new quotaPurchase(id);
						}
						else if(response == JOptionPane.CLOSED_OPTION)
						{
							System.exit(0);
						}
					}
				}
			 	}
		 });
		 
		btnProcess.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnProcess.setBounds(445, 13, 145, 36);
		btnProcess.setEnabled(false);
		imageProcess.getContentPane().add(btnProcess);
		
		 btnResult = new JButton("Show Result");
		 btnResult.setBackground(SystemColor.activeCaption);
		 btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				resultPanel();
				btnReset.setEnabled(false);
				btnProcess.setEnabled(false);
				btnUpload.setEnabled(false);
				btnConfirm.setEnabled(true);
				panel_1.setEnabled(false);
			}
				
		});
		btnResult.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnResult.setBounds(618, 13, 145, 36);
		imageProcess.getContentPane().add(btnResult);
		btnResult.setEnabled(false);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setEnabled(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File resultPath = new File("C:\\ImagePro\\Result\\");
				File[] RList = resultPath.listFiles();
		
				for(File file1:RList)
				{
					File getFile = new File("C:\\ImagePro\\Result\\"+file1.getName());
					if(file1.isFile())
					{
						BufferedImage img2;
						try
						{
							img2 = ImageIO.read(file1);
							ImageIO.write(img2, "jpg", new File("C:\\ImagePro\\CompleteImage\\"+file1.getName()));
							getFile.delete();
							
						}catch(IOException ex)
						{
							ex.printStackTrace();
							JOptionPane.showConfirmDialog(btnConfirm, "Error in copy file");
						}
					}
				}
				
				JDialog.setDefaultLookAndFeelDecorated(true);
				int response =JOptionPane.showConfirmDialog(null, "Do you want to upload new Images",null, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(response == JOptionPane.NO_OPTION)
				{
					new Home();
					imageProcess.dispose();
				}
				else if(response == JOptionPane.YES_OPTION)
				{	fImagesCollection.clear();
					imageProcess.dispose();
					new Main(id);
				}
				else if(response == JOptionPane.CLOSED_OPTION)
				{
					System.exit(0);
				}
				
			}
		});
		btnConfirm.setForeground(SystemColor.desktop);
		btnConfirm.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnConfirm.setBackground(SystemColor.activeCaption);
		btnConfirm.setBounds(1155, 611, 123, 33);
		imageProcess.getContentPane().add(btnConfirm);
	}

	/*
	 * Display Image on Jpanel Scroll [Picture Select]
	 */
	public void viewImagePanel_PictureSelect() {
		Runnable runView = new Runnable() {
			public void run() {
				scrollPane_1.setOpaque(false);// *
				scrollPane_1.getViewport().setOpaque(false);// *
				scrollPane_1.setBorder(null);// *

				panel_1 = new JPanel();
				panel_1.setOpaque(false);// *
				panel_1.setBorder(null);// *
				panel_1.setLayout(grid_PictureSelect);
				scrollPane_1.setViewportView(panel_1);

				for (File file : fImagesCollection) {
					ImageIcon picTempOri = new ImageIcon(file.getAbsolutePath().toString());

					Image img = picTempOri.getImage();
					Image newimg = img.getScaledInstance(
							((150 * picTempOri.getIconWidth()) / picTempOri.getIconHeight()), 150,
							java.awt.Image.SCALE_SMOOTH);
					ImageIcon newIcon = new ImageIcon(newimg);

					JButton picButton = new JButton();
					picButton.setIcon(newIcon);
					picButton.setText(file.getName().toString());
					picButton.setFont(new Font("Times New Roman", Font.BOLD, 19));// *
					picButton.setForeground(Color.BLACK);// *
					picButton.setHorizontalTextPosition(JLabel.CENTER);
					picButton.setVerticalTextPosition(JLabel.BOTTOM);
					picButton.setOpaque(false);
					picButton.setContentAreaFilled(false);
					picButton.setBorderPainted(false);
					picButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {

							String testSplitarg0 = arg0.getSource().toString();
							String[] outputSplitarg0 = testSplitarg0.split("\\,");

							String testSplitarg0Text = outputSplitarg0[25];
							String[] outputSplitText = testSplitarg0Text.split("\\=");
							String selectedImageShortName = outputSplitText[1];

							String fullImageDirectory = "";
							for (int i = 0; i < fImagesCollection.size(); i++) {
								String fileDirectory = fImagesCollection.get(i).getAbsolutePath().toString();
								if (fileDirectory.contains(selectedImageShortName)) {
									fullImageDirectory = fileDirectory;
								}
							}

							// pop-up image detail box
							boolean deleteImg = popupDetailImageBox_PictureSelect(fullImageDirectory,
									selectedImageShortName);
							if (deleteImg == true) {
								orderDeleteImg_PictureSelect(selectedImageShortName);
							}

						}
					});
					panel_1.add(picButton);
					panel_1.repaint();
					panel_1.revalidate();
				}
			}
		};
		Thread runview = new Thread(runView);
		runview.start();

	}

	/*
	 * pop-up image detail box [Picture Select]
	 */
	public boolean popupDetailImageBox_PictureSelect(String fullImageDirectory, String selectedImageShortName) {
		boolean deleteImage = false;

		String directoryImg = fullImageDirectory;

		ImageIcon picTempOri = new ImageIcon(directoryImg);
		Image img = picTempOri.getImage();
		Image newimg = img.getScaledInstance(((400 * picTempOri.getIconWidth()) / picTempOri.getIconHeight()), 500,
				java.awt.Image.SCALE_SMOOTH);// Image newimg =
												// img.getScaledInstance(400,
												// 400,
												// java.awt.Image.SCALE_SMOOTH);
												// //(Length,Height)
		ImageIcon newIcon = new ImageIcon(newimg);
		JLabel lbl = new JLabel(newIcon);

		JLabel lblTitle = new JLabel("<html>" + selectedImageShortName + "</html>");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 19));
		JLabel lblDirectory = new JLabel("<html>" + directoryImg + "</html>");
		lblDirectory.setFont(new Font("Times New Roman", Font.BOLD, 19));

		final JPanel dataPanel = new JPanel();
		dataPanel.setOpaque(false);

		dataPanel.add(lbl);
		dataPanel.add(Box.createVerticalStrut(15));
		JLabel lblPicture = new JLabel("<html>Picture :</html>");
		lblPicture.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		dataPanel.add(lblPicture);
		dataPanel.add(lblTitle);

		dataPanel.add(Box.createVerticalStrut(15));
		JLabel lblPath = new JLabel("<html>Path :</html>");
		lblPath.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		dataPanel.add(lblPath);
		dataPanel.add(lblDirectory);
		dataPanel.add(Box.createVerticalStrut(15));
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));

		Object[] options = { "Back", "Remove Picture" };
		int n = JOptionPane.showOptionDialog(null, dataPanel, "Picture", JOptionPane.YES_NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (n == 0) {

			deleteImage = false;
		} else if (n == 1) {

			deleteImage = true;
		}

		return deleteImage;
	}

	/*
	 * delete specific selected image [Picture Select]
	 */
	public void orderDeleteImg_PictureSelect(String deleteImgDirectory) {
		String selectedImageDeleteBtn = deleteImgDirectory;

		for (int i = 0; i < fImagesCollection.size(); i++) {
			String fileDirectory = fImagesCollection.get(i).getAbsolutePath().toString();

			if (fileDirectory.contains(selectedImageDeleteBtn)) {

				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to remove picture " + selectedImageDeleteBtn + " from this clipboard?",
						"Remove Confirmation", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					fImagesCollection.remove(i);

					boolean filedelete = new File("C:\\ImagePro\\oriImage_Upload\\" + selectedImageDeleteBtn).delete();

					viewImagePanel_PictureSelect();
				}
			}
		}
	}

	public void resultPanel() {
		Runnable run = new Runnable() {
			public void run() {

				File directory1 = new File("C:\\ImagePro\\Result\\");

				panel_2.setLayout(grid_PictureSelect);
				int c = 0;
				for (File file : directory1.listFiles()) {
					ImageIcon picTempori = new ImageIcon(file.getAbsolutePath().toString());

					Image img = picTempori.getImage();
					Image newimg = img.getScaledInstance(
							((150 * picTempori.getIconWidth()) / picTempori.getIconHeight()), 150,
							java.awt.Image.SCALE_SMOOTH);
					ImageIcon newIcon = new ImageIcon(newimg);
					picButton = new JButton();
					picButton.setIcon(newIcon);
					picButton.setText(file.getName().toString());
					picButton.setFont(new Font("Times New Roman", Font.BOLD, 19));// *
					picButton.setForeground(Color.BLACK);// *
					picButton.setHorizontalTextPosition(JLabel.CENTER);
					picButton.setVerticalTextPosition(JLabel.BOTTOM);
					picButton.setOpaque(false);
					picButton.setContentAreaFilled(false);
					picButton.setBorderPainted(false);
//					for (int i = 0; i < angleImage.size(); i++) {
//						System.out.println(angleImage.get(i).getProImage_angle());
						picButton.setToolTipText("the angle skew is " + angleImage.get(c).getProImage_angle() + file.getName().toString());
//					}

					//System.out.println(angleImage.size());
					panel_2.add(picButton);
					panel_2.repaint();
					panel_2.revalidate();
					c++;
				}

			}
		};
		Thread thr1 = new Thread(run);
		thr1.start();
	}
}
