package sda.jre28.travelagency.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.repository.PurchaseDataRepository;

@ExtendWith(MockitoExtension.class)
public class PurchaseDataServiceTest {
    @Mock
    PurchaseDataRepository purchaseDataRepository;

    @InjectMocks
    private PurchaseDataService purchaseDataService;

    @Test
    public void testCreatePurchaseData_returnSuccessfully() {

    }

    @Test
    public void testCalculateTotal_returnSuccessfully() {

    }

    @SneakyThrows
    @Test
    public void testFinalizePurchase_returnSuccessfully() {

    }

    @Test
    public void testFinalizePurchase_throwsException() {

    }
}
