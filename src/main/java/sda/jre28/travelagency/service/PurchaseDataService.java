package sda.jre28.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.jre28.travelagency.exceptions.NoAvailableSeatsException;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;

@Service
public class PurchaseDataService {
    private final PurchaseDataRepository purchaseDataRepository;
    private final TourRepository tourRepository;

    @Autowired
    public PurchaseDataService(PurchaseDataRepository purchaseDataRepository, TourRepository tourRepository) {
        this.purchaseDataRepository = purchaseDataRepository;
        this.tourRepository = tourRepository;
    }

    public PurchaseData createPurchaseData(PurchaseData purchaseData) {
        return purchaseDataRepository.save(purchaseData);
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

    public void finalizePurchase(Long purchaseDataId) throws NoAvailableSeatsException {
        PurchaseData purchaseData = purchaseDataRepository.findById(purchaseDataId).orElse(null);
        if (purchaseData != null) {
            purchaseData.setPurchased(true);
            Tour updatedTour = tourRepository.findById(purchaseData.getTourId()).orElse(null);
            if (updatedTour != null) {
                if (updatedTour.getAvailableSeats() < (purchaseData.getNumberOfAdults() + purchaseData.getNumberOfChildren())){
                    throw new NoAvailableSeatsException("Not enough available seats!");
                }
                updatedTour.setAvailableSeats(updatedTour.getAvailableSeats() - purchaseData.getNumberOfAdults() - purchaseData.getNumberOfChildren());
                tourRepository.save(updatedTour);
            }
            purchaseDataRepository.save(purchaseData);
        }
    }




}
