package sda.jre28.travelagency.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.service.TourService;

@Controller
@RequestMapping("/api/v1")
public class TourController {
    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }
    //http://localhost:8080/api/v1/tours
    @PostMapping("/tours")
    public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
        Tour createdTour = tourService.createTour(tour);
        return ResponseEntity.ok(createdTour);
    }
    @PutMapping(value = "/tours/{tourId}")
    public ResponseEntity<?> updateTour(@PathVariable Long tourId, @RequestBody Tour tour) {
        tourService.updateTour(tourId, tour);
        return ResponseEntity.ok().build();
    }
}
