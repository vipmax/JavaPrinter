package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;

public class Main implements Printable{
	private static Image img;


    public static void main(String[] args) throws PrinterException, IOException {
	    paint();
		img = new ImageIcon("image.jpg").getImage();
		PrinterJob pPrinterJob = PrinterJob.getPrinterJob();
		PageFormat pPageFormat = pPrinterJob.defaultPage();
		Paper pPaper = pPageFormat.getPaper();
		pPageFormat.setOrientation(PageFormat.LANDSCAPE);
		pPaper.setSize(594.992125984252, 841.8897637795276);
		pPaper.setImageableArea(0.1, 0, 522.99212598425197, 769.8897637795276);
		pPaper.setImageableArea(0, 0, pPaper.getWidth() - 2.0, pPaper.getHeight() - 2.0);

		pPageFormat.setPaper(pPaper);
		pPageFormat = pPrinterJob.pageDialog(pPageFormat);  //без диалога печатает с большим отступом
		Book pBook = new Book();
		pBook.append(new Main(), pPageFormat);
		pPrinterJob.setPageable(pBook);


		if (pPrinterJob.printDialog()) {

			pPrinterJob.print();
		}
    }
	public static void paint() throws IOException {

		BufferedImage img = ImageIO.read(new File("C:\\forma_6-page-001.jpg"));
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setColor(Color.black);
		Font myFont = new Font("Times New Roman",Font.ROMAN_BASELINE, 35);
		g.setFont(myFont);
		g.drawString("МУП МП ",500,420);

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
			g2d.scale(scaleFactor*1.1, scaleFactor*1.1);
			System.out.println(pf.getPaper().getImageableX() + " x " + pf.getPaper().getImageableY());
			System.out.println(pf.getPaper().getImageableWidth() + " x " + pf.getPaper().getImageableHeight());
			g2d.drawImage(img, 0, 0, null);
			return Printable.PAGE_EXISTS;
		}
		return Printable.NO_SUCH_PAGE;
	}


}
