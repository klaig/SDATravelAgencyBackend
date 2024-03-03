package sda.jre28.travelagency.service;

import org.springframework.stereotype.Service;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.TourRepository;

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
}
