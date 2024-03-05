package sda.jre28.travelagency;

import org.springframework.stereotype.Component;
import sda.jre28.travelagency.model.CityType;
import sda.jre28.travelagency.model.PurchaseData;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.model.User;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.TourRepository;
import sda.jre28.travelagency.repository.UserRepository;

import java.time.LocalDate;


@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner{
    private final TourRepository tourRepository;
    private final PurchaseDataRepository purchaseDataRepository;
    private final UserRepository userRepository;

    public CommandLineRunner(TourRepository tourRepository, PurchaseDataRepository purchaseDataRepository, UserRepository userRepository) {
        this.tourRepository = tourRepository;
        this.purchaseDataRepository = purchaseDataRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = User.builder()
                .name("Kevin")
                .build();
        userRepository.save(user1);
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

        PurchaseData purchaseData1 = PurchaseData.builder()
                .numberOfAdults(3)
                .numberOfChildren(2)
                .tourId(1L)
                .userId(1L)
                .build();
        purchaseDataRepository.save(purchaseData1);
    }



}
