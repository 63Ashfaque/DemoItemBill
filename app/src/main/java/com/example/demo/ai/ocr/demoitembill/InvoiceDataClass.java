package com.example.demo.ai.ocr.demoitembill;

public class InvoiceDataClass {

	String invoiceNo;
	String invoiceDate;
	String invoiceDueDate;

	public InvoiceDataClass(String invoiceNo, String invoiceDate, String invoiceDueDate) {
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.invoiceDueDate = invoiceDueDate;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceDueDate() {
		return invoiceDueDate;
	}

	public void setInvoiceDueDate(String invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}
}
