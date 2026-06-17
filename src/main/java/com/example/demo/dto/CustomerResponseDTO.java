package com.example.demo.dto;

public class CustomerResponseDTO {
    private Long id;
    private String customerType;
    private String companyName;
    private String contactName;
    private String email;
    private String phone;

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getContactName() { return contactName; }
    public void setContactName(String contactName) { this.contactName = contactName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}