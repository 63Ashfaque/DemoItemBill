package com.example.demo.ai.ocr.demoitembill;

public class SellerDataClass {

	int id;
	String sellerName;
	String sellerAddress;
	String sellerEmailId;
	String sellerMobileNo;
	String sellerGSTNo;

	public SellerDataClass(int id, String sellerName, String sellerAddress, String sellerEmailId, String sellerMobileNo, String sellerGSTNo) {
		this.id = id;
		this.sellerName = sellerName;
		this.sellerAddress = sellerAddress;
		this.sellerEmailId = sellerEmailId;
		this.sellerMobileNo = sellerMobileNo;
		this.sellerGSTNo = sellerGSTNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public String getSellerEmailId() {
		return sellerEmailId;
	}

	public void setSellerEmailId(String sellerEmailId) {
		this.sellerEmailId = sellerEmailId;
	}

	public String getSellerMobileNo() {
		return sellerMobileNo;
	}

	public void setSellerMobileNo(String sellerMobileNo) {
		this.sellerMobileNo = sellerMobileNo;
	}

	public String getSellerGSTNo() {
		return sellerGSTNo;
	}

	public void setSellerGSTNo(String sellerGSTNo) {
		this.sellerGSTNo = sellerGSTNo;
	}
}
