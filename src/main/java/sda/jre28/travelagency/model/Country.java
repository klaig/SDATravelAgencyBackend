package sda.jre28.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Set;

@Entity
@Table(name = "country")
@Getter
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CountryType countryType;
    @OneToMany(mappedBy = "country")
    private Set<City> cities;
    @ManyToOne
    @JoinColumn(name = "continentId", nullable = false)
    private Continent continent;

}
