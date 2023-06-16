package com.example.demo.ai.ocr.demoitembill;

import static java.lang.Math.random;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


public class TaxInvoiceFormatPDF2 {

	public static Document document;
	private static LineSeparator lineSeparator;
	private static boolean colorSwitch = true;

	private static int textSize = 10;

	private static String fontFamilyBold = FontFactory.HELVETICA_BOLD;
	private static String fontFamily = FontFactory.HELVETICA;

	private static double netAmount;
	private static double gstAmount;
	private static double discountAmount;
	private static double totalAmount;
	static SharedPreferences sharedPreferences;

	public static void generatePDF(Activity activity, List<ReportItem> reportItemList,String strFileName) {

		// Create a new document
		document = new Document(PageSize.A4, 20, 20, 20, 20);
		lineSeparator = new LineSeparator();
		lineSeparator.setLineColor(WebColors.getRGBColor("#A00000"));

		try {
			// Create a new file in external storage
			int b = (int) (random() * (99 - 1 + 1) + 1);
			String fileName = strFileName +" - "+ b + ".pdf";
			File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			File file = new File(path, fileName);

			// Initialize PDF writer
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));

			// Open the document for writing
			document.open();

			// Add content to the document
			Paragraph paragraph = new Paragraph("TAX INVOICE REPORT", FontFactory.getFont(fontFamilyBold, 18, Font.BOLD));
			paragraph.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(paragraph);

			sharedPreferences = activity.getSharedPreferences("pref", Context.MODE_PRIVATE);
			String address = sharedPreferences.getString("Address", "") + " " + sharedPreferences.getString("City", "")
					+ " " + sharedPreferences.getString("State", "");

			// Add content to the document
			Paragraph pCompanyName = new Paragraph(sharedPreferences.getString("BusinessName", ""), FontFactory.getFont(fontFamilyBold, 20, Font.BOLD));
			Paragraph pCompanyAddress = new Paragraph("ADDRESS :- " + address, FontFactory.getFont(fontFamilyBold, textSize));
			Paragraph pCompanyEmail = new Paragraph("EMAIL ID :- " + sharedPreferences.getString("MerchantEmailID", ""), FontFactory.getFont(fontFamilyBold, textSize));
			Paragraph pCompanyMobile = new Paragraph("MOBILE NO :- " + sharedPreferences.getString("mobileNumber", ""), FontFactory.getFont(fontFamilyBold, textSize));
			Paragraph pCompanyGST = new Paragraph("GST NO :- " + sharedPreferences.getString("GSTNumber", ""), FontFactory.getFont(fontFamilyBold, textSize));

			document.add(pCompanyName);
			document.add(pCompanyAddress);
			document.add(pCompanyEmail);
			document.add(pCompanyMobile);
			document.add(pCompanyGST);

			document.add(blankLine()); //Blank Line

			document.add(itemTableHeading());//itemTableHeading
			document.add(itemTableItemList(reportItemList));//itemTableItemList
			document.add(summeryTable1());
			document.add(blankLine()); //Blank Line
			document.add(blankLine()); //Blank Line
			document.add(summeryTable());//itemTableItemList

			// Close the document
			document.close();

