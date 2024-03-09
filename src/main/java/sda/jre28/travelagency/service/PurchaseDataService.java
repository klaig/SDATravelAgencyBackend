package sda.jre28.travelagency.service;

import org.springframework.stereotype.Service;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;

import java.util.List;

@Service
public class PurchaseDataService {
    private final PurchaseDataRepository purchaseDataRepository;
    private final TourRepository tourRepository;
    public PurchaseDataService(PurchaseDataRepository purchaseDataRepository, TourRepository tourRepository) {
        this.purchaseDataRepository = purchaseDataRepository;
        this.tourRepository = tourRepository;
    }

    public PurchaseData createPurchaseData(PurchaseData purchaseData) {
        return purchaseDataRepository.save(purchaseData);
    }

    public List<PurchaseData> findAllByUserId(Long userId) {
        return purchaseDataRepository.findAllByUserId(userId);
    }

    public double calculateTotal(Long tourId, Long purchaseDataId) {
        Tour tour = tourRepository.findById(tourId).orElse(null);
        PurchaseData purchaseData = purchaseDataRepository.findById(purchaseDataId).orElse(null);
        if (tour != null && purchaseData != null) {
            double adultPrice = tour.getAdultPrice();
            double childPrice = tour.getChildPrice();
            int numberOfAdults = purchaseData.getNumberOfAdults();
            int numberOfChildren = purchaseData.getNumberOfChildren();
            return (adultPrice * numberOfAdults) + (childPrice * numberOfChildren);
        }
        return 0;
    }

    public void finalizePurchase(Long purchaseDataId) {
        PurchaseData purchaseData = purchaseDataRepository.findById(purchaseDataId).orElse(null);
        if (purchaseData != null) {
            Long tourId = purchaseData.getTourId();
            purchaseData.setPurchased(true);
            Tour updatedTour = tourRepository.findById(tourId).orElse(null);
            if (updatedTour != null) {
                updatedTour.setAvailableSeats(updatedTour.getAvailableSeats() - purchaseData.getNumberOfAdults() - purchaseData.getNumberOfChildren());
                tourRepository.save(updatedTour);
            }
            purchaseDataRepository.save(purchaseData);
        }
    }

}
