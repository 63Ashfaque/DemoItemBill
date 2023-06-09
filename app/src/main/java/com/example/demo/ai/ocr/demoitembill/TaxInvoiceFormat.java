package com.example.demo.ai.ocr.demoitembill;

import static java.lang.Math.random;

import android.app.Activity;
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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TaxInvoiceFormat {

	public static Document document;
	private static LineSeparator lineSeparator;
	private static boolean colorSwitch = true;

	private static int textSize = 10;

	private static String fontFamilyBold = FontFactory.HELVETICA_BOLD;
	private static String fontFamily = FontFactory.HELVETICA;


	public static void generatePDF(Activity activity, List<ItemDataClass> itemDataList, SellerDataClass sellerDataClass, CustomerDataClass customerDataClass,
	                               InvoiceDataClass invoiceDataClass) {

		// Create a new document
		document = new Document(PageSize.A4, 20, 20, 20, 20);
		lineSeparator = new LineSeparator();
		lineSeparator.setLineColor(WebColors.getRGBColor("#A00000"));

		try {
			// Create a new file in external storage
			int b = (int) (random() * (99 - 1 + 1) + 1);
			//String fileName = "Bill" + b + ".pdf";
			String fileName = "Bill.pdf";
			File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			File file = new File(path, fileName);

			// Initialize PDF writer
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));

			// Open the document for writing
			document.open();

			// Add content to the document
			Paragraph paragraph = new Paragraph("TAX INVOICE", FontFactory.getFont(fontFamilyBold, 18, Font.BOLD));
			paragraph.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(paragraph);

			// Add content to the document
			Paragraph pCompanyName = new Paragraph(sellerDataClass.getSellerName(), FontFactory.getFont(fontFamilyBold, 20, Font.BOLD));
			Paragraph pCompanyAddress = new Paragraph("ADDRESS :- " + sellerDataClass.getSellerAddress(), FontFactory.getFont(fontFamilyBold, textSize));
			Paragraph pCompanyEmail = new Paragraph("EMAIL ID :- " + sellerDataClass.getSellerEmailId(), FontFactory.getFont(fontFamilyBold, textSize));
			Paragraph pCompanyMobile = new Paragraph("MOBILE NO :- " + sellerDataClass.getSellerMobileNo(), FontFactory.getFont(fontFamilyBold, textSize));
			Paragraph pCompanyGST = new Paragraph("GST NO :- " + sellerDataClass.getSellerGSTNo(), FontFactory.getFont(fontFamilyBold, textSize));

			document.add(pCompanyName);
			document.add(pCompanyAddress);
			document.add(pCompanyEmail);
			document.add(pCompanyMobile);
			document.add(pCompanyGST);


			document.add(blankLine()); //Blank Line

			document.add(customerDetailsTable(customerDataClass, invoiceDataClass));//Customer Details Table

			document.add(blankLine()); //Blank Line

			document.add(itemTableHeading());//itemTableHeading
			document.add(itemTableItemList(itemDataList));//itemTableItemList

			//End of the Invoice
			onEndPage(pdfWriter, document);
			// Close the document
			document.close();

			Toast.makeText(activity, "PDF generated successfully", Toast.LENGTH_SHORT).show();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
			Toast.makeText(activity, "Error generating PDF", Toast.LENGTH_SHORT).show();
		}

	}


	private static Paragraph blankLine() {
		return new Paragraph(" ", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8));
	}

	// Customer Table Start
	private static PdfPTable customerDetailsTable(CustomerDataClass customerDataClass, InvoiceDataClass invoiceDataClass) throws DocumentException {
		PdfPTable table = new PdfPTable(2); // Number of columns
		table.setWidthPercentage(100f);

		PdfPTable table1 = new PdfPTable(2); // Number of columns
		table1.getDefaultCell().setBorder(0); //remove the table border
		table1.setWidths(new float[]{2, 3});

		//row1
		table1.addCell(new Paragraph("BILL TO", FontFactory.getFont(fontFamilyBold, textSize, Font.BOLD)));
		table1.addCell(customerDetailsTableStyle(""));
		//row1
		table1.addCell(customerDetailsTableStyle("Customer Name"));
		table1.addCell(customerDetailsTableStyle(customerDataClass.getCustomerName()));
		//row2
		table1.addCell(customerDetailsTableStyle("Address"));
		table1.addCell(customerDetailsTableStyle(customerDataClass.getCustomerAddress()));
		//row3
		table1.addCell(customerDetailsTableStyle("Email Id "));
		table1.addCell(customerDetailsTableStyle(customerDataClass.getCustomerEmailId()));
		//row4
		table1.addCell(customerDetailsTableStyle("Mobile No "));
		table1.addCell(customerDetailsTableStyle(customerDataClass.getCustomerMobileNo()));

		table.addCell(table1);

		PdfPTable table2 = new PdfPTable(2); // Number of columns
		table2.getDefaultCell().setBorder(0); //remove the table border
		table2.setWidths(new float[]{2.5f, 3});
		//row1
		table2.addCell(customerDetailsTableStyle("\n"));
		table2.addCell(customerDetailsTableStyle(""));
		//row1
		table2.addCell(customerDetailsTableStyle("INVOICE NO."));
		table2.addCell(customerDetailsTableStyle(invoiceDataClass.getInvoiceNo()));
		//row2
		table2.addCell(customerDetailsTableStyle("INVOICE DATE "));
		table2.addCell(customerDetailsTableStyle(invoiceDataClass.getInvoiceDate()));
		//row3
		table2.addCell(customerDetailsTableStyle("INVOICE DUE DATE "));
		table2.addCell(customerDetailsTableStyle(invoiceDataClass.getInvoiceDueDate()));
		//row4
		table2.addCell(customerDetailsTableStyle("GST No "));
		table2.addCell(customerDetailsTableStyle(customerDataClass.getCustomerGSTNo()));

		table.addCell(table2);
		return table;
	}

	private static PdfPCell customerDetailsTableStyle(String text) {
		Paragraph paragraph = new Paragraph(text, FontFactory.getFont(fontFamily, textSize, Font.BOLD));
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setPaddingBottom(4);
		cell.setPaddingTop(4);
		cell.setPaddingLeft(5);
		cell.setPaddingRight(5);
		return cell;
	}

	// Customer Table End

	// Item Table Start

	private static PdfPTable itemTableHeading() throws DocumentException {

		PdfPTable table = new PdfPTable(6); // Number of columns
		table.setWidthPercentage(100f);
		table.setWidths(new float[]{2, 4, 2, 3, 2, 3});
		table.addCell(itemTableHeadingCellStyle("SR NO.", true));
		table.addCell(itemTableHeadingCellStyle("ITEM NAME", true));
		table.addCell(itemTableHeadingCellStyle("QTY", false));
		table.addCell(itemTableHeadingCellStyle("RATE", false));
		table.addCell(itemTableHeadingCellStyle("GST %", false));
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

	private static PdfPTable itemTableItemList(List<ItemDataClass> itemDataList) throws DocumentException {

		PdfPTable table = new PdfPTable(6); // Number of columns
		table.setWidthPercentage(100f);
		table.setWidths(new float[]{2, 4, 2, 3, 2, 3});

		for (ItemDataClass itemDataClass : itemDataList) {

			table.addCell(itemTableItemListCellStyle(itemDataClass.getId() + "", true));
			table.addCell(itemTableItemListCellStyle(itemDataClass.getItemName() + "", true));
			table.addCell(itemTableItemListCellStyle(itemDataClass.getQty() + "", false));
			table.addCell(itemTableItemListCellStyle(Utils.getDecimalFormatDoubleIndianRupees(itemDataClass.getRate()), false));
			table.addCell(itemTableItemListCellStyle(itemDataClass.getGst() + "", false));
			table.addCell(itemTableItemListCellStyle(Utils.getDecimalFormatDoubleIndianRupees(itemDataClass.getTotal()), false));

			colorSwitch = !colorSwitch;
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


	public void onStartPage(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Left"), 30, 800, 0);
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Right"), 550, 800, 0);
	}

	public static void onEndPage(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("http://www.your_example.com/"), 110, 10, 0);
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("page " + document.getPageNumber()), 550, 10, 0);
	}

	private Chunk BottomLine() {
		lineSeparator = new LineSeparator();
		lineSeparator.setLineColor(WebColors.getRGBColor("#A00000"));
		lineSeparator.setPercentage(50);
		return new Chunk(lineSeparator);
	}
}
