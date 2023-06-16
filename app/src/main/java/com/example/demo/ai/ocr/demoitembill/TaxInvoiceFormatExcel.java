package com.example.demo.ai.ocr.demoitembill;

import android.app.Activity;
import android.os.Environment;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TaxInvoiceFormatExcel {

	public static void generateExcelBill(Activity activity, List<ReportItem> reportItemList, String strFileName) {
		try (HSSFWorkbook hssfWorkbook = new HSSFWorkbook()) {


			HSSFSheet hssfSheet = hssfWorkbook.createSheet(strFileName);
			// Create header row
			HSSFRow headerRow = hssfSheet.createRow(0);
			headerRow.createCell(0).setCellValue("#");
			headerRow.createCell(1).setCellValue("DATE");
			headerRow.createCell(2).setCellValue("INVOICE NO");
			headerRow.createCell(3).setCellValue("CUSTOMER NAME");
			headerRow.createCell(4).setCellValue("NET AMOUNT");
			headerRow.createCell(5).setCellValue("DISCOUNT");
			headerRow.createCell(6).setCellValue("GST");
			headerRow.createCell(7).setCellValue("Total");
			

			int i = 0;
			for (ReportItem item : reportItemList) {
				HSSFRow row = hssfSheet.createRow(i + 1);
				row.createCell(0).setCellValue(i + 1);
				row.createCell(1).setCellValue(item.getInvoiceDate());
				row.createCell(2).setCellValue(item.getInvoiceId());
				row.createCell(3).setCellValue(item.getInvoiceName());
				row.createCell(4).setCellValue(item.getInvoiceNetAmount());
				row.createCell(5).setCellValue(item.getInvoiceDiscount());
				row.createCell(6).setCellValue(item.getInvoiceGST());
				row.createCell(7).setCellValue(item.getInvoiceTotal());
				i++;
			}

			// Save the workbook to a file
			try {
				String strDate = Utils.getCurrentDate("-yyMMddhhmmss");
				String fileName = strFileName + strDate + ".xlsx";
				File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
				File file = new File(path, fileName);
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				hssfWorkbook.write(fileOutputStream);
				fileOutputStream.close();

				Toast.makeText(activity, "XLS Generated Successfully", Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				Toast.makeText(activity, "Error generating XLS " + e.getMessage(), Toast.LENGTH_SHORT).show();
			}

		} catch (Exception e) {
			Toast.makeText(activity, "Error generating XLS " + e.getMessage(), Toast.LENGTH_SHORT).show();
		}

	}


}
