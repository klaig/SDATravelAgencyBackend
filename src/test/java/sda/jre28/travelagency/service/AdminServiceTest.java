package sda.jre28.travelagency.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.AdminRepository;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    AdminRepository adminRepository;

    @Mock
    TourRepository tourRepository;

    @Mock
    PurchaseDataRepository purchaseDataRepository;

    @InjectMocks
    private AdminService adminService;

    @Test
    public void testCreateTour_returnSuccessfully() {

        // Mock data
        int availableSeats = 25;

        // Mock behaviour of adminRepository
        Tour tour = new Tour();
        tour.setAvailableSeats(availableSeats);
        when(tourRepository.save(tour)).thenReturn(tour);

        // Calling the method
        Tour tourResult = adminService.createTour(tour);

        // Assert
        assertEquals(tourResult.getAvailableSeats(), availableSeats);
    }

    @Test
    public void testUpdateTour_returnSuccessfully() {

    }

    @Test
    public void testDeleteTour_returnSuccessfully() {

    }

    @Test
    public void testFindAllPurchaseDatas_returnSuccessfully() {

    }

    @Test
    public void testFindAllByIsPurchased_returnSuccessfully() {

    }

    @Test
    public void testFindAllByUserId_returnSuccessfully() {

    }

    @Test
    public void testFindAllUsersByTour_returnSuccessfully() {

    }
}
