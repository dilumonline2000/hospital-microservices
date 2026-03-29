package com.hospital.repository;

import com.hospital.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
    List<Doctor> findBySpecialization(String specialization);
    List<Doctor> findByDepartment(String department);
    List<Doctor> findByAvailable(boolean available);
}
