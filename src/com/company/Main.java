package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

public class Main implements Printable{
	private static Image img;


    public static void main(String[] args) throws PrinterException, IOException {
	paint();
		img = new ImageIcon("image.jpg").getImage();
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		PageFormat pf = printerJob.defaultPage();
		System.out.println(pf.getPaper().getHeight() + " x " + pf.getPaper().getHeight());
		pf.setOrientation(PageFormat.LANDSCAPE);
		printerJob.setPrintable(new Main(), pf);


		if(printerJob.printDialog()==true){                // если нажат ок на диалоге выбора принтера

			printerJob.print();
		}
    }
	public static void paint() throws IOException {
		int width = 1169*2 ,height = 827*2 ;
		BufferedImage img = new BufferedImage(width , height , BufferedImage.TYPE_INT_ARGB_PRE);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setBackground(Color.white);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		g.drawLine(width / 3, 100, width / 3, height - 100);
		g.drawLine(2 * width / 3, 100, 2 * width / 3, height - 100);
		Font myFont = new Font("Times New Roman",Font.ROMAN_BASELINE, 35);
		g.setFont(myFont);
		g.drawString("Место для штампа организации  ",10,50);
		g.drawString("Путевой лист автобуса ________ № ",width/3+10,50);
		g.drawString("Типовая межотраслевая форма №6",width-550,50);
		ImageIO.write(img, "gif", new File("image.jpg"));
	}
	@Override
	public int print(Graphics g, PageFormat pf, int pageIndex) {
		Graphics2D g2d = (Graphics2D) g;
		g.translate((int) (pf.getImageableX()), (int) (pf.getImageableY()));
		if (pageIndex == 0) {
			double pageWidth = pf.getImageableWidth();
			double pageHeight = pf.getImageableHeight();
			double imageWidth = img.getWidth(null);
			double imageHeight = img.getHeight(null);
			double scaleX = pageWidth / imageWidth;
			double scaleY = pageHeight / imageHeight;
			double scaleFactor = Math.min(scaleX, scaleY);
			g2d.scale(scaleFactor, scaleFactor);
			g.drawImage(img, 0, 0, null);
			return Printable.PAGE_EXISTS;
		}
		return Printable.NO_SUCH_PAGE;
	}


}
