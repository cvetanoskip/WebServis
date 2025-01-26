package com.example.demo.demo.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DiscountRequest")
public class DiscountRequest {
    
    private String status;
    private String type;
    private double minDiscount;
    private int daysUntilEnd;

    // Getters and Setters
    @XmlElement
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement
    public double getMinDiscount() {
        return minDiscount;
    }

    public void setMinDiscount(double minDiscount) {
        this.minDiscount = minDiscount;
    }

    @XmlElement
    public int getDaysUntilEnd() {
        return daysUntilEnd;
    }

    public void setDaysUntilEnd(int daysUntilEnd) {
        this.daysUntilEnd = daysUntilEnd;
    }
}

