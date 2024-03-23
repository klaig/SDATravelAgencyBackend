package sda.jre28.travelagency.controller.specifications;

import org.springframework.data.jpa.domain.Specification;
import sda.jre28.travelagency.model.Tour;

import java.time.LocalDate;

public class TourSpecifications {

    public static Specification<Tour> hasDestination(String destination) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.lower(root.get("destination")), "%" + destination.toLowerCase() + "%");
    }

    public static Specification<Tour> isPromoted(boolean promoted) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("promoted"), promoted);
    }

    public static Specification<Tour> hasDepartureDateBetween(LocalDate minDate, LocalDate maxDate) {
        return (root, query, criteriaBuilder) -> {
            if (minDate == null && maxDate == null) {
                return criteriaBuilder.conjunction();  // if both dates are null, apply no restriction
            } else if (minDate == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("departureDate"), maxDate);
            } else if (maxDate == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("departureDate"), minDate);
            } else {
                return criteriaBuilder.between(root.get("departureDate"), minDate, maxDate);
            }
        };
    }

    public static Specification<Tour> hasLength(Integer length) {
        return (root, query, criteriaBuilder) -> {
            if (length == null) {
                return criteriaBuilder.conjunction();  // if length is null, apply no restriction
            }
            return criteriaBuilder.equal(root.get("length"), length);

        };
    }
}

    // Other specifications come here as well
