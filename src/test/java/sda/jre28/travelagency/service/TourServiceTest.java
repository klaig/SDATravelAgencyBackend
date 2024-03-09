package sda.jre28.travelagency.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.TourRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TourServiceTest {

    @Mock
    TourRepository tourRepository;

    @InjectMocks
    private TourService tourService;

    @Test
    public void testFindById_returnSuccessfully() {

        Tour tour = new Tour();
        tour.setId(1L);
        tour.setDestination(CityType.TORONTO);
        tour.setLength(7);
        tour.setAdultPrice(250);
        tour.setChildPrice(150);
        tour.setAvailableSeats(30);
        tour.setPromoted(true);
        tour.setDepartureDate(LocalDate.parse("2024-03-09"));
        tour.setReturnDate(LocalDate.parse("2024-03-16"));

        when(tourRepository.findById(1L)).thenReturn(Optional.of(tour));

        Tour tourResult = tourService.findById(1L);

        assertNotNull(tourResult);
        assertEquals(tourResult.getLength(), 7);


    }


    @Test
    public void testFindAllTours_returnSuccessfully() {

        //Given
        Tour tour = new Tour();
        tour.setId(1L);
        tour.setDestination(CityType.TORONTO);
        tour.setLength(7);
        tour.setAdultPrice(250);
        tour.setChildPrice(150);
        tour.setAvailableSeats(30);
        tour.setPromoted(true);
        tour.setDepartureDate(LocalDate.parse("2024-03-09"));
        tour.setReturnDate(LocalDate.parse("2024-03-16"));

        Tour tour2 = new Tour();
        tour2.setId(2L);
        tour2.setDestination(CityType.MEXICO_CITY);
        tour2.setLength(3);
        tour2.setAdultPrice(150);
        tour2.setChildPrice(75);
        tour2.setAvailableSeats(15);
        tour2.setPromoted(false);
        tour2.setDepartureDate(LocalDate.parse("2024-03-10"));
        tour2.setReturnDate(LocalDate.parse("2024-03-13"));

        //When
        when(tourRepository.findAll()).thenReturn(List.of(tour, tour2));

        //Method you want to test
        List<Tour> tourList = tourService.findAllTours();

        //Then
        assert tourList.size() == 2;
        assertEquals(tourList.get(0).getId(), 1L);

    }

    @Test
    public void testFindAllByDestination_returnSuccessfully() {

    }

    @Test
    public void testFindAllByDepartureDateBetween_returnSuccessfully() {

    }

    @Test
    public void testFindAllByLength_returnSuccessfully() {


    }

    @Test
    public void testFindAllByAdultPriceBetween_returnSuccessfully() {

    }

    @Test
    public void testFindAllByPromoted_returnSuccessfully() {

    }

    @Test
    public void testFindAllBoughtTours_returnSuccessfully() {

    }




}
