package sda.jre28.travelagency.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.service.TourService;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class TourController {
    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }
    //http://localhost:8080/api/v1/tours

    //Find tour by tourId
    @GetMapping(path = "/tour")
    public ResponseEntity<Tour> findById(@RequestParam("tourId") Long tourId) {
        return ResponseEntity.ok(tourService.findById(tourId));
    }
    @GetMapping(path = "/tours")
    public List<Tour> findAllTours() {
        return tourService.findAllTours();
    }

    @GetMapping(path = "/tours/city")
    public List<Tour> findAllByCity(@RequestParam("city") String cityType) {
        return tourService.findAllByDestination(CityType.valueOf(cityType.toUpperCase()));
    }

    @GetMapping(path = "/tours/dates")
    public List<Tour> findAllByDepartureDateBetween(@RequestParam("departureDate") String departureDate, @RequestParam("returnDate") String returnDate) {
        return tourService.findAllByDepartureDateBetween(LocalDate.parse(departureDate), LocalDate.parse(returnDate));
    }

    @GetMapping(path = "/tours/length")
    public List<Tour> findAllByLength(@RequestParam("days") Integer length) {
        return tourService.findAllByLength(length);
    }

    @GetMapping(path = "/tours/price")
    public List<Tour> findAllByAdultPriceBetween(@RequestParam("min") double minPrice, @RequestParam("max") double maxPrice) {
        return tourService.findAllByAdultPriceBetween(minPrice, maxPrice);
    }

    @GetMapping(path = "/tours/promoted")
    public List<Tour> findAllByPromoted(@RequestParam("promoted") boolean promoted) {
        return tourService.findAllByPromoted(promoted);
    }

    @GetMapping("/mytours")
    public List<Tour> findAllBoughtTours(@RequestParam("userId") Long userId) {
        return tourService.findAllBoughtTours(userId);
    }
}
