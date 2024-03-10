package sda.jre28.travelagency.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TourServiceTest {

    @Mock
    TourRepository tourRepository;
    @Mock
    private PurchaseDataRepository purchaseDataRepository;

    @InjectMocks
    private TourService tourService;



    @Test
    public void testFindById_returnSuccessfully() {
        // Mock data
        Long tourId = 1L;

        // Mock behaviour of tourRepository
        Tour tour = createTestTour();
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tour));

        // Calling the method
        Tour tourResult = tourService.findById(tourId);

        // Assert
        assertEquals(tourId, tourResult.getId());
        assertEquals(tour.getDestination(), tourResult.getDestination());
        assertEquals(tour.getAdultPrice(), tourResult.getAdultPrice());
        assertEquals(tour.getChildPrice(), tourResult.getChildPrice());
        assertEquals(tour.getAvailableSeats(), tourResult.getAvailableSeats());
        assertEquals(tour.getLength(), tourResult.getLength());
        assertEquals(tour.isPromoted(), tourResult.isPromoted());
        assertEquals(tour.getDepartureDate(), tourResult.getDepartureDate());
        assertEquals(tour.getReturnDate(), tourResult.getReturnDate());
    }

    @Test
    public void testFindAllTours_returnSuccessfully() {
        // Mock behaviours of tourRepository
        Tour tour = createTestTour();
        Tour tour2 = createTestTour();
        when(tourRepository.findAll()).thenReturn(List.of(tour, tour2));

        // Calling the method
        List<Tour> tourResult = tourService.findAllTours();

        // Assert
        assert tourResult.size() == 2;
        assertEquals(tour.getId(), tourResult.get(0).getId());
        assertEquals(tour2.getId(), tourResult.get(1).getId());
    }

    @Test
    public void testFindAllByDestination_returnSuccessfully() {
        // Mock data
        CityType destination = CityType.TORONTO;

        // Mock behaviour of tourRepository
        Tour tour = new Tour();
        when(tourRepository.findAllByDestination(destination)).thenReturn(List.of(tour));

        // Calling the method
        List<Tour> tourResult = tourService.findAllByDestination(destination);

        // Assert
        assert tourResult.size() == 1;
        assertEquals(tour.getDestination(), tourResult.get(0).getDestination());
    }

    @Test
    public void testFindAllByDepartureDateBetween_returnSuccessfully() {
        // Mock data
        LocalDate minDate = LocalDate.parse("2024-03-07");
        LocalDate maxDate = LocalDate.parse("2024-03-12");
        LocalDate departureDate = LocalDate.parse("2024-03-09");

        // Mock behaviour of tourRepository
        Tour tour = createTestTour();
        tour.setDepartureDate(departureDate);
        when(tourRepository.findAllByDepartureDateBetween(minDate, maxDate)).thenReturn(List.of(tour));

        // Calling the method
        List<Tour> tourResult = tourService.findAllByDepartureDateBetween(minDate, maxDate);

        // Assert
        assert tourResult.size() == 1;
        assertEquals(tour.getDepartureDate(), tourResult.get(0).getDepartureDate());
    }

    @Test
    public void testFindAllByLength_returnSuccessfully() {
        // Mock data
        int length = 7;

        // Behaviour of tourRepository
        Tour tour = new Tour();
        tour.setLength(length);
        when(tourRepository.findAllByLength(length)).thenReturn(List.of(tour));

        // Calling the method
        List<Tour> tourResult = tourService.findAllByLength(length);

        // Assert
        assert tourResult.size() == 1;
        assertEquals(tour.getLength(), tourResult.get(0).getLength());
    }

    @Test
    public void testFindAllByAdultPriceBetween_returnSuccessfully() {
        // Mock data
        double minPrice = 600;
        double maxPrice = 1000;
        double adultPrice = 700;

        // Mock behaviour of tourRepository
        Tour tour = new Tour();
        tour.setAdultPrice(adultPrice);
        when(tourRepository.findAllByAdultPriceBetween(minPrice, maxPrice)).thenReturn(List.of(tour));

        // Calling the method
        List<Tour> tourResult = tourService.findAllByAdultPriceBetween(minPrice, maxPrice);

        // Assert
        assert tourResult.size() == 1;
        assertEquals(tour.getAdultPrice(), tourResult.get(0).getAdultPrice());
    }


    @Test
    public void testFindAllByPromoted_returnSuccessfully () {
        // Mock data
        boolean promoted = true;

        // Mock behaviour of tourRepository
        Tour tour = new Tour();
        tour.setPromoted(promoted);
        when(tourRepository.findAllByPromoted(promoted)).thenReturn(List.of(tour));

        // Calling the method
        List<Tour> tourResult = tourService.findAllByPromoted(promoted);

        // Assert
        assert tourResult.size() == 1;
        assertEquals(tour.isPromoted(), tourResult.get(0).isPromoted());
    }

    @Test
    public void testFindAllBoughtTours_returnSuccessfully () {
        // Mock data
        Long userId = 1L;
        Long tourId = 1L;
        boolean isPurchased = true;

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = new PurchaseData();
        purchaseData.setUserId(userId);
        purchaseData.setTourId(tourId);
        purchaseData.setPurchased(isPurchased);
        when(purchaseDataRepository.findAllByUserId(userId)).thenReturn(List.of(purchaseData));

        // Mock behaviour of tourRepository
        Tour tour = createTestTour();
        when(tourRepository.findById(purchaseData.getTourId())).thenReturn(Optional.of(tour));

        // Calling the method
        List<Tour> tourResult = tourService.findAllBoughtTours(userId);

        // Assert
        assert tourResult.size() == 1;
        assertEquals(tour.getId(), tourResult.get(0).getId());
    }

    public Tour createTestTour() {
        Tour tour = new Tour();
        tour.setId(1L);
        tour.setDestination(CityType.TORONTO);
        tour.setAdultPrice(250);
        tour.setChildPrice(150);
        tour.setAvailableSeats(30);
        tour.setPromoted(true);
        tour.setLength(7);
        tour.setDepartureDate(LocalDate.parse("2024-03-09"));
        tour.setReturnDate(LocalDate.parse("2024-03-16"));

        return tour;
    }
}
