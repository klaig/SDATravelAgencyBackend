package sda.jre28.travelagency.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.jre28.travelagency.repository.AdminRepository;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @Mock
    AdminRepository adminRepository;

    @InjectMocks
    private AdminService adminService;

    @Test
    public void testCreateTour_returnSuccessfully() {

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
