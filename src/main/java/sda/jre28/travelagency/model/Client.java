package sda.jre28.travelagency.model;

import jakarta.persistence.*;
import lombok.Getter;



@Entity
@Table(name = "client")
@Getter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
