package sda.jre28.travelagency.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sda.jre28.travelagency.exceptions.NoAvailableSeatsException;
import sda.jre28.travelagency.exceptions.UserAlreadyExistsException;
import sda.jre28.travelagency.model.*;
import sda.jre28.travelagency.repository.PurchaseDataRepository;
import sda.jre28.travelagency.repository.RoleRepository;
import sda.jre28.travelagency.repository.TourRepository;
import sda.jre28.travelagency.repository.UserRepository;
import sda.jre28.travelagency.service.PurchaseDataService;
import sda.jre28.travelagency.service.UserService;

import java.time.LocalDate;
import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final TourRepository tourRepository;
    private final PurchaseDataRepository purchaseDataRepository;
    private final UserService userService;
    private final PurchaseDataService purchaseDataService;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, TourRepository tourRepository, PurchaseDataRepository purchaseDataRepository, UserService userService, PurchaseDataService purchaseDataService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tourRepository = tourRepository;
        this.purchaseDataRepository = purchaseDataRepository;
        this.userService = userService;
        this.purchaseDataService = purchaseDataService;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeRoles();
        initializeDefaultAdmin();
        initializeTestUsers();
        initializeTestTours();
        initializeTestPurchaseData();

    }

    private void initializeRoles() {
        createRoleIfNotFound("ROLE_USER");
        createRoleIfNotFound("ROLE_ADMIN");
        // Add any other roles needed here
    }

    private void createRoleIfNotFound(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (role.isEmpty()) {
            Role newRole = new Role();
            newRole.setName(name);
            roleRepository.saveAndFlush(newRole);
        }
    }

    private void initializeDefaultAdmin() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            User adminUser = new User();
            adminUser.setName("Administrator");
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword(passwordEncoder.encode("potato"));
            adminUser.setRoles(adminRoles);

            userRepository.save(adminUser);
        }
    }

    private void initializeTestUsers() throws UserAlreadyExistsException {
        if (userRepository.findByUsername("Kevin").isEmpty()) {
            userService.createUser("Kevin Laig", "laig.kevin@gmail.com", "Kevin", "keviniParool");
        }
        if (userRepository.findByUsername("Triin").isEmpty()) {
            userService.createUser("Triin Kangur", "triinkangur35@gmail.com", "Triin", "triinuParool");
        }
        if (userRepository.findByUsername("Jaan").isEmpty()) {
            userService.createUser("Jaan Krukovski", "jaankrukovski@gmail.com", "Jaan", "jaaniParool");
        }
    }


    private void initializeTestTours() {
        List<Tour> tours = new ArrayList<>();

        Tour tour1 = Tour.builder()
                .destination(CityType.BANGKOK)
                .departureDate(LocalDate.parse("2024-03-20"))
                .returnDate(LocalDate.parse("2024-03-23"))
                .length(3)
                .adultPrice(200)
                .childPrice(150)
                .promoted(true)
                .availableSeats(50)
                .build();
        tours.add(tour1);

        Tour tour2 = Tour.builder()
                .destination(CityType.MEXICO_CITY)
                .departureDate(LocalDate.parse("2024-03-21"))
                .returnDate(LocalDate.parse("2024-03-25"))
                .length(4)
                .adultPrice(150)
                .childPrice(80)
                .promoted(false)
                .availableSeats(30)
                .build();
        tours.add(tour2);

        Tour tour3 = Tour.builder()
                .destination(CityType.TORONTO)
                .departureDate(LocalDate.parse("2024-03-22"))
                .returnDate(LocalDate.parse("2024-03-26"))
                .length(4)
                .adultPrice(110)
                .childPrice(60)
                .promoted(false)
                .availableSeats(70)
                .build();
        tours.add(tour3);

        Tour tour4 = Tour.builder()
                .destination(CityType.CANCUN)
                .departureDate(LocalDate.parse("2024-03-24"))
                .returnDate(LocalDate.parse("2024-03-29"))
                .length(5)
                .adultPrice(250)
                .childPrice(200)
                .promoted(false)
                .availableSeats(50)
                .build();
        tours.add(tour4);

        Tour tour5 = Tour.builder()
                .destination(CityType.DELHI)
                .departureDate(LocalDate.parse("2024-03-24"))
                .returnDate(LocalDate.parse("2024-03-28"))
                .length(4)
                .adultPrice(120)
                .childPrice(50)
                .promoted(true)
                .availableSeats(40)
                .build();
        tours.add(tour5);

        Tour tour6 = Tour.builder()
                .destination(CityType.BARCELONA)
                .departureDate(LocalDate.parse("2024-03-25"))
                .returnDate(LocalDate.parse("2024-03-30"))
                .length(5)
                .adultPrice(300)
                .childPrice(200)
                .promoted(true)
                .availableSeats(50)
                .build();
        tours.add(tour6);

        Tour tour7 = Tour.builder()
                .destination(CityType.MALAGA)
                .departureDate(LocalDate.parse("2024-03-26"))
                .returnDate(LocalDate.parse("2024-03-31"))
                .length(5)
                .adultPrice(450)
                .childPrice(400)
                .promoted(false)
                .availableSeats(20)
                .build();
        tours.add(tour7);

        Tour tour8 = Tour.builder()
                .destination(CityType.MUMBAI)
                .departureDate(LocalDate.parse("2024-03-27"))
                .returnDate(LocalDate.parse("2024-04-01"))
                .length(5)
                .adultPrice(40)
                .childPrice(20)
                .promoted(true)
                .availableSeats(10)
                .build();
        tours.add(tour8);

        Tour tour9 = Tour.builder()
                .destination(CityType.PHUKET)
                .departureDate(LocalDate.parse("2024-03-25"))
                .returnDate(LocalDate.parse("2024-03-28"))
                .length(3)
                .adultPrice(330)
                .childPrice(200)
                .promoted(true)
                .availableSeats(50)
                .build();
        tours.add(tour9);

        Tour tour10 = Tour.builder()
                .destination(CityType.ROME)
                .departureDate(LocalDate.parse("2024-03-25"))
                .returnDate(LocalDate.parse("2024-03-31"))
                .length(6)
                .adultPrice(600)
                .childPrice(300)
                .promoted(true)
                .availableSeats(35)
                .build();
        tours.add(tour10);

        Tour tour11 = Tour.builder()
                .destination(CityType.VENICE)
                .departureDate(LocalDate.parse("2024-03-25"))
                .returnDate(LocalDate.parse("2024-03-29"))
                .length(4)
                .adultPrice(1000)
                .childPrice(800)
                .promoted(false)
                .availableSeats(50)
                .build();
        tours.add(tour11);

        Tour tour12 = Tour.builder()
                .destination(CityType.VANCOUVER)
                .departureDate(LocalDate.parse("2024-03-25"))
                .returnDate(LocalDate.parse("2024-03-28"))
                .length(3)
                .adultPrice(215)
                .childPrice(175)
                .promoted(false)
                .availableSeats(50)
                .build();
        tours.add(tour12);
        tourRepository.saveAll(tours);
    }

    private void initializeTestPurchaseData() throws NoAvailableSeatsException {
        List<PurchaseData> purchaseData = new ArrayList<>();

        PurchaseData purchaseData1 = PurchaseData.builder()
                .numberOfAdults(3)
                .numberOfChildren(2)
                .tourId(1L)
                .userId(1L)
                .build();
        purchaseData.add(purchaseData1);

        PurchaseData purchaseData2 = PurchaseData.builder()
                .numberOfAdults(2)
                .numberOfChildren(1)
                .tourId(1L)
                .userId(2L)
                .build();
        purchaseData.add(purchaseData2);

        PurchaseData purchaseData3 = PurchaseData.builder()
                .numberOfAdults(4)
                .numberOfChildren(5)
                .tourId(1L)
                .userId(3L)
                .build();
        purchaseData.add(purchaseData3);

        PurchaseData purchaseData4 = PurchaseData.builder()
                .numberOfAdults(3)
                .numberOfChildren(2)
                .tourId(2L)
                .userId(1L)
                .build();
        purchaseData.add(purchaseData4);

        PurchaseData purchaseData5 = PurchaseData.builder()
                .numberOfAdults(4)
                .numberOfChildren(4)
                .tourId(2L)
                .userId(2L)
                .build();
        purchaseData.add(purchaseData5);

        PurchaseData purchaseData6 = PurchaseData.builder()
                .numberOfAdults(2)
                .numberOfChildren(3)
                .tourId(3L)
                .userId(3L)
                .build();
        purchaseData.add(purchaseData6);
        purchaseDataRepository.saveAll(purchaseData);

        purchaseDataService.finalizePurchase(purchaseData1.getId());
        purchaseDataService.finalizePurchase(purchaseData2.getId());
        purchaseDataService.finalizePurchase(purchaseData3.getId());
        purchaseDataService.finalizePurchase(purchaseData4.getId());
        purchaseDataService.finalizePurchase(purchaseData5.getId());
    }

}