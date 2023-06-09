package com.example.demo.ai.ocr.demoitembill;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Utils {

	/**
	 * Log.d("Ashu", msg);
	 * Tag = Ashu
	 * msg = String message
	 */
	@SuppressLint("LogNotTimber")
	public static void logD(String msg) {
		if (BuildConfig.DEBUG) {
			Log.d("Ashu", msg);
		}
	}

	public static void showToast(Activity activity, String msg) {
		Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
	}

	public static void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}

	/**
	 * 1. dd/MM/yyyy <br>
	 * 2. dd-MMM-yyyy<br>
	 * 3. yyyy-MM-dd<br>
	 * 4. MMMM dd, yyyy<br>
	 * 5. etc
	 */

	public static String getCurrentDate(String pattern) {
		return new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date());
	}


	private static final String[] units = {
			"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
			"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
			"Eighteen", "Nineteen"
	};

	private static final String[] tens = {
			"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
	};


	public static String convertNumberToWord(int number) {
		if (number == 0) {
			return "Zero";
		}
		if (number < 0) {
			return "Minus " + convertNumberToWord(Math.abs(number));
		}
		if (number < 20) {
			return units[number];
		}
		if (number < 100) {
			return tens[number / 10] + ((number % 10 != 0) ? " " : "") + units[number % 10];
		}
		if (number < 1000) {
			return units[number / 100] + " Hundred" + ((number % 100 != 0) ? " " : "") + convertNumberToWord(number % 100);
		}
		if (number < 100000) {
			return convertNumberToWord(number / 1000) + " Thousand" + ((number % 1000 != 0) ? " " : "") + convertNumberToWord(number % 1000);
		}
		if (number < 10000000) {
			return convertNumberToWord(number / 100000) + " Lakh" + ((number % 100000 != 0) ? " " : "") + convertNumberToWord(number % 100000);
		}
		if (number < 1000000000) {
			return convertNumberToWord(number / 10000000) + " Crore" + ((number % 10000000 != 0) ? " " : "") + convertNumberToWord(number % 10000000);
		}
		return "Number out of range";
	}

	public static String getRemoveZero(int number) {
		if (number == 0)
			return "0";
		DecimalFormat decimalFormat = new DecimalFormat("##,##,##0");
		return decimalFormat.format(number);
	}

	public static String getDecimalFormatInt(int number) {
		if (number == 0)
			return "0.00";
		DecimalFormat decimalFormat = new DecimalFormat("##,##,##0.00");
		return decimalFormat.format(number);
	}

	public static String getDecimalFormatDoubleIndianRupees(Double number) {
		if (number == null || number == 0)
			return "\u20B9 0.00";
		DecimalFormat decimalFormat = new DecimalFormat("\u20B9 ##,##,##0.00");
		return decimalFormat.format(number);
	}

	public static String getDecimalFormatDouble(Double number) {
		if (number == null || number == 0)
			return "0";
		DecimalFormat decimalFormat = new DecimalFormat("##,##,##0.00");
		return decimalFormat.format(number);
	}
}
