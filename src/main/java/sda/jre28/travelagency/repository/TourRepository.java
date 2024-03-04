package sda.jre28.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.ContinentType;
import sda.jre28.travelagency.model.CountryType;
import sda.jre28.travelagency.model.Tour;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findAllByDestination(CityType cityType);
//    List<Tour> findAllByCountry(CountryType countryType);
//    List<Tour> findAllByContinent(ContinentType continentType);
    List<Tour> findAllByDepartureDate(LocalDate departureDate);
    List<Tour> findAllByDepartureDateBetween(LocalDate departureDate, LocalDate returnDate);
    List<Tour> findAllByLength(Integer length);
    List<Tour> findAllByAdultPriceBetween(double minPrice, double maxPrice);
    List<Tour> findAllByPromoted(boolean promoted);
}
