package com.example.demo.ai.ocr.demoitembill;

public class CustomerDataClass {

	int id;
	String customerName;
	String customerAddress;
	String customerEmailId;
	String customerMobileNo;
	String customerGSTNo;

	public CustomerDataClass(int id, String customerName, String customerAddress, String customerEmailId, String customerMobileNo, String customerGSTNo) {
		this.id = id;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerEmailId = customerEmailId;
		this.customerMobileNo = customerMobileNo;
		this.customerGSTNo = customerGSTNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerEmailId() {
		return customerEmailId;
	}

	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}

	public String getCustomerMobileNo() {
		return customerMobileNo;
	}

	public void setCustomerMobileNo(String customerMobileNo) {
		this.customerMobileNo = customerMobileNo;
	}

	public String getCustomerGSTNo() {
		return customerGSTNo;
	}

	public void setCustomerGSTNo(String customerGSTNo) {
		this.customerGSTNo = customerGSTNo;
	}
}
