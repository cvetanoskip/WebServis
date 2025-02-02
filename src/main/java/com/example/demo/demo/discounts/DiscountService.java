package com.example.demo.demo.discounts;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.example.demo.demo.discounts.Discount;
import com.example.demo.demo.discounts.Discount;
import com.example.demo.demo.discounts.Discount;

import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;


@Service("soapDiscountService")
public class DiscountService {

        private static final Map<Integer, Discount> discounts = new HashMap<>();
        private static final List<Discount> list = new ArrayList<>();


    @Autowired
    com.example.demo.demo.service.DiscountService discountService;

        @PostConstruct
    public void initialize() {
        List<com.example.demo.demo.model.Discount> discountList;
        discountList = discountService.getAllDiscounts();
        // usersList = restService.getAllUsers();

        for(int i = 0; i < discountList.size(); i++){
            Discount discount = new Discount();
 
            discount.setId(discountList.get(i).getId());
            discount.setDescription(discountList.get(i).getDescription());
            discount.setValue(discountList.get(i).getDiscountValue());
            discount.setStatus(discountList.get(i).getStatus().toString());
            discount.setType(discountList.get(i).getDiscountType().toString());
           // discount.setPrice(discountList.get(i).getPrice());
            discount.setStartDate(discountList.get(i).getStartDate());
            discount.setEndDate(discountList.get(i).getEndDate());
            

            discounts.put(discount.getId(), discount);
            list.add(discount);
        }

        // for(int i = 0; i < usersList.size(); i++){
        //     User user = new User();

        //     user.setName(usersList.get(i).getUsername());
        //     user.setId(String.valueOf(usersList.get(i).getId_user()));
        //     user.setPassword(usersList.get(i).getPassword());

        //     users.put(user.getName(), user);
        // }
    }



    public List<Discount> getDiscountsByStatusAndType(String status, String type) {
        // Status statusEnum = Status.valueOf(status);  // Convert string to Status enum
        // DiscountType typeEnum = DiscountType.valueOf(type);  // Convert string to DiscountType enum
        List<Discount> filteredDiscounts = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getStatus().equals(status)  &&  list.get(i).getType().equals(type)  ){
                filteredDiscounts.add(list.get(i));
            }
        }
        return filteredDiscounts;
    }

    public List<Discount> getDiscountsAbove45() {
        List<Discount> filteredDiscounts = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getValue() >=45){
                filteredDiscounts.add(list.get(i));
            }
        }
        return filteredDiscounts;
    }

    public List<Discount> getDiscountsEndingIn2Days() {
        //
        List<Discount> filteredDiscounts = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            long diffInMillis= System.currentTimeMillis()- list.get(i).getEndDate().getTime();
        long daysLeft = TimeUnit.MILLISECONDS.toDays(diffInMillis);
            if( daysLeft >=0 && daysLeft <= 2){
                filteredDiscounts.add(list.get(i));
            }
            
        }
        return filteredDiscounts;
    }
}
