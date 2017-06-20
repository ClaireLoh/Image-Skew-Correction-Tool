package controller;

import java.awt.image.BufferedImage;
import controller.ImageDeskew;
import controller.ImageTools;

public class ImageProcess
{
	public static BufferedImage imageDeskew(BufferedImage image)
	{
		final double MINIMUM_DESKEW_THRESHOLD= 0.0d;
		double tempoInicial = System.currentTimeMillis();
		BufferedImage imageOut;
		ImageDeskew sk = null;
		
		try
		{
			sk = new ImageDeskew(image);
		} catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//describe the angle
		double angle = 0;
		
		try
		{
			angle = sk.getSkewAngle();
			//System.out.println(angle);
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("Angular deskew: "+ angle);
		
		//Rotate a image
		if((angle> MINIMUM_DESKEW_THRESHOLD || angle< -(MINIMUM_DESKEW_THRESHOLD)))
		{
			imageOut = ImageTools.rotate(image, -angle, image.getWidth()/2 , image.getHeight()/2);
		}
		else
		{
			imageOut = image;
		}
		
		double tempoFinal = System.currentTimeMillis();
		System.out.println("#time deskew:"+((tempoFinal-tempoInicial)/1000)+ "seconds.");
		return imageOut;
		
		
		
	}
}
