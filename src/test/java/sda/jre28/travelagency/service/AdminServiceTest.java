package sda.jre28.travelagency.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.AdminRepository;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;

import java.time.LocalDate;
import java.util.List;

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

        // Mock behaviour of tourRepository
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

        // Mock data
        double adultPrice = 100 ;

        // Mock behaviour of tourRepository
        Tour tour = new Tour();
        tour.setAdultPrice(adultPrice);
        when(tourRepository.save(tour)).thenReturn(tour);

        // Calling the method
        Tour tourResult = adminService.createTour(tour);

        // Assert
        assertEquals(tourResult.getAdultPrice(), adultPrice);

    }

    @Test
    public void testDeleteTour_returnSuccessfully() {

        // Mock data
        CityType destination = CityType.ROME ;

        // Mock behaviour of tourRepository
        Tour tour = new Tour();
        tour.setDestination (destination);
        when(tourRepository.save(tour)).thenReturn(tour);

        // Calling the method
        Tour tourResult = adminService.createTour(tour);

        // Assert
        assertEquals(tourResult.getDestination(), destination);


    }

    @Test
    public void testFindAllPurchaseDatas_returnSuccessfully() {

        // Mock data
        Long purchaseDataId = 1L;
        int numberOfAdults = 5;
        int numberOfChildren = 4;
        Long tourId = 1L;
        Long userId = 1L;
        boolean isPurchased = true;

        // Mock behaviour of purchaseDataRepository
        PurchaseData purchaseData = new PurchaseData();
        purchaseData.setPurchased(isPurchased);
        purchaseData.setNumberOfAdults(numberOfAdults);
        purchaseData.setNumberOfChildren(numberOfChildren);
        purchaseData.setTourId(tourId);
        purchaseData.setId(purchaseDataId);
        purchaseData.setUserId(userId);
        when(purchaseDataRepository.findAll()).thenReturn(List.of(purchaseData));

        // Calling the method
        List<PurchaseData> listResult = adminService.findAllPurchaseDatas();

        // Assert
        assertEquals(listResult.get(0).getId(), purchaseDataId);
        assertEquals(listResult.get(0).getNumberOfAdults(), numberOfAdults) ;
        assertEquals(listResult.get(0).getTourId(), tourId);
        assertEquals(listResult.get(0).getUserId(), userId);
        assertEquals(listResult.get(0).getNumberOfChildren(), numberOfChildren);
        assertEquals(listResult.get(0).isPurchased(), isPurchased);



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
