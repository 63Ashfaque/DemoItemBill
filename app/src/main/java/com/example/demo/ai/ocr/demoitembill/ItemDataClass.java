package com.example.demo.ai.ocr.demoitembill;

public class ItemDataClass {

	int id;
	String itemName;
	int qty;
	double rate;
	double gst;
	double total;

	ItemDataClass(int id, String itemName, int qty, double rate, double gst, double total) {

		this.id = id;
		this.itemName = itemName;
		this.qty = qty;
		this.rate = rate;
		this.gst = gst;
		this.total = total;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
