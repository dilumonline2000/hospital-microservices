package com.hospital.service;

import com.hospital.model.Medicine;
import com.hospital.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PharmacyService {

    @Autowired
    private MedicineRepository medicineRepository;

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Optional<Medicine> getMedicineById(String id) {
        return medicineRepository.findById(id);
    }

    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public Medicine updateMedicine(String id, Medicine medicineDetails) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));

        medicine.setName(medicineDetails.getName());
        medicine.setGenericName(medicineDetails.getGenericName());
        medicine.setManufacturer(medicineDetails.getManufacturer());
        medicine.setCategory(medicineDetails.getCategory());
        medicine.setDescription(medicineDetails.getDescription());
        medicine.setPrice(medicineDetails.getPrice());
        medicine.setStockQuantity(medicineDetails.getStockQuantity());
        medicine.setReorderLevel(medicineDetails.getReorderLevel());
        medicine.setExpiryDate(medicineDetails.getExpiryDate());
        medicine.setManufacturedDate(medicineDetails.getManufacturedDate());
        medicine.setPrescriptionRequired(medicineDetails.isPrescriptionRequired());
        medicine.setDosageInstructions(medicineDetails.getDosageInstructions());

        return medicineRepository.save(medicine);
    }

    public void deleteMedicine(String id) {
        medicineRepository.deleteById(id);
    }

    public List<Medicine> getMedicinesByCategory(String category) {
        return medicineRepository.findByCategory(category);
    }

    public List<Medicine> searchMedicines(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Medicine> getLowStockMedicines() {
        return medicineRepository.findByStockQuantityLessThanEqual(10);
    }

    public Medicine updateStock(String id, int quantity) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));
        medicine.setStockQuantity(medicine.getStockQuantity() + quantity);
        return medicineRepository.save(medicine);
    }
}
