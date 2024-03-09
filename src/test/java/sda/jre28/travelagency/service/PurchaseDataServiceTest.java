package sda.jre28.travelagency.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.jre28.travelagency.exceptions.NoAvailableSeatsException;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;
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

        // Mock data
        int numberOfAdults = 3;

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = new PurchaseData();
        purchaseData.setNumberOfAdults(numberOfAdults);
        when(purchaseDataRepository.save(purchaseData)).thenReturn(purchaseData);

        // Calling the method
        PurchaseData purchaseDataResult = purchaseDataService.createPurchaseData(purchaseData);

        // Assert
        assertEquals(purchaseDataResult.getNumberOfAdults(), 3);
    }

    @Test
    public void testCalculateTotal_returnSuccessfully() {

        // Mock data
        Long tourId = 1L;
        Long purchaseDataId = 1L;
        double adultPrice = 100.0;
        double childPrice = 50.0;
        int numberOfAdults = 2;
        int numberOfChildren = 1;

        // Mock behaviour of tourRepository
        Tour tour = new Tour();
        tour.setAdultPrice(adultPrice);
        tour.setChildPrice(childPrice);
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tour));

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = new PurchaseData();
        purchaseData.setNumberOfAdults(numberOfAdults);
        purchaseData.setNumberOfChildren(numberOfChildren);
        when(purchaseDataRepository.findById(purchaseDataId)).thenReturn(Optional.of(purchaseData));

        // Calling the method
        double total = purchaseDataService.calculateTotal(tourId, purchaseDataId);

        // Assert
        double expectedTotal = (adultPrice * numberOfAdults) + (childPrice * numberOfChildren);
        assertEquals(expectedTotal, total);

    }

    @SneakyThrows
    @Test
    public void testFinalizePurchase_returnSuccessfully() {

        // Mock data
        Long purchaseDataId = 1L;
        Long tourId = 1L;
        boolean isPurchased = true;
        int availableSeats = 50;
        int numberOfAdults = 5;
        int numberOfChildren = 3;

        // Mock behaviour of tourRepository
        Tour tour = new Tour();
        tour.setAvailableSeats(availableSeats);
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tour));
        when(tourRepository.save(tour)).thenReturn(tour);

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = new PurchaseData();
        purchaseData.setPurchased(isPurchased);
        purchaseData.setTourId(tourId);
        purchaseData.setNumberOfAdults(numberOfAdults);
        purchaseData.setNumberOfChildren(numberOfChildren);
        when(purchaseDataRepository.findById(purchaseDataId)).thenReturn(Optional.of(purchaseData));
        when(purchaseDataRepository.save(purchaseData)).thenReturn(purchaseData);

        // Calling the method
        purchaseDataService.finalizePurchase(purchaseDataId);

        // Assert
        int expectedAvailableSeats = availableSeats - numberOfAdults - numberOfChildren;
        int actualAvailableSeats = tour.getAvailableSeats();
        assertEquals(expectedAvailableSeats, actualAvailableSeats);
    }

    @Test
    public void testFinalizePurchase_throwsException() {

        // Mock data
        Long purchaseDataId = 1L;
        Long tourId = 1L;
        int availableSeats = 0;
        int numberOfAdults = 1;
        int numberOfChildren = 0;

        // Mock behaviour of tourRepository
        Tour tour = new Tour();
        tour.setAvailableSeats(availableSeats);
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tour));

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = new PurchaseData();
        purchaseData.setTourId(tourId);
        purchaseData.setNumberOfAdults(numberOfAdults);
        purchaseData.setNumberOfChildren(numberOfChildren);
        when(purchaseDataRepository.findById(purchaseDataId)).thenReturn(Optional.of(purchaseData));

        // Assert
        assertThrows(NoAvailableSeatsException.class, () -> purchaseDataService.finalizePurchase(purchaseDataId));
    }
}
