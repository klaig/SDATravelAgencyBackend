package sda.jre28.travelagency.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.model.User;
import sda.jre28.travelagency.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/tour/create")
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        Tour createdTour = adminService.createTour(tour);
        return ResponseEntity.ok(createdTour);
    }
    @PutMapping(value = "/tours/{tourId}")
    public ResponseEntity<Tour> updateTour(@PathVariable Long tourId, @RequestBody Tour tour) {
        adminService.updateTour(tourId, tour);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/tours/{tourId}")
    public ResponseEntity<String> deleteTour(@PathVariable Long tourId) {
        adminService.deleteTour(tourId);
        return ResponseEntity.ok("Successfully deleted");
    }

    @GetMapping("/tour/all")
    public List<PurchaseData> findAllPurchaseDatas() {
        return adminService.findAllPurchaseDatas();
    }
    //Find all tours that have been finalized or reserved
    @GetMapping("/tour/bought")
    public List<PurchaseData> findAllByIsPurchased(@RequestParam("isPurchased") boolean isPurchased) {
        return adminService.findAllByIsPurchased(isPurchased);
    }

    @GetMapping("/tour/userId")
    public List<PurchaseData> findAllByUserId(@RequestParam("userId") Long userId) {
        return adminService.findAllByUserId(userId);
    }

    @GetMapping("/tour/users")
    public List<User> findAllUsersByTour(@RequestParam("userId") Long userId) {
        return adminService.findAllUsersByTour(userId);
    }
}
