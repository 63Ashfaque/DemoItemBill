package com.example.demo.ai.ocr.demoitembill;

public class ReportItem {


	private int invoiceId;
	private String invoiceDate;
	private String invoiceName;
	private double invoiceNetAmount;
	private double invoiceDiscount;
	private double invoiceGST;
	private double invoiceTotal;

	public ReportItem(int invoiceId, String invoiceDate, String invoiceName, double invoiceNetAmount, double invoiceDiscount, double invoiceGST, double invoiceTotal) {
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		this.invoiceName = invoiceName;
		this.invoiceNetAmount = invoiceNetAmount;
		this.invoiceDiscount = invoiceDiscount;
		this.invoiceGST = invoiceGST;
		this.invoiceTotal = invoiceTotal;
	}

	public ReportItem() {

	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public double getInvoiceNetAmount() {
		return invoiceNetAmount;
	}

	public void setInvoiceNetAmount(double invoiceNetAmount) {
		this.invoiceNetAmount = invoiceNetAmount;
	}

	public double getInvoiceGST() {
		return invoiceGST;
	}

	public void setInvoiceGST(double invoiceGST) {
		this.invoiceGST = invoiceGST;
	}

	public double getInvoiceTotal() {
		return invoiceTotal;
	}

	public void setInvoiceTotal(double invoiceTotal) {
		this.invoiceTotal = invoiceTotal;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public double getInvoiceDiscount() {
		return invoiceDiscount;
	}

	public void setInvoiceDiscount(double invoiceDiscount) {
		this.invoiceDiscount = invoiceDiscount;
	}
}
