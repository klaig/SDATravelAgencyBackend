package sda.jre28.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.Tour;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findAllByDestination(CityType cityType);
    //TODO findAllBy:
    //  country, continent, departure date, length, price, promotion
}
