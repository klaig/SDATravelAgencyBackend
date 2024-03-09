package sda.jre28.travelagency.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public User() {

    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
    }
}
