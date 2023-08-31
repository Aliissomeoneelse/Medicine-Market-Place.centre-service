package com.example.MedicineMarketPlace.centreservice.repository;

import com.example.MedicineMarketPlace.centreservice.entity.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RatingRepository extends CrudRepository<Rating,Integer> {
    Optional<Rating> findByRatingIdAndDeletedAtIsNull(Integer id);
    Set<Rating> findAllByCentreIdAndDeletedAtIsNull(Integer id);
}
