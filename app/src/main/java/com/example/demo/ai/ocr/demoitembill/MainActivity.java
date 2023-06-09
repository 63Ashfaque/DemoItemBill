package com.example.demo.ai.ocr.demoitembill;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private static final int STORAGE_PERMISSION_CODE = 1;

	ArrayList<ItemDataClass> itemDataList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		for (int i = 1; i < 10; i++) {
			int qty = 10;
			double rate = i;
			double sub = qty * rate;
			double gst = sub / 10; //10%
			double total = sub + gst;
			itemDataList.add(new ItemDataClass(i, "ABCDE " + i, qty, rate, gst, total));
		}

		//Seller model class
		SellerDataClass sellerDataClass=new SellerDataClass(101,"ABCD ","ADDRESS","email@id.com","+91-8888-8888-77","GSTNO123456ZQ");

		//Customer model class
		CustomerDataClass customerDataClass=new CustomerDataClass(101,"Customer Name","Address","dfjd@df.df","+91-8888-0000-00","DFJFDGKJ32FD");

		//Invoice model Class
		InvoiceDataClass invoiceDataClass=new InvoiceDataClass("#NO123","06-09-2023","16-09-2023");

		findViewById(R.id.btnGeneratePDF).setOnClickListener(v -> {

			String readImagePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE;

			if (ContextCompat.checkSelfPermission(MainActivity.this, readImagePermission) == PackageManager.PERMISSION_GRANTED) {

				TaxInvoiceFormat.generatePDF(this, itemDataList,sellerDataClass,customerDataClass,invoiceDataClass);
			} else {
				TaxInvoiceFormat.generatePDF(this, itemDataList,sellerDataClass,customerDataClass,invoiceDataClass);
			}


		});
	}

//
//	// Handle permission request result
//	@Override
//	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//		if (requestCode == STORAGE_PERMISSION_CODE) {
//			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//				generatePDF();
//			} else {
//				Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
//			}
//		}
//
//
//	}
}

