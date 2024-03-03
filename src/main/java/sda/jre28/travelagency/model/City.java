package sda.jre28.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;


@Entity
@Table(name = "city")
@Getter
@ToString
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CityType cityType;
//    @ManyToOne
//    @JoinColumn(name="countryId", nullable = false)
//    private Country country;

}
