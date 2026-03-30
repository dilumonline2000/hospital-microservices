package com.hospital.controller;

import com.hospital.model.Medicine;
import com.hospital.service.PharmacyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@Tag(name = "Pharmacy Controller", description = "CRUD operations for Medicine & Pharmacy Management")
@CrossOrigin(origins = "*")
public class PharmacyController {

    @Autowired
    private PharmacyService pharmacyService;

    @GetMapping
    @Operation(summary = "Get all medicines", description = "Retrieve a list of all medicines in pharmacy")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        return ResponseEntity.ok(pharmacyService.getAllMedicines());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get medicine by ID", description = "Retrieve a specific medicine by its ID")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable String id) {
        return pharmacyService.getMedicineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Add a new medicine", description = "Add a new medicine to the pharmacy inventory")
    public ResponseEntity<Medicine> addMedicine(@Valid @RequestBody Medicine medicine) {
        Medicine created = pharmacyService.addMedicine(medicine);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update medicine", description = "Update an existing medicine's details")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable String id, @Valid @RequestBody Medicine medicine) {
        return ResponseEntity.ok(pharmacyService.updateMedicine(id, medicine));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete medicine", description = "Remove a medicine from the inventory")
    public ResponseEntity<Void> deleteMedicine(@PathVariable String id) {
        pharmacyService.deleteMedicine(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get medicines by category", description = "Find medicines by category (TABLET, CAPSULE, etc.)")
    public ResponseEntity<List<Medicine>> getMedicinesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(pharmacyService.getMedicinesByCategory(category));
    }

    @GetMapping("/search")
    @Operation(summary = "Search medicines", description = "Search medicines by name")
    public ResponseEntity<List<Medicine>> searchMedicines(@RequestParam String name) {
        return ResponseEntity.ok(pharmacyService.searchMedicines(name));
    }

    @GetMapping("/low-stock")
    @Operation(summary = "Get low stock medicines", description = "Get medicines with stock quantity below threshold")
    public ResponseEntity<List<Medicine>> getLowStockMedicines() {
        return ResponseEntity.ok(pharmacyService.getLowStockMedicines());
    }

    @PatchMapping("/{id}/stock")
    @Operation(summary = "Update stock", description = "Add or remove stock quantity for a medicine")
    public ResponseEntity<Medicine> updateStock(@PathVariable String id, @RequestParam int quantity) {
        return ResponseEntity.ok(pharmacyService.updateStock(id, quantity));
    }
}
