package sda.jre28.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.Tour;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long>, JpaSpecificationExecutor<Tour> {
    List<Tour> findAllByDestination(CityType cityType);
    List<Tour> findAllByDepartureDateBetween(LocalDate minDate, LocalDate maxDate);
    List<Tour> findAllByLength(Integer length);
    List<Tour> findAllByAdultPriceBetween(double minPrice, double maxPrice);
    List<Tour> findAllByPromoted(boolean promoted);

}
