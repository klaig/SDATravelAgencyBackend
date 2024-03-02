package sda.jre28.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.jre28.travelagency.model.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
}
