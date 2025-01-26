package com.example.demo.demo.repository;

import com.example.demo.demo.model.Discount;
import com.example.demo.demo.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    List<Discount> findByDestination(Destination destination);
}
