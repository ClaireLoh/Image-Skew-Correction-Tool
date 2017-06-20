package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageTools
{
	 
	public static BufferedImage rotate(BufferedImage image, double angle, int cx, int cy)
	{ 
	    int width = image.getWidth(null); 
	    int height = image.getHeight(null);
	   

	    int minX, minY, maxX, maxY; 
	    minX = minY = maxX = maxY = 0; 

	    int[] corners = { 0, 0, width, 0, width, height, 0, height }; 

	    double theta = Math.toRadians(angle); 

	    for (int i = 0; i < corners.length; i += 2)
	    { 
	        int x = (int) (Math.cos(theta) * (corners[i] - cx) - 
	        		Math.sin(theta) * (corners[i + 1] - cy) + cx); 
	        int y = (int) (Math.sin(theta) * (corners[i] - cx) + 
	        		Math.cos(theta) * (corners[i + 1] - cy) + cy); 

	        if (x > maxX)
	        { 
	            maxX = x; 
	        } 

	        else if (x < minX)
	        { 
	            minX = x; 
	        } 

	        else if (y > maxY) 
	        { 
	            maxY = y; 
	        } 

	        else if (y < minY) 
	        { 
	            minY = y; 
	        } 

	    } 

	    cx = (cx - minX); 
	    cy = (cy - minY); 

	    int type = image.getType(); 
	    if (type == 0) 
	    { 
	        type = BufferedImage.TYPE_INT_RGB; 
	        if (hasAlpha(image))
	        { 
	            type = BufferedImage.TYPE_INT_ARGB; 
	        } 
	    } 

	    BufferedImage bi = new BufferedImage((maxX - minX), (maxY - minY), type); 
	    Graphics2D g2 = bi.createGraphics(); 
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC); 

	    g2.setBackground(Color.white); 
	    g2.fillRect(0, 0, bi.getWidth(), bi.getHeight()); 

	    AffineTransform at = new AffineTransform(); 
	    at.rotate(theta, cx, cy); 

	    g2.setTransform(at); 
	    g2.drawImage(image, -minX, -minY, null); 
	    g2.dispose(); 

	    return bi; 
	}

	private static boolean hasAlpha(BufferedImage image)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean isBlack(BufferedImage cBmp, int x, int i)
	{
		// TODO Auto-generated method stub
		return true;
	} 
}

