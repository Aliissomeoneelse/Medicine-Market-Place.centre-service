package com.example.MedicineMarketPlace.centreservice.repository;

import com.example.MedicineMarketPlace.centreservice.entity.Centre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CentreRepository extends CrudRepository<Centre,Integer> {
    Optional<Centre> findByCentreIdAndDeletedAtIsNull(Integer id);
    Set<Centre>findAllByDoctorIdAndDeletedAtIsNull(Integer id);
    Set<Centre>findAllByMedicalServiceIdAndDeletedAtIsNull(Integer id);
}
