package sda.jre28.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sda.jre28.travelagency.controller.specifications.TourSpecifications;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.repository.query.QueryUtils.applySorting;

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

    public List<Tour> findAllByDepartureDateBetween (LocalDate minDate, LocalDate maxDate) {
        return tourRepository.findAllByDepartureDateBetween(minDate, maxDate);
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

    public Page<Tour> getTours(String destination, Boolean promoted, LocalDate minDate, LocalDate maxDate, Integer length, String sort, Pageable pageable) {
        Specification<Tour> spec = Specification.where(null);

        if (destination != null && !destination.isEmpty()) {
            spec = spec.and(TourSpecifications.hasDestination(destination));
        }

        if (promoted != null) {
            spec = spec.and(TourSpecifications.isPromoted(promoted));
        }

        if (sort != null && !sort.isEmpty()) {
            pageable = applySorting(sort, pageable);
        }

        if (minDate != null || maxDate != null) {
            spec = spec.and(TourSpecifications.hasDepartureDateBetween(minDate, maxDate));
        }

        if (length != null) {
            spec = spec.and(TourSpecifications.hasLength(length));
        }
        return tourRepository.findAll(spec, pageable);


    }

    private Pageable applySorting(String sort, Pageable pageable) {
        // Extract sort direction and sort property from 'sort' parameter
        String[] sortParams = sort.split(",");
        String sortProperty = sortParams[0];

        // Default sort direction is ascending
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (sortParams.length > 1 && "desc".equalsIgnoreCase(sortParams[1])) {
            sortDirection = Sort.Direction.DESC;
        }

        // Create a new Sort object with the extracted sort property and direction
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortDirection, sortProperty));
    }
}
