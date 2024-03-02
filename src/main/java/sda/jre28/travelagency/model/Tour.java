package sda.jre28.travelagency.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;



import java.util.Date;


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
    private Date departureDate;
    private Date returnDate;
    private Integer length;
    private String type;
    private Integer adultPrice;
    private Integer childPrice;
    private boolean promoted;
    private Integer availableSeats;

    public Tour() {
    }


    public Tour(Long id, CityType destination, Date departureDate, Date returnDate, Integer length, String type, Integer adultPrice, Integer childPrice, boolean promoted, Integer availableSeats) {
        this.id = id;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.length = length;
        this.type = type;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.promoted = promoted;
        this.availableSeats = availableSeats;
    }
}
