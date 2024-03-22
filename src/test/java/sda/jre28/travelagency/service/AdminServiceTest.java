package sda.jre28.travelagency.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.model.User;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;
import sda.jre28.travelagency.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    TourRepository tourRepository;

    @Mock
    PurchaseDataRepository purchaseDataRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private AdminService adminService;

    @Test
    public void testCreateTour_returnSuccessfully() {

        // Mock data
        int availableSeats = 25;

        // Mock behaviour of tourRepository
        Tour tour = createTestTour();
        tour.setAvailableSeats(availableSeats);
        when(tourRepository.save(tour)).thenReturn(tour);

        // Calling the method
        Tour tourResult = adminService.createTour(tour);

        // Assert
        assertEquals(tour.getAvailableSeats(), tourResult.getAvailableSeats());
        assertEquals(tour.getId(), tourResult.getId());
    }

    @Test
    public void testUpdateTour_returnSuccessfully() {

        // Mock data
        double adultPrice = 100;
        Long tourId = 1L;

        // Mock behaviour of tourRepository
        Tour tour = createTestTour();
        tour.setAdultPrice(adultPrice);
        Tour tour2 = createTestTour();
        tour2.setAdultPrice(adultPrice + 50);
        when(tourRepository.findById(tourId)).thenReturn(Optional.of(tour));
        when(tourRepository.save(tour)).thenReturn(tour);

        // Calling the method
        adminService.updateTour(tourId, tour2);

        // Assert
        assertEquals(tour.getAdultPrice(), tour2.getAdultPrice());
        assertEquals(tour.getAdultPrice(), adultPrice + 50);
    }

    @Test
    public void testDeleteTour_returnSuccessfully() {

        // Mock data
        Long tourId = 1L;

        // Calling the method
        adminService.deleteTour(tourId);

        // Verifies that tourRepository.deleteById(tourId) is called exactly once
        verify(tourRepository, times(1)).deleteById(tourId);
    }

    @Test
    public void testFindAllPurchaseData_returnSuccessfully() {

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = createTestPurchaseData();
        PurchaseData purchaseData2 = createTestPurchaseData();
        when(purchaseDataRepository.findAll()).thenReturn(List.of(purchaseData, purchaseData2));

        // Calling the method
        List<PurchaseData> purchaseDataResult = adminService.findAllPurchaseData();

        // Assert
        assert purchaseDataResult.size() == 2;
        assertEquals(purchaseData.getId(), purchaseDataResult.get(0).getId());
        assertEquals(purchaseData2.getId(), purchaseDataResult.get(1).getId());
    }

    @Test
    public void testFindAllByIsPurchased_returnSuccessfully() {

        // Mock data
        boolean isPurchased = true;

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = createTestPurchaseData();
        PurchaseData purchaseData2 = createTestPurchaseData();
        purchaseData.setPurchased(isPurchased);
        purchaseData2.setPurchased(isPurchased);
        when(purchaseDataRepository.findAllByIsPurchased(isPurchased)).thenReturn(List.of(purchaseData, purchaseData2));

        // Calling the method
        List<PurchaseData> purchaseDataResult = purchaseDataRepository.findAllByIsPurchased(isPurchased);

        // Assert
        assert purchaseDataResult.size() == 2;
        assertEquals(purchaseData.isPurchased(), purchaseDataResult.get(0).isPurchased());
        assertEquals(purchaseData2.isPurchased(), purchaseDataResult.get(1).isPurchased());
    }



    @Test
    public void testFindAllUsersByTour_returnSuccessfully() {
        // Mock data
        Long tourId = 1L;
        Long userId = 1L;
        Long userId2 = 2L;

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = createTestPurchaseData();
        PurchaseData purchaseData2 = createTestPurchaseData();
        purchaseData.setTourId(tourId);
        purchaseData2.setTourId(tourId);
        purchaseData.setUserId(userId);
        purchaseData2.setUserId(userId2);
        when(purchaseDataRepository.findAllByIsPurchased(true)).thenReturn(List.of(purchaseData, purchaseData2));

        // Mock behaviour of userRepository
        User user = new User();
        User user2 = new User();
        user.setId(userId);
        user2.setId(userId2);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.findById(userId2)).thenReturn(Optional.of(user2));

        // Calling the method
        List<User> userResult = adminService.findAllUsersByTour(tourId);

        // Assert
        assert userResult.size() == 2;
        assertEquals(user.getId(), userResult.get(0).getId());
        assertEquals(user2.getId(), userResult.get(1).getId());
    }

    @Test
    public void testFindAllByUserId_returnSuccessfully() {

        // Mock data
        Long userId = 1L;

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = createTestPurchaseData();
        PurchaseData purchaseData2 = createTestPurchaseData();
        purchaseData.setUserId(userId);
        purchaseData2.setUserId(userId);
        when(purchaseDataRepository.findAllByUserId(userId)).thenReturn(List.of(purchaseData, purchaseData2));

        // Calling the method
        List<PurchaseData> purchaseDataResult = adminService.findAllByUserId(userId);

        // Assert
        assert purchaseDataResult.size() == 2;
        assertEquals(purchaseData.getUserId(), purchaseDataResult.get(0).getUserId());
        assertEquals(purchaseData2.getUserId(), purchaseDataResult.get(1).getUserId());
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
