package sda.jre28.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.model.User;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;
import sda.jre28.travelagency.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    private final PurchaseDataRepository purchaseDataRepository;
    private final TourRepository tourRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminService(PurchaseDataRepository purchaseDataRepository, TourRepository tourRepository, UserRepository userRepository) {

        this.purchaseDataRepository = purchaseDataRepository;
        this.tourRepository = tourRepository;
        this.userRepository = userRepository;
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
    public List<PurchaseData> findAllPurchaseData() {
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

    public List<User> findAllUsersByTour(Long tourId) {
        List<PurchaseData> boughtTours = purchaseDataRepository.findAllByIsPurchased(true);
        List<User> users = new ArrayList<>();
        for (PurchaseData data : boughtTours) {
            if (data.getTourId().equals(tourId)) {
                Long userId = data.getUserId();
                users.add(userRepository.findById(userId).orElse(null));
            }
        }
        return users;
    }
}
