package sda.jre28.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TourService {
    private final TourRepository tourRepository;
    private final PurchaseDataRepository purchaseDataRepository;

    @Autowired
    public TourService(TourRepository tourRepository, PurchaseDataRepository purchaseDataRepository) {
        this.tourRepository = tourRepository;
        this.purchaseDataRepository = purchaseDataRepository;
    }

    public Tour findById(Long tourId) {
        return tourRepository.findById(tourId).orElse(null);
    }

    public List<Tour> findAllTours() {
        return tourRepository.findAll();
    }

    public List<Tour> findAllByDestination (CityType cityType) {
        return tourRepository.findAllByDestination(cityType);
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

    public List<Tour> findAllBoughtTours(Long userId) {
        List<PurchaseData> purchaseData = purchaseDataRepository.findAllByUserId(userId);
        List<Tour> tours = new ArrayList<>();
        for (PurchaseData data : purchaseData) {
            Tour tour = tourRepository.findById(data.getTourId()).orElse(null);
            if (data.isPurchased()) {
                tours.add(tour);
            }

        }
        return tours;
    }
}
