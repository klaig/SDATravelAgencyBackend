package sda.jre28.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.jre28.travelagency.model.PurchaseData;

import java.util.List;

@Repository
public interface PurchaseDataRepository extends JpaRepository<PurchaseData, Long> {
    List<PurchaseData> findAllByUserId(Long userId);

    List<PurchaseData> findAllByIsPurchased(boolean isPurchased);
}
