package sda.jre28.travelagency.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.service.PurchaseDataService;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class PurchaseDataController {
    private final PurchaseDataService purchaseDataService;

    public PurchaseDataController(PurchaseDataService purchaseDataService) {
        this.purchaseDataService = purchaseDataService;
    }

    @PostMapping("/tour")
    public ResponseEntity<PurchaseData> createPurchasedData(@RequestBody PurchaseData purchaseData) {
        PurchaseData purchasedData = purchaseDataService.createPurchaseData(purchaseData);
        return ResponseEntity.ok(purchasedData);
    }

    @GetMapping("/tour/price")
    public ResponseEntity<Double> calculateTotal(@RequestParam("tourId") Long tourId, @RequestParam("purchaseDataId") Long purchaseDataId) {
        double total = purchaseDataService.calculateTotal(tourId, purchaseDataId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/tour/userId")
    public List<PurchaseData> findAllByUserId(@RequestParam("userId") Long userId) {
        return purchaseDataService.findAllByUserId(userId);
    }

    @GetMapping("/tour/purchase")
    public ResponseEntity finalizePurchase(@RequestParam("purchaseDataId") Long purchaseDataId) {
        purchaseDataService.finalizePurchase(purchaseDataId);
        return ResponseEntity.ok().build();
    }
}
