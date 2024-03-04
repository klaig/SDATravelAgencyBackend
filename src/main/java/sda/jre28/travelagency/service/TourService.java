package sda.jre28.travelagency.service;

import org.springframework.stereotype.Service;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.TourRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TourService {
    private final TourRepository tourRepository;

    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public Tour createTour(Tour tour) { return tourRepository.save(tour);}

    public void updateTour(Long id, Tour updatedTour) {
        Tour existingTour = tourRepository.findById(id).orElse(null);

        if (existingTour != null) {
            existingTour.setAdultPrice(updatedTour.getAdultPrice());
            existingTour.setChildPrice(updatedTour.getChildPrice());
            existingTour.setDepartureDate(updatedTour.getDepartureDate());
            existingTour.setReturnDate(updatedTour.getReturnDate());
            existingTour.setDestination(updatedTour.getDestination());
            existingTour.setLength(updatedTour.getLength());
            existingTour.setPromoted(updatedTour.isPromoted());
            existingTour.setAvailableSeats(updatedTour.getAvailableSeats());

            tourRepository.save(existingTour);
        }
    }

    public void deleteTour(Long Id) {
        tourRepository.deleteById(Id);
    }

    public List<Tour> findAllTours() {
        return tourRepository.findAll();
    }

    public List<Tour> findAllByDestination (CityType cityType) {
        return tourRepository.findAllByDestination(cityType);
    }

    public List<Tour> findAllByDepartureDate (LocalDate departureDate) {
        return tourRepository.findAllByDepartureDate(departureDate);
    }

    public List<Tour> findAllByDepartureDateBetween (LocalDate departureDate, LocalDate returnDate) {
        return tourRepository.findAllByDepartureDateBetween(departureDate, returnDate);
    }
    public List<Tour> findAllByLength (Integer length) {
        return tourRepository.findAllByLength(length);
    }

    public List<Tour> findAllByAdultPriceBetween (double minPrice, double maxPrice) {
        return tourRepository.findAllByAdultPriceBetween(minPrice, maxPrice);
    }

    public List<Tour> findAllByPromoted (boolean promoted) {
        return tourRepository.findAllByPromoted(promoted);
    }

}
