package view;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.ImageProcess;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageSelector extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
				private JMenuItem   open;
        	       private JFileChooser fc;
        	       private JLabel jl;
        	       private File[] img;
        	       private String pathImg;
        	       private ImageIcon imgIcon;
        	       private JScrollPane sc;
        	       private JLabel label;
        	       private JTable jtable;
        	       private JScrollPane scrollPane;
        	       private File files;
        	       final String directoryName = "D:/FYP/IPS/OriginalImages";
        	               
        	       public ImageSelector()
        	       {
        	           setTitle("Image Viewer");
        	           getContentPane().setLayout(new GridLayout());
        	           setJMenuBarAndMenuBarItems();
        	           setJFileChooser();
        	           setJLabel();
        	           setAction();
        	           setSize(300,200);
        	           setVisible(true);
        	           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	       }
        	     
        	       void setJMenuBarAndMenuBarItems()
        	       {
        	           JMenuBar menuBar = new JMenuBar();
        	           JMenu menu1 = new JMenu("File");
        	           open = new JMenuItem("Open");
        	           menu1.add(open);
        	           menuBar.add(menu1);
        	           setJMenuBar(menuBar);
        	       }
        	       
        	       void setJLabel()
        	       {
        	       		jl = new JLabel();
        	       		jl.setBounds(10,10,700,500);
        	           getContentPane().add(jl);
        	           
        	           scrollPane = new JScrollPane();
        	           getContentPane().add(scrollPane);
        	           
        	           label = new JLabel();
        	           label.setBounds(10,10,50,50);
        	           getContentPane().add(label);
        	       }
        	       
        	       void setJFileChooser()
        	       {
        	           fc = new JFileChooser();
        	           fc.setMultiSelectionEnabled(true);
        	       }
        	     
        	       void setAction()
        	       {
        	           open.addActionListener(this);
        	       }
        	       
        	       void openDirectory ()
        	       {
        	    	   Runnable runCreateFiles = new Runnable()
        	    	   {
        	    		   	public void run()
        	    		   {
        	    		   		 files = new File("D:\\FYP\\IPS\\OriginalImages");
        	    		   		if (!files.exists()) 
        	    		   		{
        	    		   			if (files.mkdirs()) 
        	    		   			{
        	    		   				System.out.println("Multiple directories are created!");
        	    		   			} 
        	    		   			else 
        	    		   			{
        	    		   				System.out.println("Failed to create multiple directories!");
        	    		   			}
        	    		   		}
        	    		   	}

        	    		};
        	    		
        	    		Thread run2 = new Thread(runCreateFiles);
        	    		run2.start();
        	    	  }
        	     
        	       public void actionPerformed(ActionEvent eve)
        	       {
        	    	   openDirectory();
        	    	   Runnable run = new Runnable()
        	    	   {
        	    		   	public void run()
        	    		   	{
        	    		   		int getfile;
        	          	        if(eve.getSource() == open)
        	          	           {
        	          	               getfile = fc.showOpenDialog(rootPane);
        	          	               
        	          	               if(getfile==JFileChooser.APPROVE_OPTION)
        	          	               {
        	          	               		img = fc.getSelectedFiles();
        	          	               		
        	          	               		//ImageIO.doWrite(img, "jpg", new File("D:\\FYP\\IPS\\OriginalImages"));
        	          	               	 
        	          	               		BufferedImage bufferedImage;
        	          	               		for(File picture:img)
        	          	               		{
        	          	               			try {
        	          	   						bufferedImage = ImageIO.read(picture);
        	          	   						ImageIO.write(bufferedImage, "jpg",new File("D:\\FYP\\IPS\\OriginalImages\\" + picture.getName()));

        	          	   					} 
        	          	               			catch (IOException e)
        	          	               			{
        	          	   						// TODO Auto-generated catch block
        	          	   						e.printStackTrace();
        	          	               			}
        	          	               			
        	          	             
        	          	               			File directory = new File(directoryName);
        	          	               			File[] fList = directory.listFiles();
        	          	               			for(File file:fList)
        	          	               			{
        	          	               				if(file.isFile())
        	          	               				{
        	          	               					long size = file.length();
        	          	               					if (size !=0)
        	          	               					{
        	          	               						BufferedImage buffImage;
        	          	               						try
        	          	               						{
        	          	               							buffImage = ImageIO.read(file);
        	          	               							new ImageProcess();
        	          	               							BufferedImage nImage = ImageProcess.imageDeskew(buffImage);
    	              	          	   	            		
        	          	               							Image oriImage = buffImage.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        	          	               							Image newImage = nImage.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        	          	               							JLabel lbl = new JLabel(new ImageIcon(newImage));
        	          	               							JLabel lbl1 = new JLabel(new ImageIcon(oriImage));
    	              	          	   	            		
        	          	               							getContentPane().add(lbl1);
        	          	               							getContentPane().add(lbl);
	    	              	          	   	            	
        	          	               							file.delete();
    	              	          	   	            	
        	          	               						} 
        	          	               						catch (IOException e)
        	          	               						{
    													
        	          	               							e.printStackTrace();
        	          	               						}
        	          	               					}
        	          	               					else
        	          	               					{
        	          	               						System.out.println("There are no images select");
        	          	               					}
        	          	               			}
    						 	            		
    	              	          	               	repaint();
    	              	          	               	revalidate();
        	          	               			}
        	          	               	

        	          	               }
        	          	               		}
        	          	          	}	
        	    		   		}
        	    		   	
        	    			   };
        	    		   	
        	    			   
        	    			   Thread runNow = new Thread(run);
        	    			  
        	    			   runNow.start();
        	    		   	}
        	    	   }
        	       
        	       
        	    		   		   
/*        	    			   Runnable run3 = new Runnable()
        	    			 {
        	    				  public void run()
        	    				  {
        	    					  	File directory = new File(directoryName);
        	    					  	File[] fList = directory.listFiles();
        	    					  	for(File file:fList)
        	    					  	{
        	    					  		if(file.isFile())
        	    					  		{
        	    					  			BufferedImage buffImage;
												try {
													buffImage = ImageIO.read(file);
	        	    					  		  	new ImageProcess();
	              	          	   					BufferedImage nImage = ImageProcess.imageDeskew(buffImage);
	              	          	   	            		
	              	          	   	            	Image oriImage = buffImage.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
	              	          	   	            	Image newImage = nImage.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
	              	          	   	            	JLabel lbl = new JLabel(new ImageIcon(newImage));
	              	          	   	            	JLabel lbl1 = new JLabel(new ImageIcon(oriImage));
	              	          	   	            		
	              	          	   	            	getContentPane().add(lbl1);
	              	          	   	            	getContentPane().add(lbl);
	              	          	   	            	
												} catch (IOException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
						 	            		
	              	          	               	repaint();
	              	          	               	revalidate();
        	    					  			

        	    					  		}
        	    					  	}
          	          	               		
        	    					
      	          	  
        	    				  }
        	    			 };
        	    			 
        	    			 Thread runOperation = new Thread(run3);
        	    			 runOperation.start();
        	          */
        	    		   	
        	       
        	    	   
        	   
        	       