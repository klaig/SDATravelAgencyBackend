package sda.jre28.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.Set;

@Entity
@Getter
@Table(name = "continent")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ContinentType continentType;
    @OneToMany(mappedBy = "continent")
    private Set<Country> countries;
}
