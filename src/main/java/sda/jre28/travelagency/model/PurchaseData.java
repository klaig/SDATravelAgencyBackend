package sda.jre28.travelagency.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@ToString
@Builder
@Getter
@Setter
public class PurchaseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfAdults;
    private Integer numberOfChildren;
    private Long tourId;
    private Long userId;

    public PurchaseData() {

    }

    public PurchaseData(Long id, Integer numberOfAdults, Integer numberOfChildren, Long tourId, Long userId) {
        this.id = id;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.tourId = tourId;
        this.userId = userId;
    }

    public PurchaseData(Integer numberOfAdults, Integer numberOfChildren, Long tourId, Long userId) {
    }
}


