package sda.jre28.travelagency.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDate;


@Entity
@Table(name = "tour")
@Getter
@Setter
@Builder
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CityType destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private Integer length;
    private double adultPrice;
    private double childPrice;
    private boolean promoted;
    private Integer availableSeats;

    public Tour() {
    }


    public Tour(Long id, CityType destination, LocalDate departureDate, LocalDate returnDate, Integer length, double adultPrice, double childPrice, boolean promoted, Integer availableSeats) {
        this.id = id;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.length = length;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.promoted = promoted;
        this.availableSeats = availableSeats;
    }

}
