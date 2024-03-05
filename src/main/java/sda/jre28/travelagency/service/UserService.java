package sda.jre28.travelagency.service;

import org.springframework.stereotype.Service;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final PurchaseDataRepository purchaseDataRepository;
    private final TourRepository tourRepository;
    public UserService(PurchaseDataRepository purchaseDataRepository, TourRepository tourRepository) {
        this.purchaseDataRepository = purchaseDataRepository;
        this.tourRepository = tourRepository;
    }

    public List<Tour> findAllBoughtTours(Long userId) {
        List<PurchaseData> purchaseData = purchaseDataRepository.findAllByUserId(userId);
        List<Tour> tours = new ArrayList<>();
        for (PurchaseData data : purchaseData) {
            Tour tour = tourRepository.findById(data.getTourId()).orElse(null);
            tours.add(tour);
        }
        return tours;
    }
}
