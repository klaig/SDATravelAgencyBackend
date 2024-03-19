package sda.jre28.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.jre28.travelagency.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
