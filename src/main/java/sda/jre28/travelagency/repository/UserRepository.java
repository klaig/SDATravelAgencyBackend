package sda.jre28.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sda.jre28.travelagency.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
