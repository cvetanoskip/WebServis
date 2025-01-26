package com.example.demo.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.demo.model.Discount;
import com.example.demo.demo.model.Destination;
import com.example.demo.demo.repository.DiscountRepository;
import com.example.demo.demo.repository.DestinationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final DestinationRepository destinationRepository;

    public DiscountService(DiscountRepository discountRepository, DestinationRepository destinationRepository) {
        this.discountRepository = discountRepository;
        this.destinationRepository = destinationRepository;
    }

    // Get all discounts (SOAP Model)
    public List<com.example.demo.demo.soap.models.Discount> getAllDiscounts() {
        List<com.example.demo.demo.model.Discount> discounts = discountRepository.findAll();
        return discounts.stream()
                .map(this::convertToSoapModel)
                .toList();
    }

    // Convert Model to SOAP
    private com.example.demo.demo.soap.models.Discount convertToSoapModel(com.example.demo.demo.model.Discount discount) {
        com.example.demo.demo.soap.models.Discount soapDiscount = new com.example.demo.demo.soap.models.Discount();
        soapDiscount.setId(discount.getId());
        soapDiscount.setDescription(discount.getDescription());
        soapDiscount.setDiscountValue(discount.getDiscountValue());
        soapDiscount.setStartDate(discount.getStartDate());
        soapDiscount.setEndDate(discount.getEndDate());
        return soapDiscount;
    }

    // Get sorted discounts
    public List<com.example.demo.demo.soap.models.Discount> getSortedDiscounts() {
        List<com.example.demo.demo.soap.models.Discount> discounts = getAllDiscounts();
        discounts.sort((d1, d2) -> Double.compare(d2.getDiscountValue(), d1.getDiscountValue()));
        return discounts;
    }

    // Add a new discount
    public Discount addDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    // Add a discount to a destination
    public Discount addDiscountToDestination(Integer destinationId, Discount discount) {
        Optional<Destination> destinationOptional = destinationRepository.findById(destinationId);

        if (destinationOptional.isPresent()) {
            Destination destination = destinationOptional.get();
            discount.setDestination(destination);
            return discountRepository.save(discount);
        } else {
            throw new IllegalArgumentException("Destination with ID " + destinationId + " does not exist.");
        }
    }

    // Remove an existing discount by ID
    public void removeDiscountById(Integer discountId) {
        if (discountRepository.existsById(discountId)) {
            discountRepository.deleteById(discountId);
        } else {
            throw new IllegalArgumentException("Discount with ID " + discountId + " does not exist.");
        }
    }

    // Remove a discount from a destination
    public void removeDiscount(Integer discountId) {
        if (discountRepository.existsById(discountId)) {
            Discount discount = discountRepository.findById(discountId).orElseThrow(() ->
                new IllegalArgumentException("Discount with ID " + discountId + " not found.")
            );
            discount.setDestination(null); // Detach from destination
            discountRepository.delete(discount);
        } else {
            throw new IllegalArgumentException("Discount with ID " + discountId + " does not exist.");
        }
    }

    // Get all discounts for a destination
    public List<Discount> getDiscountsByDestination(Integer destinationId) {
        Optional<Destination> destinationOptional = destinationRepository.findById(destinationId);

        if (destinationOptional.isPresent()) {
            return discountRepository.findByDestination(destinationOptional.get());
        } else {
            throw new IllegalArgumentException("Destination with ID " + destinationId + " does not exist.");
        }
    }
}
