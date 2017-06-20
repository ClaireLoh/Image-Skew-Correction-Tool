package controller;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JOptionPane;

public class FileController {
	private static String sDirectoryA;
	private static String sDirectoryC;
	private String sDrive;
	public void createFile() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sDrive = null;
					String sDriveC = "C:\\";
					String sDriveD = "D:\\";
					File fDriveC = new File(sDriveC);
					File fDriveD = new File(sDriveD);

					if (fDriveC.exists()) {
						sDrive = sDriveC;
					} else if (fDriveD.exists()) {
						sDrive = sDriveD;
					} else {
						sDrive = "<unknown>:\\";
						JOptionPane.showMessageDialog(null,
								"Drive " + sDriveC + " or " + sDriveD + " does not exist in this machine.", "Warning",
								JOptionPane.ERROR_MESSAGE);
					}

					sDirectoryA = sDrive + "ImagePro\\oriImage_Upload";
					String sFailMsgA = "";
					boolean bfailA = false;
					File fDirectoryA = new File(sDirectoryA);
					if (!fDirectoryA.exists()) {
						if (fDirectoryA.mkdirs()) {
							System.out.println("Path directory of \"" + sDirectoryA + "\" was created!");
						} else {
							bfailA = true;
							sFailMsgA = "Failed to create path directory of \"" + sDirectoryA
									+ "\" ! Check if this machine has drive either " + sDriveC + " or " + sDriveD
									+ "  \n";
							System.out.print(sFailMsgA);
						}
					} else {
						System.out.println("Path directory of \"" + sDirectoryA + "\" was already exist!");
					}

					String sDirectoryB = sDrive + "ImagePro\\processed";
					String sFailMsgB = "";
					boolean bfailB = false;
					File fDirectoryB = new File(sDirectoryB);
					if (!fDirectoryB.exists()) {
						if (fDirectoryB.mkdirs()) {
							System.out.println("Path directory of \"" + sDirectoryB + "\" was created!");
						} else {
							bfailB = true;
							sFailMsgB = "Failed to create path directory of \"" + sDirectoryB
									+ "\" ! Check if this machine has drive either " + sDriveC + " or " + sDriveD
									+ "  \n";
							System.out.print(sFailMsgB);
						}
					} else {
						System.out.println("Path directory of \"" + sDirectoryB + "\" was already exist!");
					}

					sDirectoryC = sDrive + "ImagePro\\Result";
					String sFailMsgC = "";
					boolean bfailC = false;
					File fDirectoryC = new File(sDirectoryC);
					if (!fDirectoryC.exists()) {
						if (fDirectoryC.mkdirs()) {
							System.out.println("Path directory of \"" + sDirectoryC + "\" was created!");
						} else {
							bfailC = true;
							sFailMsgC = "Failed to create path directory of \"" + sDirectoryC
									+ "\" ! Check if this machine has drive either " + sDriveC + " or " + sDriveD
									+ "  \n";
							System.out.print(sFailMsgC);
						}
					} else {
						System.out.println("Path directory of \"" + sDirectoryC + "\" was already exist!");
					}

					String sDirectoryD = sDrive + "ImagePro\\CompleteImage";
					String sFailMsgD = "";
					boolean bfailD = false;
					File fDirectoryD = new File(sDirectoryD);
					if (!fDirectoryD.exists()) {
						if (fDirectoryD.mkdirs()) {
							System.out.println("Path directory of \"" + sDirectoryD + "\" was created!");
						} else {
							bfailD = true;
							sFailMsgD = "Failed to create path directory of \"" + sDirectoryD
									+ "\" ! Check if this machine has drive either " + sDriveC + " or " + sDriveD
									+ "  \n";
							System.out.print(sFailMsgD);
						}
					} else {
						System.out.println("Path directory of \"" + sDirectoryD + "\" was already exist!");
					}

					if (bfailA == true || bfailB == true || bfailC == true || bfailD == true) {
						JOptionPane.showMessageDialog(null, sFailMsgA + sFailMsgB + sFailMsgC + sFailMsgD, "Warning",
								JOptionPane.ERROR_MESSAGE);
					}

					//Main window = new Main(sDrive);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getDrive()
	{
		return sDrive;
	}

}
