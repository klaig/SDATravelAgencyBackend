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

        when(tourRepository.findById(1L)).thenReturn(Optional.of(tour));

        Tour tourResult = tourService.findById(1L);

        assertEquals(tourResult.getLength(), 7);
    }

    @Test
    public void testFindAllTours_returnSuccessfully() {
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

        Tour tour2 = new Tour();
        tour2.setId(2L);
        tour2.setDestination(CityType.MEXICO_CITY);
        tour2.setAdultPrice(150);
        tour2.setChildPrice(75);
        tour2.setAvailableSeats(15);
        tour2.setPromoted(false);
        tour2.setLength(3);
        tour2.setDepartureDate(LocalDate.parse("2024-03-10"));
        tour2.setReturnDate(LocalDate.parse("2024-03-13"));

        when(tourRepository.findAll()).thenReturn(List.of(tour, tour2));

        List<Tour> tourResult = tourService.findAllTours();

        assert tourResult.size() == 2;
        assertEquals(tourResult.get(0).getId(), 1L);
    }

    @Test
    public void testFindAllByDestination_returnSuccessfully() {
        Tour tour = new Tour();
        tour.setDestination(CityType.TORONTO);

        when(tourRepository.findAllByDestination(CityType.TORONTO)).thenReturn(List.of(tour));

        List<Tour> tourResult = tourService.findAllByDestination(CityType.TORONTO);

        assertEquals(tourResult.get(0).getDestination(), CityType.TORONTO);
    }

    @Test
    public void testFindAllByDepartureDateBetween_returnSuccessfully() {
        Tour tour = createTestTour();
        LocalDate departureDate = LocalDate.parse("2024-03-09");
        LocalDate returnDate = LocalDate.parse("2024-03-30");
        tour.setDepartureDate(departureDate);

        Tour tour2 = createTestTour();
        LocalDate departureDate2 = LocalDate.parse("2024-02-02");
        tour2.setDepartureDate(departureDate2);

        when(tourRepository.findAllByDepartureDateBetween(departureDate, returnDate)).thenReturn(List.of(tour, tour2));

        List<Tour> tourResult = tourService.findAllByDepartureDateBetween(departureDate, returnDate);

        assert tourResult.size() == 2;
        assertEquals(tourResult.get(0).getDepartureDate(), departureDate);
        assertEquals(tourResult.get(1).getDepartureDate(), departureDate2);
    }

    @Test
    public void testFindAllByLength_returnSuccessfully() {
        Tour tour = new Tour();
        int length = 7;
        tour.setLength(length);

        when(tourRepository.findAllByLength(7)).thenReturn(List.of(tour));

        List<Tour> tourResult = tourService.findAllByLength(7);

        assertEquals(tourResult.get(0).getLength(), length);
    }

    @Test
    public void testFindAllByAdultPriceBetween_returnSuccessfully() {
        Tour tour = new Tour();
        double minPrice = 600;
        double maxPrice = 1000;
        double adultPrice = 700;

        tour.setAdultPrice(adultPrice);

        when(tourRepository.findAllByAdultPriceBetween(minPrice, maxPrice)).thenReturn(List.of(tour));

        List<Tour> tourResult = tourService.findAllByAdultPriceBetween(minPrice, maxPrice);

        assertEquals(tourResult.get(0).getAdultPrice(), adultPrice);
    }


    @Test
    public void testFindAllByPromoted_returnSuccessfully () {
        Tour tour = new Tour();
        boolean promoted = true;
        tour.setPromoted(promoted);

        when(tourRepository.findAllByPromoted(true)).thenReturn(List.of(tour));

        List<Tour> tourResult = tourService.findAllByPromoted(true);

        assertEquals(tourResult.get(0).isPromoted(), promoted);
    }

    @Test
    public void testFindAllBoughtTours_returnSuccessfully () {
        Long userId = 1L;
        Long tourId = 1L;
        Long tourId2 = 2L;
        boolean isPurchased = true;
        boolean isPurchased2 = false;

        PurchaseData purchaseData = new PurchaseData();
        PurchaseData purchaseData2 = new PurchaseData();
        purchaseData.setUserId(userId);
        purchaseData.setTourId(tourId);
        purchaseData.setPurchased(isPurchased);
        purchaseData2.setUserId(userId);
        purchaseData2.setTourId(tourId2);
        purchaseData2.setPurchased(isPurchased2);
        when(purchaseDataRepository.findAllByUserId(userId)).thenReturn(List.of(purchaseData));

        Tour tour = createTestTour();
        Tour tour2 = createTestTour();
        tour2.setId(tourId2);
        when(tourRepository.findById(purchaseData.getTourId())).thenReturn(Optional.of(tour));

        List<Tour> tourResult = tourService.findAllBoughtTours(userId);

        assert tourResult.size() == 1;
        assertEquals(tourResult.get(0).getId(), tourId);

        assertEquals(purchaseData.getUserId(), userId);
        assertEquals(purchaseData.getTourId(), tourId);
        assertEquals(purchaseData.isPurchased(), isPurchased);
        assertEquals(purchaseData2.getUserId(), userId);
        assertEquals(purchaseData2.getTourId(), tourId2);
        assertEquals(purchaseData2.isPurchased(), isPurchased2);
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
