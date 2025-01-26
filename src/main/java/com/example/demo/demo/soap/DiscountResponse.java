package com.example.demo.demo.soap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import com.example.demo.demo.model.Discount;
@XmlRootElement(name = "DiscountResponse")
public class DiscountResponse {

    private List<Discount> discounts;

    // Getters and Setters
    @XmlElement
    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }
}
