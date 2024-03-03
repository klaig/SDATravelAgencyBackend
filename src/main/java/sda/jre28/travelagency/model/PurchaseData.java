package sda.jre28.travelagency.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@ToString
@Builder
@Getter
@Setter
public class PurchaseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate purchaseDate;
    private Integer numberOfAdults;
    private Integer numberOfChildren;
    private Long tourId;
    private Long userId;
    private double totalPrice;

    public PurchaseData() {
    }

    public PurchaseData(Long id, LocalDate purchaseDate, Integer numberOfAdults, Integer numberOfChildren, Long tourId, Long userId, double totalPrice) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
        this.tourId = tourId;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }
}