			Toast.makeText(activity, "PDF generated successfully", Toast.LENGTH_SHORT).show();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
			Toast.makeText(activity, "Error generating PDF", Toast.LENGTH_SHORT).show();
		}

	}

	private static PdfPTable summeryTable1() throws DocumentException {
		PdfPTable table1 = new PdfPTable(7); // Number of columns
		table1.setWidths(new float[]{2, 3, 3, 3, 3, 3, 3});
		table1.setWidthPercentage(100f);
		table1.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
		table1.getDefaultCell().setBorder(0); //remove the table border
		table1.addCell(itemTableItemListCellStyle("", true));
		table1.addCell(itemTableItemListCellStyle("", true));
		table1.addCell(itemTableItemListCellStyle("Total ", true));
		table1.addCell(itemTableItemListCellStyle("= " + Utils.getDecimalFormatDoubleIndianRupees(netAmount), false));
		table1.addCell(itemTableItemListCellStyle("= " + Utils.getDecimalFormatDoubleIndianRupees(discountAmount), false));
		table1.addCell(itemTableItemListCellStyle("= " + Utils.getDecimalFormatDoubleIndianRupees(gstAmount), false));
		table1.addCell(itemTableItemListCellStyle("= " + Utils.getDecimalFormatDoubleIndianRupees(totalAmount), false));

		return table1;
	}

	private static PdfPTable summeryTable() {
		PdfPTable table1 = new PdfPTable(2); // Number of columns
		table1.setWidthPercentage(50f);
		table1.setHorizontalAlignment(PdfPTable.ALIGN_RIGHT);
		table1.getDefaultCell().setBorder(0); //remove the table border

		table1.addCell(itemTableHeadingCellStyle("Net Amount", true));
		table1.addCell(itemTableHeadingCellStyle(Utils.getDecimalFormatDoubleIndianRupees(netAmount ), false));

		table1.addCell(itemTableHeadingCellStyle("Discount Amount", true));
		table1.addCell(itemTableHeadingCellStyle("( - ) " + Utils.getDecimalFormatDoubleIndianRupees(discountAmount), false));

		table1.addCell(itemTableHeadingCellStyle("Gst Amount", true));
		table1.addCell(itemTableHeadingCellStyle("( + ) " +Utils.getDecimalFormatDoubleIndianRupees(gstAmount), false));

		table1.addCell(itemTableHeadingCellStyle("Total Amount", true));
		table1.addCell(itemTableHeadingCellStyle(Utils.getDecimalFormatDoubleIndianRupees(totalAmount ), false));

		return table1;
	}


	private static Paragraph blankLine() {
		return new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8));
	}

	private static Paragraph paragraph(String value) {
		Paragraph paragraph = new Paragraph(value, FontFactory.getFont(fontFamilyBold, 16));
		paragraph.setAlignment(Paragraph.ALIGN_RIGHT);
		return paragraph;
	}


	// Item Table Start

	private static PdfPTable itemTableHeading() throws DocumentException {

		PdfPTable table = new PdfPTable(7); // Number of columns
		table.setWidthPercentage(100f);
		table.setWidths(new float[]{2, 3, 3, 3, 3, 3, 3});
		table.addCell(itemTableHeadingCellStyle("SR NO.", true));
		table.addCell(itemTableHeadingCellStyle("DATE", true));
		table.addCell(itemTableHeadingCellStyle("INVOICE NO", true));
		table.addCell(itemTableHeadingCellStyle("NET AMOUNT", false));
		table.addCell(itemTableHeadingCellStyle("DISCOUNT", false));
		table.addCell(itemTableHeadingCellStyle("GST", false));
		table.addCell(itemTableHeadingCellStyle("TOTAL", false));
		return table;
	}

	private static PdfPCell itemTableHeadingCellStyle(String text, boolean left) {
		BaseColor backgroundColor = WebColors.getRGBColor("#245689");
		BaseColor textColor = WebColors.getRGBColor("#FFFFFF");
		Paragraph paragraph = new Paragraph(text, FontFactory.getFont(fontFamily, textSize, Font.BOLD, textColor));
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBackgroundColor(backgroundColor);
		cell.setPaddingBottom(8);
		cell.setPaddingTop(8);
		cell.setPaddingLeft(8);
		cell.setPaddingRight(8);
		cell.setHorizontalAlignment(left ? Element.ALIGN_LEFT : Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return cell;
	}


	private static PdfPTable itemTableItemList(List<ReportItem> reportItemList) throws DocumentException {

		netAmount = 0;
		gstAmount = 0;
		discountAmount = 0;
		totalAmount = 0;
		int sr = 1;

		PdfPTable table = new PdfPTable(7); // Number of columns
		table.setWidthPercentage(100f);
		table.setWidths(new float[]{2, 3, 3, 3, 3, 3, 3});
		for (ReportItem item : reportItemList) {
			table.addCell(itemTableItemListCellStyle(sr + "", true));
			table.addCell(itemTableItemListCellStyle(Utils.getDateConverter(item.getInvoiceDate(),"MM/dd/yyyy","dd, MMM yyyy"), true));
			table.addCell(itemTableItemListCellStyle(""+item.getInvoiceId(), true));
			table.addCell(itemTableItemListCellStyle(Utils.getDecimalFormatDoubleIndianRupees(item.getInvoiceNetAmount()), false));
			table.addCell(itemTableItemListCellStyle(Utils.getDecimalFormatDoubleIndianRupees(item.getInvoiceDiscount()), false));
			table.addCell(itemTableItemListCellStyle(Utils.getDecimalFormatDoubleIndianRupees(item.getInvoiceGST()), false));
			table.addCell(itemTableItemListCellStyle(Utils.getDecimalFormatDoubleIndianRupees(item.getInvoiceTotal()), false));

			netAmount = netAmount + item.getInvoiceNetAmount();
			discountAmount = discountAmount + item.getInvoiceDiscount();
			gstAmount = gstAmount + item.getInvoiceGST();
			totalAmount = totalAmount + item.getInvoiceTotal();

			colorSwitch = !colorSwitch;
			sr++;
		}
		return table;
	}

	private static PdfPCell itemTableItemListCellStyle(String text, boolean left) {
		Font fontRupee = FontFactory.getFont("Arial", "₹", true, 12);
		Chunk chunkRupee = new Chunk(" ₹ 5410", fontRupee);
		BaseColor color1 = new BaseColor(254, 246, 247);
		BaseColor color2 = new BaseColor(237, 243, 250);
		Paragraph paragraph = new Paragraph(text, FontFactory.getFont(fontFamily, textSize, Font.BOLD));
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(Rectangle.BOX);
		cell.setPaddingBottom(4);
		cell.setPaddingTop(4);
		cell.setPaddingLeft(5);
		cell.setPaddingRight(5);
		if (colorSwitch) {
			cell.setBackgroundColor(color2);
		} else {
			cell.setBackgroundColor(color1);
		}
		cell.setHorizontalAlignment(left ? Element.ALIGN_LEFT : Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return cell;
	}

	// Item Table End

}
