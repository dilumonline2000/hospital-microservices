package com.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "medicines")
public class Medicine {

    @Id
    private String id;

    @NotBlank(message = "Medicine name is required")
    private String name;

    private String genericName;
    private String manufacturer;

    @NotBlank(message = "Category is required")
    private String category; // TABLET, CAPSULE, SYRUP, INJECTION, CREAM, DROPS

    private String description;

    @Min(value = 0, message = "Price must be positive")
    private double price;

    @Min(value = 0, message = "Stock quantity must be positive")
    private int stockQuantity;

    private int reorderLevel;
    private LocalDate expiryDate;
    private LocalDate manufacturedDate;
    private boolean prescriptionRequired;
    private String dosageInstructions;
}
