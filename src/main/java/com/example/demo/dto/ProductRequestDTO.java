package com.example.demo.dto;

import java.math.BigDecimal;

public class ProductRequestDTO {
    private String sku;
    private String name;
    private String description;
    private String type;
    private String unit;
    private BigDecimal unitPrice;
    private BigDecimal taxRate;
    private Boolean active;

    
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public BigDecimal getTaxRate() { return taxRate; }
    public void setTaxRate(BigDecimal taxRate) { this.taxRate = taxRate; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}