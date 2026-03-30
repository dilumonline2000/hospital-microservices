package com.hospital.repository;

import com.hospital.model.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicineRepository extends MongoRepository<Medicine, String> {
    List<Medicine> findByCategory(String category);
    List<Medicine> findByNameContainingIgnoreCase(String name);
    List<Medicine> findByStockQuantityLessThanEqual(int quantity);
    List<Medicine> findByPrescriptionRequired(boolean required);
}
