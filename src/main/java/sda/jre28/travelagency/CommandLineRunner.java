package sda.jre28.travelagency;

import org.springframework.stereotype.Component;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.repository.TourRepository;

import java.time.LocalDate;


@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner{
    private final TourRepository tourRepository;

    public CommandLineRunner(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Tour tour1 = Tour.builder()
                .destination(CityType.BANGKOK)
                .departureDate(LocalDate.parse("2024-03-20"))
                .returnDate(LocalDate.parse("2024-03-23"))
                .length(7)
                .adultPrice(50)
                .childPrice(30)
                .promoted(true)
                .availableSeats(300)
                .build();
        tourRepository.save(tour1);
    }

}
