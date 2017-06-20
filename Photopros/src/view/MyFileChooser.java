package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;

public class MyFileChooser
{

JFrame frame; 
JPanel panel;
JButton btnBrowse;
JButton change;
JLabel imglabel;

File targetFile;
BufferedImage targetImg;
private static final int baseSize = 128;
private static final String basePath ="/images/fimage";
JPanel panel_1;
ImageIcon icon;
public MyFileChooser() 
{
    // TODO Auto-generated constructor stub
    frame =new JFrame();
    frame.setLayout(new MigLayout());

    frame.setSize(300, 300);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     panel=new JPanel(new MigLayout());
     panel_1 = new JPanel();
     panel_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(5, 5, 5), 1, true));

     panel_1.setBackground(Color.pink);

     btnBrowse=new JButton("browse");
     btnBrowse.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            browseButtonActionPerformed(e);
        }
    });

    change=new JButton("Delete");
    change.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            // TODO Auto-generated method stub
            changeButtonActionPerformed(e);

        }

        private void changeButtonActionPerformed(ActionEvent e) 
        {
            // TODO Auto-generated method stub

               imglabel.revalidate(); //ADD THIS AS WELL
               imglabel.repaint();  //ADD THIS AS WELL
               imglabel.setIcon(null);
               System.out.println("delete button activated");

        }
    });


    imglabel=new JLabel("Image");
    imglabel.setSize(100, 100);
    imglabel.setBackground(Color.yellow);
    frame.add(panel_1,"span,pushx,pushy,growx,growy");
    frame.add(btnBrowse);
    frame.add(change,"");

    //frame.pack();



}

protected void browseButtonActionPerformed(ActionEvent e) 
{


    JFileChooser fc = new JFileChooser(basePath);
    fc.setFileFilter(new JPEGImageFileFilter());
    int res = fc.showOpenDialog(null);
    // We have an image!
    try {
        if (res == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            imglabel.setIcon(null);
           setTarget(file);
        } // Oops!
        else {
            JOptionPane.showMessageDialog(null,
                    "You must select one image to be the reference.", "Aborting...",
                    JOptionPane.WARNING_MESSAGE);
        }
    } catch (Exception iOException) {
    }

}

public BufferedImage rescale(BufferedImage originalImage)
{
    BufferedImage resizedImage = new BufferedImage(baseSize, baseSize, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(originalImage, 0, 0, baseSize, baseSize, null);
    g.dispose();
    return resizedImage;
}
public void setTarget(File reference)
{

    try {
        targetFile = reference;
        targetImg = rescale(ImageIO.read(reference));
    } catch (IOException ex) {
       // Logger.getLogger(MainAppFrame.class.getName()).log(Level.SEVERE, null, ex);
    }

    panel_1.setLayout(new BorderLayout(0, 0));

    icon=new ImageIcon(targetImg);
    imglabel=new JLabel(icon);
    panel_1.add(imglabel); 

    frame.setVisible(true);
}
public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub

            new MyFileChooser();

        }
    });
}
}