package sda.jre28.travelagency.service;

import org.springframework.stereotype.Service;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    private final PurchaseDataRepository purchaseDataRepository;
    private final TourRepository tourRepository;

    public AdminService(PurchaseDataRepository purchaseDataRepository, TourRepository tourRepository) {

        this.purchaseDataRepository = purchaseDataRepository;
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
    public List<PurchaseData> findAllPurchaseDatas() {
        return purchaseDataRepository.findAll();
    }

    public List<PurchaseData> findAllByIsPurchased(boolean isPurchased) {
        return purchaseDataRepository.findAllByIsPurchased(isPurchased);
    }
    public List<PurchaseData> findAllByUserId(Long userId) {
        List<PurchaseData> purchaseData = purchaseDataRepository.findAllByUserId(userId);
        List<PurchaseData> purchaseDatas = new ArrayList<>();
        for (PurchaseData data : purchaseData) {
            if (data.isPurchased()) {
                purchaseDatas.add(data);
            }
        }
        return purchaseDatas;
    }
}