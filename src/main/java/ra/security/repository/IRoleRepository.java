package ra.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.security.entity.Role;
import ra.security.entity.RoleName;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    // Define methods for role-specific queries if needed
    // For example, you might want to find roles by name or permissions
    Optional<Role> findByRoleName(RoleName roleName);
}
