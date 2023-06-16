package com.example.demo.ai.ocr.demoitembill;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private static final int STORAGE_PERMISSION_CODE = 1;
	private List<ReportItem> reportItemList = new ArrayList<>();

	ArrayList<ItemDataClass> itemDataList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//checkPermisionsExternalStorage();

		for (int i = 1; i < 10; i++) {
			int qty = 10;
			double rate = i;
			double sub = qty * rate;
			double gst = sub / 10; //10%
			double total = sub + gst;
			itemDataList.add(new ItemDataClass(i, "ABCDE " + i, qty, rate, gst, total));
		}


//		findViewById(R.id.btnGeneratePDF).setOnClickListener(v -> {
//			TaxInvoiceFormatPDF.generatePDF(this, itemDataList, sellerDataClass, customerDataClass, invoiceDataClass);
//
//			requestWriteStoragePermission();
//		});
		findViewById(R.id.linearLayoutDownload).setOnClickListener(v -> requestWriteStoragePermission());
	}

	private final static int PERMISSION_EXTERNAL_STORAGE = 99;

	private static final int REQUEST_WRITE_STORAGE = 1;

	private void requestWriteStoragePermission() {
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.TIRAMISU) {

			showDownloadDialogBox();
		} else {
			if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
				requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
			} else {
				showDownloadDialogBox();
			}
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == REQUEST_WRITE_STORAGE) {
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				showDownloadDialogBox();
			} else {
				Toast.makeText(this, "Write storage permission denied", Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void showDownloadDialogBox() {

		Dialog dialogDownload = new Dialog(MainActivity.this);
		dialogDownload.setContentView(R.layout.dialog_layout_download_option);
		dialogDownload.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		dialogDownload.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialogDownload.show();

		dialogDownload.findViewById(R.id.imgPDF).setOnClickListener(v1 -> {
			dialogDownload.dismiss();

			//Seller model class
			SellerDataClass sellerDataClass = new SellerDataClass(101, "ABCD ", "ADDRESS", "email@id.com", "+91-8888-8888-77", "GSTNO123456ZQ");

			//Customer model class
			CustomerDataClass customerDataClass = new CustomerDataClass(101, "Customer Name", "Address", "dfjd@df.df", "+91-8888-0000-00", "DFJFDGKJ32FD");

			//Invoice model Class
			InvoiceDataClass invoiceDataClass = new InvoiceDataClass("#NO123", "06-09-2023", "16-09-2023");
			TaxInvoiceFormatPDF.generatePDF(this, itemDataList, sellerDataClass, customerDataClass, invoiceDataClass);

		});

		dialogDownload.findViewById(R.id.imgEXCEL).setOnClickListener(v1 -> {
			dialogDownload.dismiss();
			TaxInvoiceFormatExcel.generateExcelBill(this, reportItemList, "FileName");

		});
	}


}


