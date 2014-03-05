package com.company;

import javafx.geometry.Orientation;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Main implements Printable{

    public static void main(String[] args) throws PrinterException {
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		PageFormat pf = printerJob.defaultPage();
		System.out.println(pf.getPaper().getHeight() + " x " + pf.getPaper().getHeight());
		pf.setOrientation(PageFormat.LANDSCAPE);
		printerJob.setPrintable(new Main(), pf);


		if(printerJob.printDialog()==true){                // если нажат ок на диалоге выбора принтера

			printerJob.print();
		}
    }

	@Override
	public int print(Graphics g, PageFormat pf, int pageNumber)
			throws PrinterException {

		if(pageNumber>0){
			return(Printable.NO_SUCH_PAGE);
		}else{
			g.drawString("Test Printing",10,10);
			return(Printable.PAGE_EXISTS);
		}
	}
}
