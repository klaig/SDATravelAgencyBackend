package sda.jre28.travelagency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.jre28.travelagency.exceptions.NoAvailableSeatsException;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.service.AdminService;
import sda.jre28.travelagency.service.PurchaseDataService;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class PurchaseDataController {
    private final PurchaseDataService purchaseDataService;

    private final AdminService adminService;
    @Autowired
    public PurchaseDataController(PurchaseDataService purchaseDataService, AdminService adminService) {
        this.purchaseDataService = purchaseDataService;
        this.adminService = adminService;
    }

    @PostMapping("/tour/purchase")
    public ResponseEntity<PurchaseData> createPurchasedData(@RequestBody PurchaseData purchaseData) {
        PurchaseData purchasedData = purchaseDataService.createPurchaseData(purchaseData);
        return ResponseEntity.ok(purchasedData);
    }

    @GetMapping("/tour/price")
    public ResponseEntity<Double> calculateTotal(@RequestParam("purchaseDataId") Long purchaseDataId) {
        double total = purchaseDataService.calculateTotal(purchaseDataId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/tour/userId")
    public List<PurchaseData> findAllByUserId(@RequestParam("userId") Long userId) {
        return adminService.findAllByUserId(userId);
    }

    @GetMapping("/tour/purchase")
    public ResponseEntity<PurchaseData> finalizePurchase(@RequestParam("purchaseDataId") Long purchaseDataId) throws NoAvailableSeatsException {
        purchaseDataService.finalizePurchase(purchaseDataId);
        return ResponseEntity.ok().build();
    }


}
