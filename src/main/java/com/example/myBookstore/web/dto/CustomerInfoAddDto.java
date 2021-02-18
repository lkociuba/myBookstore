package com.example.myBookstore.web.dto;


import javax.validation.constraints.*;

public class CustomerInfoAddDto {

    @Size(min = 1, max = 255, message = "Customer name lenght between 1 and 255")
    @NotNull
    private String customerName;

    @Size(min = 1, max = 255, message = "Customer Address lenght between 1 and 255")
    @NotNull
    private String customerAddress;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is invalid")
    private String customerEmail;

    @Pattern(regexp = "(^$|[0-9]{10})")
    @NotNull
    private String customerPhone;

    public CustomerInfoAddDto() {
    }

    public CustomerInfoAddDto(String customerName, String customerAddress, String customerEmail, String customerPhone) {
        super();
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
}
