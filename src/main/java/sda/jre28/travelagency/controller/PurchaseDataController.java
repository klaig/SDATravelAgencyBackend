package sda.jre28.travelagency.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.jre28.travelagency.exceptions.NoAvailableSeatsException;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.service.PurchaseDataService;


@RestController
@RequestMapping("/api/v1")
public class PurchaseDataController {
    private final PurchaseDataService purchaseDataService;

    public PurchaseDataController(PurchaseDataService purchaseDataService) {
        this.purchaseDataService = purchaseDataService;
    }

    @PostMapping("/tour/purchase")
    public ResponseEntity<PurchaseData> createPurchasedData(@RequestBody PurchaseData purchaseData) {
        PurchaseData purchasedData = purchaseDataService.createPurchaseData(purchaseData);
        return ResponseEntity.ok(purchasedData);
    }

    @GetMapping("/tour/price")
    public ResponseEntity<Double> calculateTotal(@RequestParam("tourId") Long tourId, @RequestParam("purchaseDataId") Long purchaseDataId) {
        double total = purchaseDataService.calculateTotal(tourId, purchaseDataId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/tour/purchase")
    public ResponseEntity<PurchaseData> finalizePurchase(@RequestParam("purchaseDataId") Long purchaseDataId) throws NoAvailableSeatsException {
        purchaseDataService.finalizePurchase(purchaseDataId);
        return ResponseEntity.ok().build();
    }


}
