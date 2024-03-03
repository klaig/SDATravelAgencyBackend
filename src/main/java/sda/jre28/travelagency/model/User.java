package sda.jre28.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;



@Entity
@Table(name = "user")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
