package com.tgd.dto.request.payment;

import com.tgd.enums.PaymentMethod;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CheckoutRequest {
	@NotBlank(message = "Shipping full name is required")
	@Size(max = 100, message = "The maximun character of shipping full name is 100")
	private String shippingFullName;

	@NotBlank(message = "Shipping phone number is required")
	@Size(max = 20, message = "The maximum length of shipping phone number is 20")
	@Pattern(regexp = "^[0-9]+$", message = "Shipping phone number must contain only numbers")
	private String shippingPhoneNumber;

	@NotBlank(message = "Shipping address is required")
	@Size(max = 1000, message = "The maximun character of shipping address is 1000")
	private String shippingAddress;

	@NotNull(message = "Payment method is required")
	private PaymentMethod paymentMethod;

	public CheckoutRequest(String shippingFullName, String shippingPhoneNumber, String shippingAddress,
			PaymentMethod paymentMethod) {
		super();
		this.shippingFullName = shippingFullName;
		this.shippingPhoneNumber = shippingPhoneNumber;
		this.shippingAddress = shippingAddress;
		this.paymentMethod = paymentMethod;
	}

	public CheckoutRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getShippingFullName() {
		return shippingFullName;
	}

	public void setShippingFullName(String shippingFullName) {
		this.shippingFullName = shippingFullName;
	}

	public String getShippingPhoneNumber() {
		return shippingPhoneNumber;
	}

	public void setShippingPhoneNumber(String shippingPhoneNumber) {
		this.shippingPhoneNumber = shippingPhoneNumber;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}
