package sda.jre28.travelagency.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.jre28.travelagency.exceptions.NoAvailableSeatsException;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;

import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PurchaseDataServiceTest {

    @Mock
    private PurchaseDataRepository purchaseDataRepository;

    @Mock
    private TourRepository tourRepository;

    @InjectMocks
    private PurchaseDataService purchaseDataService;

    @Test
    public void testCreatePurchaseData_returnSuccessfully() {

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = createTestPurchaseData();
        when(purchaseDataRepository.save(purchaseData)).thenReturn(purchaseData);

        // Calling the method
        PurchaseData purchaseDataResult = purchaseDataService.createPurchaseData(purchaseData);

        // Assert
        assertEquals(purchaseData.getId(), purchaseDataResult.getId());
        assertEquals(purchaseData.getNumberOfAdults(), purchaseDataResult.getNumberOfAdults());
    }

    @Test
    public void testCalculateTotal_returnSuccessfully() {

        // Mock data
        Long tourId = 1L;
        Long purchaseDataId = 1L;

        // Mock behaviour of tourRepository
        Tour tour = createTestTour();
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tour));

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = createTestPurchaseData();
        when(purchaseDataRepository.findById(purchaseDataId)).thenReturn(Optional.of(purchaseData));

        // Calling the method
        double total = purchaseDataService.calculateTotal(purchaseDataId);

        // Assert
        double expectedTotal = (tour.getAdultPrice() * purchaseData.getNumberOfAdults()) + (tour.getChildPrice() * purchaseData.getNumberOfChildren());
        assertEquals(expectedTotal, total);
        assertEquals(tour.getId(), tourId);
        assertEquals(purchaseData.getId(), purchaseDataId);

    }

    @SneakyThrows
    @Test
    public void testFinalizePurchase_returnSuccessfully() {

        // Mock data
        Long purchaseDataId = 1L;
        Long tourId = 1L;
        boolean isPurchased = false;
        int availableSeats = 30;

        // Mock behaviour of tourRepository
        Tour tour = createTestTour();
        tour.setAvailableSeats(availableSeats);
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tour));
        when(tourRepository.save(tour)).thenReturn(tour);

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = createTestPurchaseData();
        purchaseData.setPurchased(isPurchased);
        when(purchaseDataRepository.findById(purchaseDataId)).thenReturn(Optional.of(purchaseData));
        when(purchaseDataRepository.save(purchaseData)).thenReturn(purchaseData);

        // Calling the method
        purchaseDataService.finalizePurchase(purchaseDataId);

        // Assert
        int expectedAvailableSeats = availableSeats - purchaseData.getNumberOfAdults() - purchaseData.getNumberOfChildren();
        int actualAvailableSeats = tour.getAvailableSeats();
        assertEquals(expectedAvailableSeats, actualAvailableSeats);
        assertTrue(purchaseData.isPurchased());
    }

    @Test
    public void testFinalizePurchase_throwsException() {

        // Mock data
        Long purchaseDataId = 1L;
        Long tourId = 1L;
        int availableSeats = 0;

        // Mock behaviour of tourRepository
        Tour tour = createTestTour();
        tour.setAvailableSeats(availableSeats);
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tour));

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = createTestPurchaseData();
        when(purchaseDataRepository.findById(purchaseDataId)).thenReturn(Optional.of(purchaseData));

        // Assert
        assertThrows(NoAvailableSeatsException.class, () -> purchaseDataService.finalizePurchase(purchaseDataId));
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

    public PurchaseData createTestPurchaseData() {
        PurchaseData purchaseData = new PurchaseData();
        purchaseData.setId(1L);
        purchaseData.setUserId(1L);
        purchaseData.setTourId(1L);
        purchaseData.setNumberOfAdults(5);
        purchaseData.setNumberOfChildren(3);
        purchaseData.setPurchased(true);

        return purchaseData;
    }
}
