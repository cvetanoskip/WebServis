package com.example.demo.demo.discounts;


import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.demo.discounts.DiscountService;
import com.example.demo.demo.discounts.Discount;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Endpoint
public class DiscountEndpoint {

    private static final String NAMESPACE_URI = "http://demo.demo.example.com/discounts";

    @Autowired
    private DiscountService discountService;

    // SOAP method to list discounts by status and type
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DiscountByStatusAndTypeRequest")
    
    @ResponsePayload
    public DiscountResponse listDiscountsByStatusAndType(@RequestPayload DiscountByStatusAndTypeRequest request) {
        List<Discount> discounts = discountService.getDiscountsByStatusAndType(request.getStatus(), request.getType());
        DiscountResponse response = new DiscountResponse();


//         Calendar calendar = Calendar.getInstance();
// calendar.set(2025, Calendar.JANUARY, 26, 15, 30, 0);  // Year, Month (0-based), Day, Hour, Minute, Second
// Date specificDate = calendar.getTime();
//       Calendar calendar1 = Calendar.getInstance();
// calendar.set(2025, Calendar.FEBRUARY, 26, 15, 30, 0);  // Year, Month (0-based), Day, Hour, Minute, Second
// Date specificDate1 = calendar.getTime();
//         Discount discount=new Discount("Discount on Food",45.00,"Food","Active",specificDate,  specificDate1);
//         // for (Discount discount : discounts) {
        //     response.setDiscounts(discount);
        // }
        response.setDiscounts(discounts);
        return response;
    }

    // SOAP method to list discounts above 45%
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DiscountAbove45Request")
    
    @ResponsePayload
    public DiscountResponse listDiscountsAbove45(@RequestPayload DiscountAbove45Request request) {
        List<Discount> discounts = discountService.getDiscountsAbove45();
        DiscountResponse response = new DiscountResponse();
        response.setDiscounts(discounts);
        return response;
    }

    // SOAP method to list discounts ending in 2 days
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DiscountEndingIn2DaysRequest")
    
    @ResponsePayload
    public DiscountResponse listDiscountsEndingIn2Days(@RequestPayload DiscountEndingIn2DaysRequest request) {
        List<Discount> discounts = discountService.getDiscountsEndingIn2Days();
        DiscountResponse response = new DiscountResponse();
        response.setDiscounts(discounts);
        return response;
    }
}

